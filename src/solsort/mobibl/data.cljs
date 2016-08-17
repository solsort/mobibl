(ns solsort.mobibl.data
  (:require-macros
    [cljs.core.async.macros :refer [go go-loop alt!]]
    [reagent.ratom :as ratom :refer  [reaction run!]])
  (:require
    [solsort.util
     :refer
     [<p <load-script throttle <ajax <seq<! js-seq normalize-css load-style! put!close!
      parse-json-or-nil log page-ready render dom->clj]]
    [clojure.walk :refer [keywordize-keys]]
    [reagent.core :as reagent :refer []]
    [clojure.data]
    [solsort.appdb :refer [db db!]]
    [re-frame.core :as re-frame
     :refer [register-sub subscribe register-handler dispatch dispatch-sync]]
    [cljs.core.async :refer [>! <! chan put! take! timeout close! pipe]]))
(defonce clid "a36227da-e477-491e-b4a2-ccd9df365cf9")
(defonce clsec "YfO7hc8OJ+vUGh9GhMZhJw06cyHxNi48fwWnVLJGPrPHvkZaYYj0cboM")
(defn <op [endpoint o]
  (go
    (when-not js/window.dbcOpenPlatform
      (<! (<load-script "https://openplatform.dbc.dk/v1/dbc_openplatform.min.js")))
    (when-not (js/dbcOpenPlatform.connected)
      (<! (<p (js/dbcOpenPlatform.connect clid clsec))))
    (js->clj (<! (<p  (.call (aget js/dbcOpenPlatform (name endpoint)) js/dbcOpenPlatform (clj->js o)) )))))

(def facets ["subjectDBC0" "subjectDBCF" "subjectDBCN" "subjectDBCS" "subjectDK5Text" "subjectGenre" "language" "type" "creator" "contributor" "audience" "temporalDBCP"])
(defn dkabm->data [o]
  (letfn [(g [key] (first (get o key)))]
    (log
     {:title (g "dcTitle")
      :cover-url (:cover-url o)
      :creator (g "creator")
      :keywords (apply concat (for [facet facets] (get o facet [])))
      :location nil
      :year (g "date")
      :description (or (g "abstract") (g "description"))
      :language (g "language")}
     o)))

(defn libraries []
  (db [:libraries] {}))
(defn get-work [pid]
  (when-not (db [:work pid]) (db! [:work pid] {}))
  (dkabm->data (db [:work pid])))
(defn get-search [q page]
  (let [results (db [:search [q page]])]
    (when-not results (db! [:search-request] [q page]))
    results))

(def view-fields ["dcTitle" "creator" "coverUrlFull" "subjectDBCF" "description" "language"])
(defn load-work [id]
  (go
    (db! [:work id :status-work] :loading)
    (let [[o] (<! (<op :work {:pids [id]}))
          o (into o {:status-work :loaded})]
      (db! [:work id] (into (db [:work id] {}) o)))))

(defn load-cover [id]
  (go
    (db! [:work id :cover-url] "assets/loading.png")
    (db! [:work id :cover-url]
          (first
           (get (first (<! (<op :work {:pids [id] :fields ["coverUrlFull"]})))
                 "coverUrlFull"
                 ["assets/no-cover.png"])))
    ))
(defn load-search [[q page]]
  (go
    (db! [:search [q page]] [])
    (let [results (<! (<op :search {:fields ["pid" "collection" "collectionDetails"]:q q :limit 10 :offset (* 10 page)}))]
      (when (coll? results)
        (db! [:search [q page]] (map #(first (get % "pid")) results))
        (doall
         (for [result results]
           (let [works (get result "collectionDetails")
                 collection (get result "collection")]
             (doall
              (for [work works]
                (let [pid (first (get work "pid"))]
                  (db! [:work pid] work)))))))))))

(defn load-libraries []
 (go
   (db! [:libraries]
        (into
         {}
         (map
          (fn [o] [(get o "branchId") o])
          (filter #(and
                    (get % "geolocation")
                    (= "Folkebibliotek" (get % "agencyType")))
                  (<! (<op :libraries {}))))))))

(defn needs-search [[q results]]
  (< (get results :wanted -1) (get results :requested -1)))
(defn key2? [k] #(not (nil? (get (second %) k))))

(defn sync []
  (doall (map load-work (keys (remove (key2? :status-work) (db [:work])))))
  (doall (map load-cover (keys (remove (key2? :cover-url) (db [:work])))))
  (when (empty? (db [:libraries]))
    (load-libraries))
  (when-not (db [:search (db [:search-request])])
    (load-search (db [:search-request]))))

(def throttled-sync (throttle sync 1000))
(defonce -runner (run! (db) (throttled-sync)))
(sync)
(throttled-sync)

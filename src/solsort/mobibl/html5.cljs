;; # HTML5 view (html5.cljs)
;;
(ns solsort.mobibl.html5
  (:require-macros
    [cljs.core.async.macros :refer [go go-loop alt!]]
    [reagent.ratom :as ratom :refer  [reaction]])
  (:require
    [solsort.util
     :refer
     [<ajax <seq<! js-seq normalize-css load-style! put!close!
      parse-json-or-nil log page-ready render dom->clj]]
    [reagent.core :as reagent :refer []]
    [re-frame.core :as re-frame
     :refer [register-sub subscribe register-handler dispatch dispatch-sync]]
    [clojure.string :as string :refer [replace split blank?]]
    [cljs.core.async :refer [>! <! chan put! take! timeout close! pipe]]
    [solsort.mobibl.bib-map :refer [bib-map]]
    [goog.string :refer [unescapeEntities]]))

;; ## Styling
;;
(load-style! normalize-css "style-reset")
(def highlight "#326bc5")
;(def background fade from "#eaeaea" to "#ffffff")
(def dark "#262626")
(def light "#e5e5e5")
(def medium "#d8d8d8")
(defn styling []
  ;; ### general styling
  ;; We are designing for mobile-portrait-mode,
  ;; which can be enforced in the packaged cordova-app.
  ;;
  ;; Using a unit-size of 1/24 of the mobile portrait width,
  ;; means that a clickable object should preferibly
  ;; be 4 units high/wide, though 3 units is also ok.
  ;;
  ;; It also allows for 1/2, 1/3, 1/4, and 1/6 division of the screen,
  ;; and 5/8 vs 3/8 which approximately the golden ratio.
  ;;
  (let [unit (/ (js/Math.min js/document.body.clientHeight
                             js/document.body.clientWidth)
                24)
        unit-height (/ js/document.body.clientHeight 24)]
    (load-style!
      {:body
       {
        ; :background "url(assets/background.jpg)"
        ; :background-color "#fbf8f4"
        :font-family "\"Open Sans\", sans-serif"
        :font-weight "300"}
       ".bold" {:font-weight "bold"}
       ".center" {:text-align :center}
       ".italic" {:font-style "italic"}
       ".large" {:font-size "120%"}
       ".small" {:font-size "80%"}
       ".regular " {:font-weight "300"}
       ".condensed" {:font-family "\"Open Sans Condensed\""}
       ".ssbutton"
       {:display :inline-block
        :min-height (* 2.5 unit)
        :border-radius (* 0.5 unit)
        :border (str (* 0.15 unit) "px solid black")
        :padding-top (* 0.5 unit)
        :padding-left (* 0.3 unit)
        :padding-right (* 0.3 unit)
        :text-align :center
        :vertical-align :middle}}
      "general styling")
    ;; ### Tabbar
    (load-style!
      {".tabbar-spacer"
       {:display :inline-block
        :height 50
        }
       ".tabbar"
       {:position :fixed
        :box-sizing :border-box
        :bottom 0
        :text-align :center
        :left 0
        :width "100%"
        ;:background "url(assets/background.jpg)"
        ;:background-color "#fbf8f4"
        :background-color "#ffffff"
        :box-shadow "-1px 0px 5px rgba(0,0,0,1);"
        :z-index "100"
        }
       ".tabbar a"
       {:display :inline-block
        :box-sizing :border-box
        :width (* 0.25 (- js/document.body.clientWidth 100))
        :text-align :center}
       ".tabbar img"
       {:padding 5
        :height 40
        :width 40}
       }
      "tabbar-styling")
    ;; ### Book
    (load-style!
      {".work"
       {:margin-left unit
        :margin-right unit }
       ".work-cover-img"
       {:float :right
        :max-width "62%"
        :max-height (- js/window.innerHeight (* 4 unit)) }
       ".work .title"
       {:text-align :center
        :font-size "200%"
        :margin-top unit }
       ".work .author"
       {:text-align :center
        :margin-bottom unit}
       ".work-keyword"
       {:display :inline-block
        :vertical-align :middle
        :clear :none
        :padding-top (* 0.5 unit)
        :min-height (* 2 unit)
        ;:outline "1px solid black"
        :width (* unit 7.3)
        }
       ".work-img"
       {:float :right
        :margin-left 0
        :margin-right 0
        :width (* unit 14)}}
      "work-style")
    ;; ### Library view
    ;;
    ;; FIXME Not so nice to have the style for bib-map defined here
    ;;
    (load-style!
      {"#bib-map"
       {:height (* unit-height 6)}}
      "bib-map-style")
    (load-style!
      {"table.openhours th"
       {:text-align "left"
        :padding "0em 0.8em 0em 0em"}
       "table.openhours tbody td"
       {:text-align "center"}}
      "open-hours-styling")
(load-style!
  {".contact"
   {:padding "0em 0em 10em 0em"
    ".contact div span"
    {:margin "0em 1em 0em 0em"
     :border "1px solid blue"}}}
  "contact-styling")))

;; ### Actually apply styling
;;
; re-layout on rotation etc.
(js/window.addEventListener "resize" styling)
; re-layout when everything has loaded, to account for
; possible change of width due to appearing scrollbar
(js/window.addEventListener "load" #(js/setTimeout styling 0))
; re-layout on load, and on figwheel reload
(styling)

;; ## Components
;; ### Tab bar - menu in bottom of the screen

(defn tabbar-button [id s]
  [:a {:href (str "#" id)}
   [:img {:src (str "assets/" id "-icon.svg")
          :alt s}]])

(defn tabbar []
  [:div
   [:div.tabbar-spacer " "]
   [:div.tabbar
    [tabbar-button "search" "Søg"]
    [tabbar-button "work" "Materiale"]
    [tabbar-button "library" "Bibliotek"]
    [tabbar-button "status" "Status"]]])

;; ### book-view

(defn work-item [pid]
  (let [o @(subscribe [:work pid])
        keywords
        (map
          (fn [kw] [:a {:href (str "#search/" kw)} kw])
          (:keywords o)
          )]

    [:div
     {:style
      {:position :relative
       :overflow "hidden"
       :height "100%"
       :width "100%" }}
     [:img
      {:src (:cover-url o)
       :style
       {:max-width "33%"
        :max-height "100%"
        :box-sizing :border-box
        :vertical-align :top
        }}]
     [:div
      {:style
       {:display :inline-block
        :box-sizing :border-box
        :width "66%"
        :height "100%"
        :vertical-align :top
        :padding-left ".3em"
        :overflow :hidden
        }}
      [:div
       {:style
        {:display :block
         :position :absolute
         :bottom "0px"
         :height "33%"
         :width "100%"
         :background "linear-gradient(rgba(255,255,255,0), white)"
         }}]
      [:div.bold.large (:title o)]
      [:div.italic.large (:creator o)]
      (into [:div] (interpose " " (map
                                    (fn [s] [:span.condensed
                                             {:style {:display :inline-block}}
                                             s])
                                    (:keywords o))))
      [:div (:description o)]
      ]]
    ))

;; ### Search
;; <img width=20% align=top src=doc/wireframes/search.jpg>

(defn facets [& facets]
  (into 
    [:div.condensed
     {:style 
      {:height "6rem" 
       :overflow :hidden
       :line-height "2rem"
       :margin-bottom "0.4rem" }}]
    (map (fn [s] 
           [:a.ui.label s " "
            [:span.small.regular "123"]
            ])
    facets) )
  
  )
(defn search [query]
  (let
    [results @(subscribe [:search query 0])
     results 
     (map 
       (fn [pid]
         [:a.column
          {:key pid
           :href (str "#work/" pid)}
          [:div
           {:style
            {:border "0px solid black"
             :height "9rem"
             :color :black
             :margin-bottom "1rem"
             :box-shadow "2px 2px 5px 0px rgba(0,0,0,0.1)"
             }
            }
            [work-item pid]]])
       results)]
    (log 'search-results query results)
    [:div.ui.container
     [:h1 "Enby Biblioteker"]
     [:div
      [:div.ui.search.fluid.input.icon
       [:input
        {:placeholder "Indtast søgning"
         :type :text
         :value query
         :on-change #(dispatch-sync [:route "search" (-> % .-target .-value)])
         }]
       [:i.search.icon]
       [:div.results.transition.hidden
        {:style {:display "block !important"}}
        [:div.result "hjhj"]
        [:a.result "reulst2"]
        ]]
      
      
       [facets "Jens Jensen" "Holger Danske" "H C Andersen" "Kumbel"
       "bog" "noder" "cd" "tidskriftsartikel" "dvd" "video" "avisartikel" "lydbog" "billedbog" "VHS" "cd-rom" 
       "engelske skuespillere" "kager" "åer" "gæs" "sjove bøger"
       "engelsk" "dansk" "blandede sprog" "tysk" "færøsk" "persisk"] 
      ]
     [:p]
     [:div.ui.grid
      (merge [:div.stackable.doubling.four.column.row]
             results)]
     [tabbar]
     ]
    ))

;; ### Work
;; <img width=20% align=top src=doc/wireframes/work.jpg>

(defn work [work-id]
  (let [work @(subscribe [:work work-id])
        language (:language work)
        keywords (:keywords work)
        location (:location work)
        creator (:creator work)]
    [:div.ui.container
     ;[:div "TODO: Work history here"]
     [:h1.center (:title work)]
     [:p.center "af " [:a {:href (str "#search/" creator)} creator]]
     [:p.center
      [:img
       {:src (:cover-url work)
        :style
        {:max-height (* 0.5 (- js/document.body.clientHeight 50))
         :max-width (* 0.8 (- js/document.body.clientWidth 20))

         }
        }]
      ]
     [:p.center [:a.ui.primary.button "Bestil"]  ]
     [:p (:description work)]
     (if-not keywords ""
       (into [:p {:style {:line-height "2rem"}}]
             (interpose
               " "
               (for [word keywords]
                 [:a.ui.label {:href
                                   (str "#search/" word)} word]))))
     (if language [:p [:em "Sprog: "] language] "")
     (if location [:p [:em "Opstilling: "] location] "")
     [tabbar]
     ]))


;; ### Library
;; <img width=20% align=top src=doc/wireframes/library.jpg>

(def daynames ["Man" "Tir" "Ons" "Tor" "Fre" "Lør" "Søn"])

(defn library [id]
  (let [current-library (subscribe [:current-library])]
    (fn []
      (let [address (:address @current-library)
            hours   (:hours @current-library)
            phone   (:phone @current-library)]
        [:div
         [:h1 (:name @current-library)]
         [bib-map
          :id "bib-map"
          :pos (:position @current-library)]
         [:div.address
          [:h2 "Adresse"]
          [:div (:road address)]
          [:div (:city address)]
          [:div (:country address)]]
         [:div.open
          [:h2 "Åbningstider"]
          [:table.openhours
           [:thead
            (into
              [:tr [:th]]
              (for [title (map :title hours)]
                [:th title]))]
           (into [:tbody]
                 (for [day (range 7)]
                   (into [:tr
                          [:th (get daynames day)]]
                         (for [area (map :weekdays hours)
                               :let [time (get area day)]]
                           (into [:td]
                                 [(if (nil? time)
                                    "Lukket"
                                    (let [t0 (get time 0)
                                          t1 (get time 1)]
                                      (str (if (< t0 10)
                                             (unescapeEntities "&nbsp;")
                                             "")
                                           t0 " - " t1)))])))))]]
         [:div.contact
          [:h2 "Kontakt"]
          [:div
           [:span "Email"]
           [:span (:email @current-library)]]
          [:div
           [:span "Telefon"]
           [:span (:number phone)]
           [:span (:time phone)]]]
         [tabbar]
         ]))))

;; ### Status
;; <img width=20% align=top src=doc/wireframes/patron-status.jpg>

(defn status []
  (let [arrived (subscribe [:arrived])
        borrowed             (subscribe [:borrowed])
        reservations         (subscribe [:reservations])]
    (fn []
      [:div
       [:h1 "Låner status"]
       [:div {:class "menu"}
        [:button {:type "submit"} "Log Ud"]]
       [:div
        [:h2 "Hjemkomne"]
        (into
          [:ul]
          (for
            [ra @arrived]
            [:li
             [:a {:href (str "#work/" (:id ra))} (:title ra)]
             [:ul
              [:li (str "Afhentes inden " (:until ra))]
              [:li "Opstilling "
               [:a {:href (str "#/location/" (:location ra))} (:location ra)]]
              ;;
              ;; **TODO** Add unique creator ID
              ;;
              [:li
               [:a
                {:href (str "#/creator/" "TODO-creator-id")} (:creator ra)]]
              ]]))]
       [:div
        [:h2 "Hjemlån"]
        [:div
         [:a {:href (str "#/borrowed/renew/all")} "Forny Alle"]]
        (into
          [:ul]
          (for [b @borrowed]
            [:li
             [:a {:href (str "#/borrowed/item/" (:id b))}
              ;;
              ;; **TODO** It would be nice with thumbnails
              ;;
              [:img {:src "http://www.bogpriser.dk/Images/placeholder-cover.png"
                     :width "32" :height "32" :alt "TODO :cover-mini-url"}]
              [:span { :style {:margin-left "1em"}} (:title b)]]
             [:ul
              [:li (str "Afleveres senest " (:until b))]
              [:li [:a {:href (str "#/borrowed/renew/" (:id b))} "Forny"]]]]))]
       [:div
        [:h2 "Bestillinger"]
        (into
          [:ul]
          (for [r @reservations]
            [:li
             [:a {:href (str "#/reservation/" (:id r))} (:title r)]
             [:ul
              [:li [:a {:href (str "#/creator/" (:id r))} (:creator r)]]
              [:li [:a {:href (str "#/reservation/remove/" (:id r))} "Slet"]]
              ]]))]
       [tabbar]
       ])))

;; ### Main App entry point
(defn app []
  (let [[page id] @(subscribe [:route])]
    (case page
      "search" [search id]
      "work" [work id]
      "library" [library id]
      "status" [status]
      [search ""])))

;; ## Execute and events

(render [app])

;; ## Routing

(defn handle-hash []
  (dispatch (into [:route] (string/split (.slice js/location.hash 1) "/"))))
(js/window.addEventListener "hashchange" handle-hash)
(handle-hash)

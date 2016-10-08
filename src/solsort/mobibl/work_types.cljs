(ns solsort.mobibl.work-types)

(def ns
  {"bib" "http://bib.schema.org/"
   "dc" "http://purl.org/dc/elements/1.1/"
   "dkdcplus" "http://biblstandard.dk/abm/namespace/dkdcplus/"
   "dcterms" "http://purl.org/dc/terms/1.1/"
   "misc" "http://rdf.solsort.com/misc"
   "ac" "http://biblstandard.dk/ac/namespace/"
   "bd" "http://opensearch.addi.dk/unspecified/briefDisplay/"
   "os" "http://opensearch.addi.dk/unspecified/"
   "mi" "http://moreinfo.addi.dk/unspecified/"
   "dbcaddi" "http://www.danbib.dk/broend3_relationer/unspecified/"
   "dbcbib" "http://www.danbib.dk/broend3_relationer/unspecified/"})

(def property-mappings
  {"tingRelated" "misc:recommend"
   "abstract" "dcterms:abstract"
   "accessType" "bd:accessType"
   "activity" "ac:activity"
   "alternative" "dcterms:alternative"
   "audienceAge" "dcterms:audience"
   "audience" "dcterms:audience"
   "audienceMedieraad" "dcterms:audience"
   "audiencePegi" "dcterms:audience"
   "collection" "os:collection"
   "collectionDetails" "os:collectionBriefDisplay"
   "continuedIn" "dbcaddi:continuedIn"
   "continues" "dbcaddi:continues"
   "contributorAct" "dc:contributor"
   "contributorAft" "dc:contributor"
   "contributorAnm" "dc:contributor"
   "contributorAnt" "dc:contributor"
   "contributorArc" "dc:contributor"
   "contributorArr" "dc:contributor"
   "contributorArt" "dc:contributor"
   "contributorAud" "dc:contributor"
   "contributorAui" "dc:contributor"
   "contributorAus" "dc:contributor"
   "contributorAut" "dc:contributor"
   "contributorCcp" "dc:contributor"
   "contributorChr" "dc:contributor"
   "contributorClb" "dc:contributor"
   "contributorCli" "dc:contributor"
   "contributorCll" "dc:contributor"
   "contributorCmm" "dc:contributor"
   "contributorCmp" "dc:contributor"
   "contributorCnd" "dc:contributor"
   "contributorCng" "dc:contributor"
   "contributorCom" "dc:contributor"
   "contributorCre" "dc:contributor"
   "contributorCtb" "dc:contributor"
   "contributorCtg" "dc:contributor"
   "contributorCur" "dc:contributor"
   "contributorCwt" "dc:contributor"
   "contributorDkani" "dc:contributor"
   "contributorDkbea" "dc:contributor"
   "contributorDkdes" "dc:contributor"
   "contributorDkfig" "dc:contributor"
   "contributorDkfvl" "dc:contributor"
   "contributorDkind" "dc:contributor"
   "contributorDkmdt" "dc:contributor"
   "contributorDkmed" "dc:contributor"
   "contributorDkmon" "dc:contributor"
   "contributorDkops" "dc:contributor"
   "contributorDkref" "dc:contributor"
   "contributorDkste" "dc:contributor"
   "contributorDktek" "dc:contributor"
   "contributorDktil" "dc:contributor"
   "contributorDkved" "dc:contributor"
   "contributorDrm" "dc:contributor"
   "contributorDte" "dc:contributor"
   "contributorEdt" "dc:contributor"
   "contributorFmo" "dc:contributor"
   "contributorHnr" "dc:contributor"
   "contributor" "dc:contributor"
   "contributorIll" "dc:contributor"
   "contributorInv" "dc:contributor"
   "contributorIsb" "dc:contributor"
   "contributorItr" "dc:contributor"
   "contributorIve" "dc:contributor"
   "contributorIvr" "dc:contributor"
   "contributorLbt" "dc:contributor"
   "contributorLtg" "dc:contributor"
   "contributorLyr" "dc:contributor"
   "contributorMus" "dc:contributor"
   "contributorNrt" "dc:contributor"
   "contributorOrm" "dc:contributor"
   "contributorOth" "dc:contributor"
   "contributorPbl" "dc:contributor"
   "contributorPht" "dc:contributor"
   "contributorPrd" "dc:contributor"
   "contributorPrf" "dc:contributor"
   "contributorPro" "dc:contributor"
   "contributorPROVIDERID" "dc:contributor"
   "contributorPrt" "dc:contributor"
   "contributorRce" "dc:contributor"
   "contributorRes" "dc:contributor"
   "contributorRev" "dc:contributor"
   "contributorScl" "dc:contributor"
   "contributorSds" "dc:contributor"
   "contributorSng" "dc:contributor"
   "contributorSpk" "dc:contributor"
   "contributorStl" "dc:contributor"
   "contributorTrl" "dc:contributor"
   "contributorWdc" "dc:contributor"
   "coverDataUrl117" "mi:coverDataUrl117"
   "coverDataUrl207" "mi:coverDataUrl207"
   "coverDataUrl42" "mi:coverDataUrl42"
   "coverDataUrl500" "mi:coverDataUrl500"
   "coverDataUrlBack" "mi:coverDataUrlBack"
   "coverDataUrlFull" "mi:coverDataUrlFull"
   "coverDataUrlThumbnail" "mi:coverDataUrlThumbnail"
   "coverUrl117" "mi:coverUrl117"
   "coverUrl207" "mi:coverUrl207"
   "coverUrl42" "mi:coverUrl42"
   "coverUrl500" "mi:coverUrl500"
   "coverUrlBack" "mi:coverUrlBack"
   "coverUrlFull" "mi:coverUrlFull"
   "coverUrlThumbnail" "mi:coverUrlThumbnail"
   "creatorActPeriod" "dc:creator"
   "creatorAnt" "dc:creator"
   "creatorArr" "dc:creator"
   "creatorArt" "dc:creator"
   "creatorAus" "dc:creator"
   "creatorAut" "dc:creator"
   "creatorCcp" "dc:creator"
   "creatorCmp" "dc:creator"
   "creatorCom" "dc:creator"
   "creatorCre" "dc:creator"
   "creatorDkbea" "dc:creator"
   "creatorDkbra" "dc:creator"
   "creatorDkbrm" "dc:creator"
   "creatorDkdes" "dc:creator"
   "creatorDkmed" "dc:creator"
   "creatorDkmon" "dc:creator"
   "creatorDkops" "dc:creator"
   "creatorDkved" "dc:creator"
   "creatorDrm" "dc:creator"
   "creatorDrt" "dc:creator"
   "creatorEdt" "dc:creator"
   "creator" "bd:creator"
   "dcCreator" "dc:creator"
   "creatorIll" "dc:creator"
   "creatorIve" "dc:creator"
   "creatorIvr" "dc:creator"
   "creatorLyr" "dc:creator"
   "creatorMus" "dc:creator"
   "creatorOth" "dc:creator"
   "creatorPht" "dc:creator"
   "creatorPROVIDERID" "dc:creator"
   "creatorScl" "dc:creator"
   "creatorSng" "dc:creator"
   "creatorSort" "dc:creator"
   "creatorTrl" "dc:creator"
   "creatorWdc" "dc:creator"
   "date" "dc:date"
   "description" "dc:description"
   "descriptionSeries" "dc:description"
   "discussedIn" "dbcaddi:discussedIn"
   "discusses" "dbcaddi:discusses"
   "extent" "dcterms:extent"
   "fedoraPid" "bd:fedoraPid"
   "format" "dc:format"
   "hasAdaptation" "dbcaddi:hasAdaptation"
   "hasAnalysis" "dbcaddi:hasAnalysis"
   "hasCreatorDescription" "dbcaddi:hasCreatorDescription"
   "hasDescriptionFromPublisher" "dbcaddi:hasDescriptionFromPublisher"
   "hasManuscript" "dbcaddi:hasManuscript"
   "hasOnlineAccess" "dbcaddi:hasOnlineAccess"
   "hasPart" "dcterms:hasPart"
   "hasPartTrack" "dcterms:hasPart"
   "hasReview" "dbcaddi:hasReview"
   "hasSoundtrack" "dbcaddi:hasSoundtrack"
   "acIdentifier" "ac:identifier"
   "pid" "bd:identifier"
   "identifier" "dc:identifier"
   "identifierISBN" "dc:identifier"
   "identifierISMN" "dc:identifier"
   "identifierISRC" "dc:identifier"
   "identifierISSN" "dc:identifier"
   "identifierPROVIDERID" "dc:identifier"
   "identifierPROVIDERMID" "dc:identifier"
   "identifierUPC" "dc:identifier"
   "identifierURI" "dc:identifier"
   "isAdaptationOf" "dbcaddi:isAdaptationOf"
   "isAnalysisOf" "dbcaddi:isAnalysisOf"
   "isDescriptionFromPublisherOf" "dbcaddi:isDescriptionFromPublisherOf"
   "isManuscriptOf" "dbcaddi:isManuscriptOf"
   "isPartOfAlbumId" "dcterms:isPartOf"
   "isPartOfAlbumTitle" "dcterms:isPartOf"
   "isPartOf" "dcterms:isPartOf"
   "isPartOfISBN" "dcterms:isPartOf"
   "isPartOfISSN" "dcterms:isPartOf"
   "isPartOfManifestation" "dbcbib:isPartOfManifestation"
   "isReplacedBy" "dcterms:isReplacedBy"
   "isReviewOf" "dbcaddi:isReviewOf"
   "isSoundtrackOfGame" "dbcaddi:isSoundtrackOfGame"
   "isSoundtrackOfMovie" "dbcaddi:isSoundtrackOfMovie"
   "language" "bd:language"
   "dcLanguage" "dc:language"
   "languageISO6392" "dc:language"
   "languageSpoken" "dc:language"
   "languageSubtitles" "dc:language"
   "multiVolumeType" "bd:multiVolumeType"
   "partOf" "bd:partOf"
   "publisher" "dc:publisher"
   "referencesISBN" "dcterms:references"
   "replaces" "dcterms:replaces"
   "rights" "dc:rights"
   "shelfMusicshelf" "dkdcplus:shelf"
   "acSource" "ac:source"
   "source" "dc:source"
   "spatialCoordinates" "dcterms:spatial"
   "spatialDBCF" "dcterms:spatial"
   "spatialDBCM" "dcterms:spatial"
   "spatialDBCS" "dcterms:spatial"
   "spatial" "dcterms:spatial"
   "subjectDBCF" "dc:subject"
   "subjectDBCM" "dc:subject"
   "subjectDBCN" "dc:subject"
   "subjectDBCO" "dc:subject"
   "subjectDBCS" "dc:subject"
   "subjectDK5" "dc:subject"
   "subjectDK5Text" "dc:subject"
   "subjectGenre" "dc:subject"
   "subject" "dc:subject"
   "subjectLCSH" "dc:subject"
   "subjectSort" "dc:subject"
   "temporalDBCM" "dcterms:temporal"
   "temporalDBCP" "dcterms:temporal"
   "temporal" "dcterms:temporal"
   "titleFull" "bd:titleFull"
   "dcTitleFull" "dc:title"
   "title" "bd:title"
   "dcTitle" "dc:title"
   "titleSeries" "dc:title"
   "typeBibDKType" "dc:type"
   "type" "bd:type"
   "version" "dkdcplus:version"
   "workType" "bd:workType"})

(defonce reverse-types
  {"Book bib:AudioBook" ["lydbog (bånd)" "lydbog (net)" "spolelydbånd" "lydbog" "lydbog (cd)" "lydbog (cd-mp3)"]
   "PublicationIssue bib:ComicIssue" ["tegneserie"]
   "Book bib:GraphicNovel" ["graphic novel"]
   "VideoGame" ["pc-spil" "playstation 3" "playstation 2" "xbox 360" "wii" "nintendo ds" "playstation 4" "wii u" "playstation" "xbox one" "pc-spil (net)" "xbox" "gameboy advance" "psp" "playstation vita" "html5-spil" "gameboy"]
   "SoftwareApplication" ["cd-rom" "dvd-rom" "diskette"] 
   "PublicationIssue" ["periodikum" "periodikum (net)" "tidsskrift (net)"]
   "Map" ["kort" "e-kort"] 
   "CreativeWorkSeries" ["avis" "avis (net)" "serie" "tidsskrift"]
   "Movie" ["dvd" "video" "blu-ray" "betamax" "film (net)" "biograffilm" "film"]
   "MusicComposition" ["node" "sang" "e-node"]
   "MusicAlbum" ["cd (musik)" "grammofonplade" "bånd" "musik (net)"]
   "CreativeWork" ["cd"  "cd-rom (mp3)" "netdokument" "sammensat materiale" "diverse" "grafisk blad" "legetøj" "mikroform" "udstilling" "spil" "dias" "teateropførelse" "plakat" "puslespil" "originalkunst" "genstand" "lydspor" "akvarel" "laborativt materiale" "transparent" "elektronisk materiale" "planche" "billedkort" "fotoreproduktion" "emnekasse" "foto" "dcc-bånd" "materiale til indlæringsapparat" "billedbånd" "ordkort" "tegning" "måleapparat" "flonellografmateriale" "teaterdukke" "cd-i" "postkort" "arkitekturtegning" "kunstreproduktion" "maleri" "flipover-materiale" "mini disc" "øvelsesmodel" "fastplade" "billedtæppe" "foto-cd" "teknisk tegning" "udstillingsmontage" "symbolkort"]
   "Article" ["avisartikel" "artikel" "tidsskriftsartikel"]
   "Book" ["årbog" "bog" "ebog" "billedbog" "punktskrift" "bog stor skrift"]})
(defonce types
  (into {}
        (apply concat
               (map (fn [[k vs]] (map (fn [v] [v k]) vs)) reverse-types))))

(def prop-types
  {:uncategorised
   ["date" "extent" "format" "abstract" "description" "descriptionSeries"
    "accessType" "activity" "alternative" "audienceAge" "audience"
    "audienceMedieraad" "audiencePegi" "collection" "collectionDetails"
    "version" "multiVolumeType" "publisher" "referencesISBN" "rights"
    "shelfMusicshelf" "acSource" "source" "typeBibDKType" "type" "workType"]
   :ignore ["creatorSort"]
   :cover-url
   ["coverUrlFull"]
   :language
   ["language" "dcLanguage" "languageISO6392" "languageSpoken" "languageSubtitles"]
   :contributor
   ["contributorAct" "contributorAft" "contributorAnm" "contributorAnt" "contributorArc"
    "contributorArr" "contributorArt" "contributorAud" "contributorAui" "contributorAus"
    "contributorAut" "contributorCcp" "contributorChr" "contributorClb" "contributorCli"
    "contributorCll" "contributorCmm" "contributorCmp" "contributorCnd" "contributorCng"
    "contributorCom" "contributorCre" "contributorCtb" "contributorCtg" "contributorCur"
    "contributorCwt" "contributorDkani" "contributorDkbea" "contributorDkdes"
    "contributorDkfig" "contributorDkfvl" "contributorDkind" "contributorDkmdt"
    "contributorDkmed" "contributorDkmon" "contributorDkops" "contributorDkref"
    "contributorDkste" "contributorDktek" "contributorDktil" "contributorDkved"
    "contributorDrm" "contributorDte" "contributorEdt" "contributorFmo" "contributorHnr"
    "contributor" "contributorIll" "contributorInv" "contributorIsb" "contributorItr"
    "contributorIve" "contributorIvr" "contributorLbt" "contributorLtg" "contributorLyr"
    "contributorMus" "contributorNrt" "contributorOrm" "contributorOth" "contributorPbl"
    "contributorPht" "contributorPrd" "contributorPrf" "contributorPro"
    "contributorPROVIDERID" "contributorPrt" "contributorRce" "contributorRes"
    "contributorRev" "contributorScl" "contributorSds" "contributorSng" "contributorSpk"
    "contributorStl" "contributorTrl" "contributorWdc"]
   :creator
   ["creatorActPeriod" "creatorAnt" "creatorArr" "creatorArt" "creatorAus" "creatorAut"
    "creatorCcp" "creatorCmp" "creatorCom" "creatorCre" "creatorDkbea" "creatorDkbra"
    "creatorDkbrm" "creatorDkdes" "creatorDkmed" "creatorDkmon" "creatorDkops"
    "creatorDkved" "creatorDrm" "creatorDrt" "creatorEdt" "creator" "dcCreator"
    "creatorIll" "creatorIve" "creatorIvr" "creatorLyr" "creatorMus" "creatorOth"
    "creatorPht" "creatorPROVIDERID" "creatorScl" "creatorSng" 
    "creatorTrl" "creatorWdc"]
   :id
   ["fedoraPid" "pid" "acIdentifier"
    "identifier" "identifierISBN" "identifierISMN" "identifierISRC" "identifierISSN"
    "identifierPROVIDERID" "identifierPROVIDERMID" "identifierUPC" "identifierURI"]
   :relation
   ["continuedIn" "continues" "discussedIn" "discusses"
    "hasAdaptation" "hasAnalysis" "hasCreatorDescription" "hasDescriptionFromPublisher"
    "hasManuscript" "hasOnlineAccess" "hasPart" "hasPartTrack" "hasReview" "hasSoundtrack"
    "isAdaptationOf" "isAnalysisOf" "isDescriptionFromPublisherOf" "isManuscriptOf"
    "isPartOfAlbumId" "isPartOfAlbumTitle" "isPartOf" "isPartOfISBN" "isPartOfISSN"
    "isPartOfManifestation" "isReplacedBy" "isReviewOf" "isSoundtrackOfGame"
    "isSoundtrackOfMovie"
    "partOf" "replaces"]
   :subject
   ["spatialCoordinates" "spatialDBCF" "spatialDBCM" "spatialDBCS" "spatial"
    "temporalDBCM" "temporalDBCP" "temporal"
    "subjectDBCF" "subjectDBCM" "subjectDBCN" "subjectDBCO" "subjectDBCS" "subjectDK5"
    "subjectDK5Text" "subjectGenre" "subject" "subjectLCSH" "subjectSort"]
   :title
   ["titleFull" "dcTitleFull" "title" "dcTitle" "titleSeries"]})


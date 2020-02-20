(ns chalice.json)

(defn clj->json [value]
  (.stringify js/JSON (clj->js value) nil 2))

(defn json->clj [value]
  (js->clj (.parse js/JSON value) :keywordize-keys true))
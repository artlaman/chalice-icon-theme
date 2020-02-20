(ns chalice.settings
  (:require
   [fs :refer [writeFileSync readFileSync]]
   [chalice.json :refer [json->clj clj->json]]))

(defn read! [file-name]
  (json->clj (readFileSync file-name)))

(defn write! [file-name settings]
  (writeFileSync file-name (clj->json settings)))

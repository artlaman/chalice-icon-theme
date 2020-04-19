(ns chalice.extension
  (:require [chalice.vscode :refer
             [affects-configuration get-config!
              on-config-change file-path-memo]]
            [chalice.settings :as settings]))

(defn sync-arrows! [context]
  (let [settings (settings/read! (file-path-memo context "chalice-icon-theme.json"))
        hidden-in-settings (:hidesExplorerArrows settings)
        hidden-in-config (not (get-config! "chaliceIcons" "showArrows" false))]
    (when (not= hidden-in-config hidden-in-settings)
      (settings/write!
       (file-path-memo context "chalice-icon-theme.json")
       (assoc settings :hidesExplorerArrows hidden-in-config)))))

(defn handle-arrows-section-change! [context]
  (fn [event]
    (when (affects-configuration event "chaliceIcons.showArrows")
      (sync-arrows! context))))

(defn activate
  [context]
  (on-config-change (handle-arrows-section-change! context))
  (sync-arrows! context))

(defn deactivate
  [])

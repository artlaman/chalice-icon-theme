(ns vscode.api)

(def vscode (js/require "vscode"))

(defn register-command!
  [id f]
  (.registerCommand (.. vscode -commands) id f))

(defn push-subscription!
  [context disp]
  (.push (.. context -subscriptions) disp))

(defn on-config-change
  [f]
  (.onDidChangeConfiguration (.. vscode -workspace) f))

(defn affects-configuration [event section]
  (.affectsConfiguration event section))

(defn info-message!
  [msg]
  (.showInformationMessage (.. vscode -window) msg))

(defn file-path [context file-name]
  (.asAbsolutePath context file-name))

(def file-path-memo
  (memoize file-path))

(defn get-config!
  ([path]
   (.. vscode -workspace (getConfiguration path)))
  ([conf key]
   (.. vscode -workspace (getConfiguration conf) (get key)))
  ([conf key default]
   (.. vscode -workspace (getConfiguration conf) (get key default))))
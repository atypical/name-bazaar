(ns user
  (:require [figwheel-sidecar.repl-api]))

(defn start-backend! []
  (figwheel-sidecar.repl-api/start-figwheel!
    (assoc-in (figwheel-sidecar.config/fetch-config)
              [:data :figwheel-options :server-port] 4541)
    "dev-backend")
  (figwheel-sidecar.repl-api/cljs-repl "dev-backend"))

(defn start-frontend! []
  (figwheel-sidecar.repl-api/start-figwheel!
    (figwheel-sidecar.config/fetch-config)
    "dev")
  (figwheel-sidecar.repl-api/cljs-repl "dev"))

(defn start-tests! []
  (figwheel-sidecar.repl-api/start-figwheel!
    (assoc-in (figwheel-sidecar.config/fetch-config)
              [:data :figwheel-options :server-port] 4543)
    "test-fig")
  (figwheel-sidecar.repl-api/cljs-repl "test-fig"))

(comment
  (start-frontend!)
  (start-backend!)
  (start-tests!))
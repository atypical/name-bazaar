(ns name-bazaar.contracts-api.offering-requests
  (:require
    [cljs.core.async :refer [<! >! chan]]
    [district0x.server.state :as state]
    [cljs-web3.eth :as web3-eth]
    [district0x.server.effects :as effects]
    [cljs-web3.async.eth :as web3-eth-async]
    [district0x.big-number :as bn]))

(defn on-request-added [server-state & args]
  (apply web3-eth/contract-call (state/instance server-state :offering-requests) :on-request-added args))

(defn on-requests-cleared [server-state & args]
  (apply web3-eth/contract-call (state/instance server-state :offering-requests) :on-requests-cleared args))

(defn on-new-requests [server-state & args]
  (apply web3-eth/contract-call (state/instance server-state :offering-requests) :on-new-requests args))

(defn add-request! [server-state {:keys [:offering-request/name]} opts]
  (effects/logged-contract-call! server-state
                                 (state/instance server-state :offering-requests)
                                 :add-request
                                 name
                                 (merge {:gas 1000000
                                         :from (state/active-address server-state)}
                                        opts)))

(defn requests-count [server-state {:keys [:offering-requests/node]}]
  (web3-eth-async/contract-call
    (chan 1 (map (fn [[err res]]
                   [err (bn/->number res)])))
    (state/instance server-state :offering-requests)
    :requests-count
    node))
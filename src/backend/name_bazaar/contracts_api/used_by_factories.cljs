(ns name-bazaar.contracts-api.used-by-factories
  (:require [district0x.server.state :as state]
            [cljs-web3.async.eth :as web3-eth]
            [district0x.server.effects :as effects]))

(defn set-factories! [server-state {:keys [:contract-key] :as opts}]
  (effects/logged-contract-call! server-state
                                 (state/instance contract-key)
                                 :set-factories
                                 [(state/contract-address server-state :instant-buy-offering-factory)
                                  (state/contract-address server-state :english-auction-offering-factory)]
                                 true
                                 (merge
                                   {:gas 100000
                                    :from (state/active-address server-state)}
                                   opts)))

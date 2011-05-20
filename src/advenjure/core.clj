(ns advenjure.core
  (:require [advenjure.player :as player])
  (:import [javax.swing JFrame JPanel]
           [java.awt.event MouseAdapter]))

(def frame (JFrame.))
(def panel (JPanel.))

(doto frame
  (.add panel)
  (.setSize 800 600)
  (.setVisible true))

(doto panel
  (.addMouseListener (proxy [MouseAdapter] []
                       (mouseClicked [ms-event]
                         (let [x (.getX ms-event)
                               y (.getY ms-event)]
                          (println "x: " x "y: " y)
                          (player/move-to x y)))))
  (.add player/component))

(defn activate! []
  (player/init)
  (loop []
    (Thread/sleep 1000/60) ;60 times per second
    (player/update)
    (.repaint frame)
    (recur)))

#_(.start (Thread. activate!))
(activate!)
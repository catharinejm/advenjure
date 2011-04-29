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
                          (.setBounds player/component x y 41 39.25)))))
  (.add player/component))

(.setBounds player/component 400 300 41 39.25)

(loop []
  (Thread/sleep 1000/60) ;60 times per second
  (.repaint frame)
  (recur))
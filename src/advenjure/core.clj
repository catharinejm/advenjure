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

(defn ^:dynamic click-fn [ms-event]
  (println "click"))

(doto panel
  (.addMouseListener (proxy [MouseAdapter] []
                       (mouseClicked [ms-event]
                         (click-fn ms-event))))
  (.add player/component))

(player/set-coords! 400 300)

(defn animate []
  ())

(binding [click-fn (fn [ms-event]
                       (let [x (.getX ms-event)
                             y (.getY ms-event)]
                         (println "x: " x "y: " y)
                         #_(player/set-coords! x y)))]
  (loop []
    (Thread/sleep 1000/60) ;; draw 60 times per second
    (animate)
    (.repaint frame)
    (recur)))
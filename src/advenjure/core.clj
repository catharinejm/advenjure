(ns advenjure.core
  (:require [advenjure.player :as player])
  (:import [javax.swing JFrame]))

(def frame (JFrame.))

(doto frame (.add player/component) (.setSize 800 600) (.setVisible true))

(loop []
  (Thread/sleep 1000/60) ;60 times per second
  (.repaint frame)
  (recur))
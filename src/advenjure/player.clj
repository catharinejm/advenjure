(ns advenjure.player
  (:use advenjure.sprite)
  (:import [javax.swing JComponent]))

(def tiles (load-tiles "resources/player.png" 2 4))

(defn sprite []
  (nth tiles
       (-> (System/currentTimeMillis)
	   (rem 2400)
	   (quot 300))))

(def component
  (proxy [JComponent] []
    (paint [gfx]
      (.drawImage gfx (sprite) 0 0 nil))))
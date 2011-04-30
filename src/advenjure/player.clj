(ns advenjure.player
  (:use advenjure.sprite)
  (:import [javax.swing JComponent]
           [java.awt.event MouseAdapter]))

(def tiles (load-tiles "resources/player.png" 2 4))

(defn sprite []
  (nth tiles
       (-> (System/currentTimeMillis)
	   (rem 2400)
	   (quot 300))))

(def component
  (let [jcmp (proxy [JComponent] []
                (paint [gfx]
                  (.drawImage gfx (sprite) 0 0 nil)))]
    (doto jcmp
      (.addMouseListener (proxy [MouseAdapter] []
                           (mouseClicked [ms-event]
                             (println "Inside the sprite!" (.getX ms-event) (.getY ms-event))))))))

(defn set-coords! [x y]
  (.setBounds component x y 41 39.25))
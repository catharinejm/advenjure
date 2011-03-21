(ns advenjure.core
  (:use advenjure.sprite)
  (:import [java.awt GraphicsEnvironment GraphicsDevice GraphicsConfiguration Graphics2D]
	   [javax.swing JWindow JPanel]))

(def gfx-env (GraphicsEnvironment/getLocalGraphicsEnvironment))
(def gfx-device (.getDefaultScreenDevice gfx-env))
(def gfx-config (.getDefaultConfiguration gfx-device))

(def window
  (let [tiles (load-tiles "resources/player.png" 2 4)
	pane (proxy [JPanel] [true]
	       (paint [gfx]
		      (.drawImage gfx
				  (nth tiles (-> (System/currentTimeMillis) (rem 2400) (quot 300)))
				  0 0 nil)))]
    (doto (JWindow. gfx-config) (.add pane) (.setSize 640 480))))

(defn draw-it []
  (.setFullScreenWindow gfx-device window)
  (Thread/sleep 2000)
  (.setFullScreenWindow gfx-device nil))
(ns advenjure.core
  (:use advenjure.sprite)
  (:import [java.awt GraphicsEnvironment GraphicsDevice GraphicsConfiguration Graphics2D]
	   [javax.swing JWindow JPanel]))

(def gfx-env (GraphicsEnvironment/getLocalGraphicsEnvironment))
(def gfx-device (.getDefaultScreenDevice gfx-env))
(def gfx-config (.getDefaultConfiguration gfx-device))
(def sprite (ImageIO/read (as-file "resources/player.png")))
(def window
  (proxy [JWindow] [gfx-config]
    (paint [gfx]
	   (.drawImage gfx
		       (ImageIO/read (as-file "resources/player.png"))
		       50 50 100 100 0 0 50 50 nil))))

(defn draw-it []
  (.setFullScreenWindow gfx-device window)
  (Thread/sleep 2000)
  (.setFullScreenWindow gfx-device nil))
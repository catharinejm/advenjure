(ns advenjure.core
  (:require [advenjure.sprite :as sprite])
  (:import [java.awt GraphicsEnvironment GraphicsDevice GraphicsConfiguration Graphics2D]
	   [javax.swing JFrame JPanel JWindow]))

(def gfx-env (GraphicsEnvironment/getLocalGraphicsEnvironment))
(def gfx-device (.getDefaultScreenDevice gfx-env))
(def gfx-config (.getDefaultConfiguration gfx-device))

(def player-tiles (sprite/load-tiles "resources/player.png" 2 4))

(defn player-sprite []
  (nth player-tiles
       (-> (System/currentTimeMillis)
	   (rem 2400)
	   (quot 300))))

(def pane
  (proxy [JPanel] []
    (paint [gfx]
	   (.drawImage gfx (player-sprite) 0 0 nil))))

(def frame (JFrame.))
(def window (JWindow.))

(doto frame (.add pane) (.setSize 640 480) (.setVisible true))
#_(doto window (.add pane))
#_(.setFullScreenWindow gfx-device window)

(loop []
  (Thread/sleep 1000/60)
  (.repaint pane)
  (recur))
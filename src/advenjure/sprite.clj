(ns advenjure.sprite
  (:use [clojure.java.io :only (as-file)])
  (:import [javax.imageio ImageIO]
	   [java.awt.image BufferedImage]))

(defn load-tiles [path cols rows]
  (let [img (ImageIO/read (as-file path))
	raster (.getData img)
	cm (.getColorModel img)
	premulti? (.isAlphaPremultiplied img)
	cwid (/ (.getWidth img)	cols)
	rht (/ (.getHeight img) rows)]
    (loop [x 0 y 0 tiles []]
      (if (>= y (.getHeight img))
	tiles
	(let [writable-raster (.. (.copyData img (.createCompatibleWritableRaster raster x y cwid rht))
				  (createTranslatedChild 0 0))
	      tile (BufferedImage. cm writable-raster premulti? nil)
	      x (-> x (+ cwid) (rem (.getWidth img)))
	      y (if (zero? x) (+ y rht) y)]
	  (recur x y (conj tiles tile)))))))

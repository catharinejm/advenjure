(ns advenjure.player
  (:use advenjure.sprite)
  (:import [javax.swing JComponent]
           [java.awt.event MouseAdapter]))

(def tiles (load-tiles "resources/player.png" 2 4))

(def width (.getWidth (first tiles)))
(def height (.getHeight (first tiles)))
(def ^{:private true} movement (atom {:x 400 :dx 0
                                      :y 300 :dy 0
                                      :xstep 0
                                      :ystep 0
                                      :step 1}))

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

(defn move-to [x y]
  (swap! movement (fn [m]
                    (let [dx (- x (:x m))
                          dy (- y (:y m))
                          dx+dy (+ (Math/abs dy) (Math/abs dx))
                          xstep (->> dx+dy
                                     (/ dx)
                                     (* (:step m)))
                          ystep (->> dx+dy
                                     (/ dy)
                                     (* (:step m)))]
                      (println "dest-x" x
                               "dest-y" y
                               "cur-x" (:x m)
                               "cur-y" (:y m)
                               "dx" dx
                               "dy" dy
                               "xstep" xstep
                               "ystep" ystep)
                      (assoc m
                        :x x :dx dx
                        :y y :dy dy
                        :xstep xstep
                        :ystep ystep)))))

(defn init []
  (.setBounds component 400 300 width height))

(defn update []
  (let [{:keys [xstep ystep]} @movement
        cur-x (.getX component)
        cur-y (.getY component)]
    (.setLocation component
                  (+ cur-x xstep)
                  (+ cur-y ystep))))
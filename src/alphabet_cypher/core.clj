(ns alphabet-cypher.core
 (:require [clojure.math]))

 (def alpha-str (atom "abcdefghijklmnopqrstuvwxyz"))
 (def secret-code "scones")
 (def msg "meetmebythetree")

 (defn foo
  "encode or decode a message using a secret key and cyphers."
  [x]
   (println x "Hello, World!"))

 (defn get-nth-char [s n]
  (str (.charAt s n)))

;; generate the row for the given first/root letter
 (defn gen-char-set [first-letter]
 (let [idx (.indexOf @alpha-str first-letter)]
  (str (subs @alpha-str idx) (subs @alpha-str 0 idx))))

;; find the substitution for the original character
 (defn encode-char [code-char orig-char]
  (let [idx (.indexOf @alpha-str code-char)
        char-set (gen-char-set orig-char)
        enc-char (get-nth-char char-set idx)]
   (str enc-char)))

;; encrypt the message using the secret code
(defn encrypt [message code]
 (let [msg-len (.length message)
       code-len (.length code)
       rpt-n-times  (inc (int (/ msg-len code-len)))
       rpt-code (apply str (repeat rpt-n-times code))
       placeholder "thisdon'tdoshit"
       p2 (str placeholder "some other stuff")]
  (for [i (range msg-len)]
   (encode-char (get-nth-char message i) (get-nth-char rpt-code i)))))

;;; main execution, show message, secret code, and the encoded string
(println "Message : " msg)
(println "Secret Code : " secret-code)
(println (encrypt msg secret-code))

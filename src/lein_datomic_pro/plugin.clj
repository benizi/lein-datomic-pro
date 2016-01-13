(ns lein-datomic-pro.plugin)

(def datomic-free 'com.datomic/datomic-free)
(def datomic-pro 'com.datomic/datomic-pro)

(defn- datomic-in?
  [deps]
  (some (fn [[artifact]] (= artifact datomic-free)) deps))

;; TODO: do something smarter here (e.g. use same version, or use a specific
;; version if the passed-in version is too old)
(defn- fix-version
  [version]
  "0.9.5344")

(defn- fix-artifact
  [deps]
  (mapv
   (fn [[artifact version :as maven-id]]
     (if (= artifact datomic-free)
       [datomic-pro (fix-version version)]
       maven-id))
   deps))

(defn- add-sql-drivers
  [deps]
  (conj deps '[org.postgresql/postgresql "9.4-1201-jdbc41"]))

(defn- print-notice
  [deps]
  (leiningen.core.main/warn "Converted from" datomic-free "to" datomic-pro)
  deps)

(defn- fix-datomic-deps
  [deps]
  (if (datomic-in? deps)
    (-> deps
        fix-artifact
        add-sql-drivers
        print-notice)
    deps))

(defn middleware
  "Expand com.datomic/datomic-free deps"
  [project & args]
  (update-in project [:dependencies] fix-datomic-deps))

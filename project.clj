(defn artifactory [path]
  {:url (str "https://contactsplus.jfrog.io/artifactory/" path)
   :sign-releases false})

(defproject fullcontact/full.http "1.0.11"
  :description "Async HTTP client and server on top of http-kit and core.async."
  :url "https://github.com/contactsplusapp/full.http"
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo}
  :repositories [["fullcontact" ~(artifactory "repo")]
                 ["releases" ~(artifactory "libs-release-local")]
                 ["snapshots" ~(artifactory "libs-snapshot-local")]]
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [javax.xml.bind/jaxb-api "2.4.0-b180830.0359"]
                 [org.clojure/core.async "0.7.559"]
                 [http-kit "2.6.0"]
                 [compojure "1.3.4" :exclusions [clj-time]]
                 [javax.servlet/servlet-api "2.5"]
                 [ring-cors "0.1.7"]
                 [fullcontact/camelsnake "0.9.0"]
                 [fullcontact/full.json "0.10.3"]
                 [fullcontact/full.metrics "0.13.1"]
                 [fullcontact/full.async "1.1.1"]
                 [fullcontact/full.core "1.1.3"]]
  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]
  :plugins [[lein-midje "3.1.3"]]
  :profiles {:dev {:dependencies [[midje "1.7.0"]]}})

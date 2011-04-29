#!/usr/bin/env bash

cd $(dirname $0)/..

java -cp lib/\*:src clojure.main src/advenjure/core.clj

#!/bin/bash
if [ $# -ne 2 -a $# -ne 3 ]; then
    echo "Incorrect number of input parameters: $#"
    exit 1
fi

IMP_CLASS="$1"
INPUT="$2"
OUTPUT="$3"
MAIN="com.ddamiani.codejam.Runner"
ROOT_DIR="$(readlink -f "$(dirname $0)/..")"
JAR="${ROOT_DIR}/target/codejam-solutions-0.0.1-SNAPSHOT.jar"

if [ ! -f "$JAR" ]; then
    echo "Jar file not found: $JAR"

    echo "Attempting to build it..."
    pushd "$ROOT_DIR"
    mvn clean package
    popd

    if [ ! -f "$JAR" ]; then
        echo "Jar is still not there. Exitting..."
        exit 1
    fi
fi

java -cp $JAR $MAIN $IMP_CLASS $INPUT $OUTPUT

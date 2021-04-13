#!/bin/sh

export ANTLR_JAR=antlr-4.9-complete.jar
export ANTLR_HOME=./tools/antlr/
#export JAVA_HOME=<TBD>

java -Xmx500M -jar $ANTLR_HOME$ANTLR_JAR -o ./src/main/java/dev/conductor/centra/domain/search/cql
 -Dlanguage=Java -package "dev.conductor.centra.domain.search.cql" -encoding UTF-8 -listener -visitor ./src/main/antlr4/dev/conductor/cql/cql.g4
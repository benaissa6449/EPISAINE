#!/bin/bash

cd ../../prototype-ing1

mvn clean compile
mvn package
mvn install
cd ihm-client
mvn exec:java

if [ $? -eq 0 ]; then
    echo "Compilation went successfully."
else
    echo "Error compiling file."
fi
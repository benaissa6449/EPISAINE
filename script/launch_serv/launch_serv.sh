#!/bin/bash

ssh episaine@172.31.252.163 "java -jar xmart-city-backend-1.0-SNAPSHOT-jar-with-dependencies.jar"

if [ $? -eq 0 ]; then
    echo "JAR file executed successfully on remote VM."
else
    echo "Error executing JAR file on remote VM."
fi
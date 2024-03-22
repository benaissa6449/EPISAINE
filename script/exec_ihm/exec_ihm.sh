#!/bin/bash

cd ../../prototype-ing1/ihm-client
mvn exec:java

if [ $? -eq 0 ]; then
    echo "Exec went successfully."
else
    echo "Error executing file."
fi
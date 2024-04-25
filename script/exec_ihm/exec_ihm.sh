#!/bin/bash

cd ../../prototype-ing1/demo194
mvn javafx:run

if [ $? -eq 0 ]; then
    echo "Exec went successfully."
else
    echo "Error executing file."
fi
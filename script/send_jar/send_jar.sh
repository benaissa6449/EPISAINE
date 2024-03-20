#!/bin/bash

cd ../../prototype-ing1/xmart-city-backend/target/
scp xmart-city-backend-1.0-SNAPSHOT-jar-with-dependencies.jar episaine@172.31.252.163:xmart-city-backend-1.0-SNAPSHOT-jar-with-dependencies.jar

if [ $? -eq 0 ]; then
    echo "File sent successfully."
else
    echo "Error sending file."
fi
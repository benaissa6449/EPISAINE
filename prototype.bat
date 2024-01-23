@echo off
set local EnableDelayedExpansion

rem List of module names
set "modules = logging-utils rdbms-connection-pool xmart-city-backend xmart-city-business-dto xmart-city-client-commons xmart-city-commons xmart-insert-client xmart-select-client"

rem Iterates through each module
for %%i in (%modules%) do (
    cd %%i\target
    java -jar %%i.jar
    cd ..\..
)
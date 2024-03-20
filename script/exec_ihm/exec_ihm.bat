@echo off
cd ../../prototype-ing1/ihm-episaine
call mvn exec:java

if %errorlevel% equ 0 (
    echo Exec went successfully.
) else (
    echo Error executing file.
)
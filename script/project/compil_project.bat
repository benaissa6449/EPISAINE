@echo off
cd ../../prototype-ing1
call mvn clean compile
call mvn package
call mvn install
cd demo194
call mvn javafx:run

if %errorlevel% equ 0 (
    echo Compilation went successfully.
) else (
    echo Error compiling file.
)
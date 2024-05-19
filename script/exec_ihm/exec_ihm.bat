@echo
cd ../../prototype-ing1/demo194
call mvn javafx:run

if %errorlevel% equ 0 (
    echo Exec went successfully.
) else (
    echo Error executing file.
)
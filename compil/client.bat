cd ../prototype-ing1
call mvn clean compile
call mvn package
call mvn install
cd ihm-episaine
call mvn exec:java
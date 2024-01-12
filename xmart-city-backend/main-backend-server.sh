# depends on M2_REPO env. variable.
m2=${M2_REPO}
clsspth=src/main/resources
# clsspth+=:resources
# clsspth+=:etc

# mvn dependency:build-classpath | tail -8 | head -1 |\
#	sed -e 's/\/home\/mylogin\/\.m2\/repository/xxxxxx/g'|\
#	 sed -e 's/:/\n/g' | sed -e 's/xxxxxx/clsspth+=:${m2}/g'
# find . -regex ".*\.jar$" -exec sudo cp {} /etc/mc-service-config/lib/ \;

#jarfiles=$(find . -regex ".*\.jar$")
#for jarfile in $jarfiles; do
#    clsspth=${clsspth}:${jarfile}
#    echo "Adding $jarfile"
#done

#clsspth+=${clsspth}:${m2}/org/yaml/snakeyaml/1.21/snakeyaml-1.21.jar
#clsspth+=${clsspth}:${m2}/ch/qos/logback/logback-classic/1.1.7/logback-classic-1.1.7.jar
#clsspth+=${clsspth}:${m2}/ch/qos/logback/logback-core/1.1.7/logback-core-1.1.7.jar
#clsspth+=${clsspth}:${m2}/org/slf4j/slf4j-api/1.7.20/slf4j-api-1.7.20.jar
clsspth+=${clsspth}:${m2}/edu/ezip/ing1/pds/xmart-zity-backend/1.0-SNAPSHOT/xmart-zity-backend-1.0-SNAPSHOT-jar-with-dependencies.jar
#clsspth+=${clsspth}:${m2}/com/fasterxml/jackson/core/jackson-databind/2.9.8/jackson-databind-2.9.8.jar
#clsspth+=${clsspth}:${m2}/com/fasterxml/jackson/core/jackson-annotations/2.9.0/jackson-annotations-2.9.0.jar
#clsspth+=${clsspth}:${m2}/com/fasterxml/jackson/core/jackson-core/2.9.8/jackson-core-2.9.8.jar
#clsspth+=${clsspth}:${m2}/edu/ezip/commons/rdbms-connection-pool/1.0-SNAPSHOT/rdbms-connection-pool-1.0-SNAPSHOT.jar
#clsspth+=${clsspth}:${m2}/org/postgresql/postgresql/42.2.9/postgresql-42.2.9.jar
#clsspth+=${clsspth}:${m2}/edu/ezip/ing1/pds/commons/xmart-city-commons/1.0-SNAPSHOT/xmart-city-commons-1.0-SNAPSHOT.jar

exec java  -cp ${clsspth} edu.ezip.ing1.pds.backend.MainBackendServer

# depends on M2_REPO env. variable.
m2=${M2_REPO}
clsspth=src/main/resources

clsspth+=${clsspth}:${m2}/org/yaml/snakeyaml/1.21/snakeyaml-1.21.jar
clsspth+=${clsspth}:${m2}/org/postgresql/postgresql/42.2.9/postgresql-42.2.9.jar
clsspth+=${clsspth}:${m2}/edu/ezip/commons/rdbms-connection-pool/1.0-SNAPSHOT/rdbms-connection-pool-1.0-SNAPSHOT.jar
clsspth+=${clsspth}:${m2}/ch/qos/logback/logback-classic/1.1.7/logback-classic-1.1.7.jar
clsspth+=${clsspth}:${m2}/ch/qos/logback/logback-core/1.1.7/logback-core-1.1.7.jar
clsspth+=${clsspth}:${m2}/org/slf4j/slf4j-api/1.7.20/slf4j-api-1.7.20.jar

exec java -cp ${clsspth} edu.ezip.commons.connectionpool.config.ExperimentConnectionImpl


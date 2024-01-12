# depends on M2_REPO env. variable.
m2=${M2_REPO}
clsspth=src/main/resources

clsspth+=${clsspth}:${m2}/edu/ezip/ing1/pds/client/xmart-select-client/1.0-SNAPSHOT/xmart-select-client-1.0-SNAPSHOT-jar-with-dependencies.jar

exec java  -cp ${clsspth} edu.ezip.ing1.pds.client.MainSelectClient

package edu.ezip.commons.connectionpool.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

//      sudo -i -u postgres
//      psql -c "alter user pgil with password 'ezipspirit'"

public class DatabaseConnectionBasicConfiguration {
    private String  databaseName;
    private String  host;
    private String  username;
    private String  password;
    private short   port;
    private short   poolSize;
    public static DatabaseConnectionBasicConfiguration inst = null;
    private final static String dbConfigDefaultFileName = "db-config.yaml";
    private final static String LoggingLabel = "C o n n - p o o l";

    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);
    public static DatabaseConnectionBasicConfiguration getInstance() {
        if(inst == null) {
            new DatabaseConnectionBasicConfiguration(true);
        }
        return inst;
    }

    private  DatabaseConnectionBasicConfiguration(final boolean t) {
        final Yaml yaml = new Yaml(new Constructor(DatabaseConnectionBasicConfiguration.class));
        final InputStream nptStrm = this.getClass().getClassLoader().getResourceAsStream(dbConfigDefaultFileName);
        logger.debug("Load config file : {}", dbConfigDefaultFileName);
        inst = yaml.load(nptStrm);
        logger.debug("Configuration loaded : {}", inst.toString());
    }
    private DatabaseConnectionBasicConfiguration() {
    }
    public String getDatabaseName() {
        return databaseName;
    }
    public String getHost() {
        return host;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public short getPort() {
        return port;
    }
    public short getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(short poolSize) {
        this.poolSize = poolSize;
    }
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPort(short port) {
        this.port = port;
    }
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "DatabaseConnectionBasicConfiguration{" +
                "databaseName='" + databaseName + '\'' +
                ", host='" + host + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", port=" + port +
                ", poolSize=" + poolSize +
                '}';
    }
}

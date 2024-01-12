package edu.ezip.commons.connectionpool.config;

import edu.ezip.commons.connectionpool.config.impl.ConnectionPoolImpl;

import java.sql.SQLException;

public class ExperimentConnectionImpl {
    public static void main(String[] args) throws SQLException {
        ConnectionPoolImpl.getInstance("postgresql");
    }
}

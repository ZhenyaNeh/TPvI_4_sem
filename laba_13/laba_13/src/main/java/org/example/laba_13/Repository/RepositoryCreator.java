package org.example.laba_13.Repository;
import org.example.laba_13.Connection.ConnectionPool;

import java.sql.Connection;

public class RepositoryCreator implements AutoCloseable  {
    private ConnectionPool connectionPool;
    private Connection connection;
    public RepositoryCreator() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }
    public UserRepository getUserRepository() {
        return new UserRepository(connection);
    }
    public UnivClassRepository getClassRepository() {
        return new UnivClassRepository(connection);
    }
    @Override
    public void close() {
        connectionPool.releaseConnection(connection);
    }

}

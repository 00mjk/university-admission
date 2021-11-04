package com.solvd.university.multithreading;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static final int DEFAULT_INITIAL_POOL_SIZE = 10;

    private static volatile ConnectionPool connectionPool;
    private static volatile List<Connection> availableConnections;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return getInstance(DEFAULT_INITIAL_POOL_SIZE);
    }

    public static ConnectionPool getInstance(Integer initialPoolSize) {
        if (connectionPool == null && initialPoolSize != null) {
            synchronized (ConnectionPool.class) {
                if (connectionPool == null) {
                    connectionPool = new ConnectionPool();
                    availableConnections = new ArrayList<>(initialPoolSize);
                    for (int i = 0; i < initialPoolSize; i++) {
                        availableConnections.add(new Connection("connection-" + i));
                    }
                }
            }
        }
        return connectionPool;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        while (availableConnections.size() <= 0) {
        }
        connection = availableConnections.remove(0);
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (ConnectionPool.class) {
                if (availableConnections.contains(connection)) {
                    throw new RuntimeException("Try to release a non-existing connection");
                }
                availableConnections.add(connection);
            }
        } else {
            throw new RuntimeException("Connection param in #releaseConnection() is null");
        }
    }
}

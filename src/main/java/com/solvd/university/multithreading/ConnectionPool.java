package com.solvd.university.multithreading;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static final int INITIAL_POOL_SIZE = 5;
    private static final List<Connection> availableConnections = new ArrayList<>(INITIAL_POOL_SIZE);

    private static volatile ConnectionPool connectionPool;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            synchronized (ConnectionPool.class) {
                if (connectionPool == null) {
                    connectionPool = new ConnectionPool();
                    for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
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

    public synchronized void releaseConnection(Connection connection) {
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

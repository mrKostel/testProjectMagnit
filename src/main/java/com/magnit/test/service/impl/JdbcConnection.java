package com.magnit.test.service.impl;

import com.magnit.test.type.DbType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class JdbcConnection {
    private static final Logger log = Logger.getLogger(JdbcConnection.class.getName());
    private static Connection connection;

    private JdbcConnection() {
    }

    public static Connection buildConnection(String url, String login, String password, DbType type) throws Exception {
        Class.forName(type.getDriver());
        connection = DriverManager.getConnection(url, login, password);
        log.info("Created connection");
        return connection;
    }
}

package org.zerock.w1.todo.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public enum ConnectionUtil {

    INSTANCE;

    private final HikariDataSource ds;

    ConnectionUtil() {
        HikariConfig config = makeHikariConfig();
        ds = new HikariDataSource(config);
    }

    private static HikariConfig makeHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/w1db");
        config.setUsername("w1user");
        config.setPassword("w1user");
        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", 250);
        config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        return config;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}

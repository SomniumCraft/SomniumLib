package ru.somniumcraft.somniumlib.Database.Connector;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLConnector extends AbstractSQLConnector {

    public MySQLConnector(String host, String port, String database, String user, String password){
        super(true);
        super.host = host;
        super.port = port;
        super.database = database;
        super.user = user;
        super.password = password;
        databaseType = "mysql";

        configure();
    }

    @Override
    protected void configure() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://"+host+":"+port+"/"+database);
        config.setUsername(user);
        config.setPassword(password);
        config.setConnectionTestQuery("SELECT 1");
        config.addDataSourceProperty("connectionTimeout", "10000");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");

        this.dataSource = new HikariDataSource(config);
    }
}

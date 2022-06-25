package ru.somniumcraft.somniumlib.Database.Connector;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class SQLiteConnector extends AbstractSQLConnector{

    public SQLiteConnector(String databasePath, String database, String user, String password){
        super(true);
        super.host = databasePath;
        super.database = database;
        super.user = user;
        super.password = password;
        databaseType = "sqlite";
    }

    @Override
    protected void configure() {
        HikariConfig config = new HikariConfig();
        config.setPoolName("AuthMeSQLitePool");
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:" + host + "/" + database + ".db");
        config.setConnectionTestQuery("SELECT 1");
        config.setMaxLifetime(60000); // 60 Sec
        config.setIdleTimeout(45000); // 45 Sec
        config.setMaximumPoolSize(50); // 50 Connections (including idle connections)
        this.dataSource = new HikariDataSource(config);
    }
}

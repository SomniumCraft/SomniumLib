package ru.somniumcraft.somniumlib.Database.Connector;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractSQLConnector implements IDatabaseConnector{

    public final boolean needConnection;
    protected String host;
    protected String port;
    protected String database;
    protected String user;
    protected String password;
    @Getter
    protected HikariDataSource dataSource;

    protected String databaseType;

    public AbstractSQLConnector(boolean needConnection) {
        this.needConnection = needConnection;
    }

    protected abstract void configure();

    @Override
    public void disconnect(Connection connection){
        if (connection == null)
            return;
        try {
            if (!connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeResources(ResultSet resultSet, PreparedStatement preparedStatement) {
        try {
            if (resultSet != null)
                resultSet.close();
            if (preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection(){
        Connection connection = null;

        try{
            connection = dataSource.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }

        return connection;
    }

    @Override
    public String getDatabaseType() {
        return databaseType;
    }
}

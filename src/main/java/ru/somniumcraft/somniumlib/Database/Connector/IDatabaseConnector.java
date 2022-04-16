package ru.somniumcraft.somniumlib.Database.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface IDatabaseConnector {

    Connection getConnection();

    void disconnect(Connection connection);

    void closeResources(ResultSet resultSet, PreparedStatement preparedStatement);

    String getDatabaseType();

}

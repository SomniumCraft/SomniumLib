package ru.somniumcraft.somniumlib.Database.Data.Util;

import ru.somniumcraft.somniumlib.Database.Connector.IDatabaseConnector;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

public class PluginDataHolder {

    // connector pri
    private final IDatabaseConnector connector;
    private final SomniumLib plugin;

    public PluginDataHolder(IDatabaseConnector connector) {
        plugin = SomniumLib.getInstance();
        this.connector = connector;
    }

    protected Connection getConnection() throws SQLException {
        return connector.getConnection();
    }

    protected void logSQLError(String message, SQLException ex) {
        logSQLError(Level.SEVERE, message, ex);
    }
    protected void logSQLError(Level level, String message, SQLException ex) {
        if (level.intValue() < Level.INFO.intValue()) {
            level = Level.INFO;
        }

        plugin.getLogger().log(
                level, String.format("%s%nMessage: %s%nCode: %s%nState: %s",
                        message, ex.getMessage(), ex.getErrorCode(), ex.getSQLState()), ex);
    }


}

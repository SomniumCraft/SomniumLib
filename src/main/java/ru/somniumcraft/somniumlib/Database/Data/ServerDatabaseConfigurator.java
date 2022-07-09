package ru.somniumcraft.somniumlib.Database.Data;

import ru.somniumcraft.somniumlib.Database.Connector.IDatabaseConnector;
import ru.somniumcraft.somniumlib.Database.Data.Util.DatabaseConfigurator;

import java.io.IOException;
import java.sql.SQLException;

public class ServerDatabaseConfigurator extends DatabaseConfigurator {
    public ServerDatabaseConfigurator() {
        super("mysql/ServerDatabase");
    }

    @Override
    protected void loadPatches(IDatabaseConnector connector) throws IOException, SQLException {

    }
}

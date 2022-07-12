package ru.somniumcraft.somniumlib;

import lombok.Getter;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import ru.somniumcraft.somniumlib.BasePlugin.SomniumPlugin;
import ru.somniumcraft.somniumlib.CommandManager.CommandRegister;
import ru.somniumcraft.somniumlib.Config.ServerDatabaseConfig;
import ru.somniumcraft.somniumlib.Config.SharedConfig;
import ru.somniumcraft.somniumlib.Config.SharedDatabaseConfig;
import ru.somniumcraft.somniumlib.Database.Caching.SharedPlayerDTOCache;
import ru.somniumcraft.somniumlib.Database.Connector.AbstractSQLConnector;
import ru.somniumcraft.somniumlib.Database.Connector.IDatabaseConnector;
import ru.somniumcraft.somniumlib.Database.Connector.MySQLConnector;
import ru.somniumcraft.somniumlib.Database.Data.ServerDatabaseConfigurator;
import ru.somniumcraft.somniumlib.Database.Data.SharedDatabaseConfigurator;
import ru.somniumcraft.somniumlib.Database.Data.Util.DatabaseConfigurator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

public class SomniumLib extends SomniumPlugin {
    private static SomniumLib instanse;

    @Getter
    private SharedConfig sharedConfig;

    @Getter
    private SharedDatabaseConfig sharedDatabaseConfig;

    @Getter
    private ServerDatabaseConfig serverDatabaseConfig;

    @Getter
    private AbstractSQLConnector sharedDatabaseConnector;

    @Getter
    private AbstractSQLConnector serverDatabaseConnector;

    @Getter
    private SharedPlayerDTOCache playerDTOCache;

    private CommandRegister commandRegister;

    public static SomniumLib getInstance() {
        return instanse;
    }

    @Override
    public void Load() {
        instanse = this;
    }

    @Override
    public void Enable() {

        sharedDatabaseConnector = new MySQLConnector(
                sharedDatabaseConfig.getHost(),
                sharedDatabaseConfig.getPort(),
                sharedDatabaseConfig.getDatabase(),
                sharedDatabaseConfig.getUser(),
                sharedDatabaseConfig.getPassword());

        serverDatabaseConnector = new MySQLConnector(
                serverDatabaseConfig.getHost(),
                serverDatabaseConfig.getPort(),
                serverDatabaseConfig.getDatabase(),
                serverDatabaseConfig.getUser(),
                serverDatabaseConfig.getPassword());

        configureDatabase(new SharedDatabaseConfigurator() ,sharedDatabaseConnector);
        configureDatabase(new ServerDatabaseConfigurator() ,serverDatabaseConnector);

        var abob = DSL.using(sharedDatabaseConnector.getDataSource(), SQLDialect.MYSQL);
        var bobab = DSL.using(serverDatabaseConnector.getDataSource(), SQLDialect.MYSQL);


        commandRegister = new CommandRegister();
        commandRegister.register();

        playerDTOCache = new SharedPlayerDTOCache();
        playerDTOCache.loadData();
    }

    @Override
    public void Disable() {
    }

    private void configureDatabase(DatabaseConfigurator configurator, IDatabaseConnector connector) {
        try {
            configurator.setup(connector);
        } catch (SQLException | IOException throwables) {
            this.getLogger().log(Level.SEVERE, "Error while configuring database " + configurator.getClass().getName(), throwables);
        }
    }
}

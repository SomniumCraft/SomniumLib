package ru.somniumcraft.somniumlib;

import lombok.Getter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.somniumcraft.somniumlib.BasePlugin.SomniumPlugin;
import ru.somniumcraft.somniumlib.CommandManager.CommandRegister;
import ru.somniumcraft.somniumlib.Config.SharedConfig;
import ru.somniumcraft.somniumlib.Config.SharedDatabaseConfig;
import ru.somniumcraft.somniumlib.Database.Caching.PlayerDTOCache;
import ru.somniumcraft.somniumlib.Database.Connector.IDatabaseConnector;
import ru.somniumcraft.somniumlib.Database.Connector.MySQLConnector;
import ru.somniumcraft.somniumlib.Database.Data.SharedDatabase;
import ru.somniumcraft.somniumlib.Util.*;

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
    private IDatabaseConnector databaseConnector;

    @Getter
    private PlayerDTOCache playerDTOCache;

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

        databaseConnector = new MySQLConnector(
                sharedDatabaseConfig.getHost(),
                sharedDatabaseConfig.getPort(),
                sharedDatabaseConfig.getDatabase(),
                sharedDatabaseConfig.getUser(),
                sharedDatabaseConfig.getPassword());

        configureSharedDatabase();

        commandRegister = new CommandRegister();
        commandRegister.register();

        playerDTOCache = new PlayerDTOCache();
        playerDTOCache.loadData();
    }

    @Override
    public void Disable() {
    }

    private void configureSharedDatabase() {
        SharedDatabase sharedDatabase = new SharedDatabase();
        try {
            sharedDatabase.setup(databaseConnector);
        } catch (SQLException throwables) {
            this.getLogger().log(Level.SEVERE, "Error while configuring shared database", throwables);
        } catch (IOException exception) {
            this.getLogger().log(Level.SEVERE, "Error while configuring shared database", exception);
        }
    }
}

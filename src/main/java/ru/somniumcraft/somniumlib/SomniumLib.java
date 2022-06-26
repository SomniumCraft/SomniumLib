package ru.somniumcraft.somniumlib;

import lombok.Getter;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.somniumcraft.somniumlib.BasePlugin.SomniumPlugin;
import ru.somniumcraft.somniumlib.Config.SharedConfig;
import ru.somniumcraft.somniumlib.Config.SharedDatabaseConfig;
import ru.somniumcraft.somniumlib.Database.Caching.PlayerDTOCache;
import ru.somniumcraft.somniumlib.Database.Connector.IDatabaseConnector;
import ru.somniumcraft.somniumlib.Database.Connector.MySQLConnector;
import ru.somniumcraft.somniumlib.Database.Connector.SharedDatabase;
import ru.somniumcraft.somniumlib.Util.*;

import java.sql.Connection;
import java.sql.Statement;

public class SomniumLib extends SomniumPlugin implements Listener {
    private static SomniumLib instanse;

    @Getter
    private SharedConfig sharedConfig;

    @Getter
    private SharedDatabaseConfig sharedDatabaseConfig;

    @Getter
    private IDatabaseConnector databaseConnector;

    private PlayerDTOCache playerDTOCache;

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

        databaseConnector.getConnection();

        playerDTOCache = new PlayerDTOCache();

        // register onPlayerJoin event
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void Disable() {
    }

    private void configureSharedDatabase() {
        SharedDatabase sharedDatabase = new SharedDatabase();

        try (Connection connection = databaseConnector.getConnection()) {

            connection.setAutoCommit(false);

            try (Statement statement = connection.createStatement()) {

                for (String sql : sharedDatabase.getSqlStatements()) {
                    statement.executeQuery(sql);
                }

            } catch (Exception e) {
                connection.rollback();
                connection.setAutoCommit(true);
                e.printStackTrace();
            }

            connection.commit();
            connection.setAutoCommit(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {
        MessageUtils.sendChatMessage(playerDTOCache.getPlayer(event.getPlayer().getUniqueId().toString()).getJoinMessage(), event.getPlayer());
    }

}

package ru.somniumcraft.somniumlib.Database.Data;

import org.bukkit.entity.Player;
import ru.somniumcraft.somniumlib.Database.Data.Async.BukkitAsyncAction;
import ru.somniumcraft.somniumlib.Database.Data.Objects.PlayerDTO;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.util.List;
import java.util.Optional;

public class AsyncWrappedPlayerData {

    private final PlayerData delegate;
    private final SomniumLib plugin;

    public AsyncWrappedPlayerData() {
        plugin = SomniumLib.getInstance();
        delegate = new PlayerData();
    }

    public BukkitAsyncAction<Optional<List<PlayerDTO>>> getPlayers() {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.getPlayers());
    }

    public BukkitAsyncAction<Optional<PlayerDTO>> getPlayer(String uuid) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.getPlayer(uuid));
    }

    public BukkitAsyncAction<Boolean> updatePlayer(PlayerDTO player) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayer(player));
    }

    public BukkitAsyncAction<Boolean> createPlayer(PlayerDTO player) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.createPlayer(player));
    }

    public BukkitAsyncAction<Boolean> updateJoinMessage(String uuid, String message) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updateJoinMessage(uuid, message));
    }

    public BukkitAsyncAction<Boolean> updateLeaveMessage(String uuid, String message) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updateLeaveMessage(uuid, message));
    }

}

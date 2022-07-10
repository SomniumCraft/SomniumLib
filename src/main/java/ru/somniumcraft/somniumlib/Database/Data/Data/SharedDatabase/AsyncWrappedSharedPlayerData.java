package ru.somniumcraft.somniumlib.Database.Data.Data.SharedDatabase;

import ru.somniumcraft.somniumlib.Database.Data.Async.BukkitAsyncAction;
import ru.somniumcraft.somniumlib.Database.Data.Objects.SharedDatabase.SharedPlayerDTO;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.util.List;
import java.util.Optional;

public class AsyncWrappedSharedPlayerData {

    private final SharedPlayerData delegate;
    private final SomniumLib plugin;

    public AsyncWrappedSharedPlayerData() {
        plugin = SomniumLib.getInstance();
        delegate = new SharedPlayerData();
    }

    public BukkitAsyncAction<Optional<List<SharedPlayerDTO>>> getPlayers() {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.getPlayers());
    }

    public BukkitAsyncAction<Optional<SharedPlayerDTO>> getPlayer(String uuid) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.getPlayer(uuid));
    }

    public BukkitAsyncAction<Boolean> updatePlayer(SharedPlayerDTO player) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayer(player));
    }

    public BukkitAsyncAction<Boolean> createPlayer(SharedPlayerDTO player) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.createPlayer(player));
    }

    public BukkitAsyncAction<Boolean> updateJoinMessage(String uuid, String message) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updateJoinMessage(uuid, message));
    }

    public BukkitAsyncAction<Boolean> updateLeaveMessage(String uuid, String message) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updateLeaveMessage(uuid, message));
    }

}

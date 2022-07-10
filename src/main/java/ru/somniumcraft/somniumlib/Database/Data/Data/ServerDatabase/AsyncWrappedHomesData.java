package ru.somniumcraft.somniumlib.Database.Data.Data.ServerDatabase;

import ru.somniumcraft.somniumlib.Database.Data.Async.BukkitAsyncAction;
import ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase.HomesDTO;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.util.List;
import java.util.Optional;

public class AsyncWrappedHomesData {

    private final HomesData delegate;
    private final SomniumLib plugin;

    public AsyncWrappedHomesData() {
        plugin = SomniumLib.getInstance();
        delegate = new HomesData();
    }

    // getHomes

    public BukkitAsyncAction<Optional<List<HomesDTO>>> getHomes(String uuid) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.getPlayerHomes(uuid));
    }

    // getHome

    public BukkitAsyncAction<Optional<HomesDTO>> getHome(String uuid, String name) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.getPlayerHome(uuid, name));
    }

    // createHome

    public BukkitAsyncAction<Boolean> createHome(HomesDTO home) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.createHome(home));
    }

    // updateHome

    public BukkitAsyncAction<Boolean> updateHome(HomesDTO home) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerHome(home));
    }

    // deleteHome

    public BukkitAsyncAction<Boolean> deleteHome(String uuid, String name) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.deletePlayerHome(uuid, name));
    }

}

package ru.somniumcraft.somniumlib.Database.Data.Data.ServerDatabase;

import org.bukkit.Location;
import ru.somniumcraft.somniumlib.Database.Data.Async.BukkitAsyncAction;
import ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase.PlayerDTO;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class AsyncWrappedPlayerData {


    private final PlayerData delegate;
    private final SomniumLib plugin;

    public AsyncWrappedPlayerData() {
        plugin = SomniumLib.getInstance();
        delegate = new PlayerData();
    }

    public BukkitAsyncAction<Optional<PlayerDTO>> getPlayer(String uuid) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.getPlayer(uuid));
    }

    public BukkitAsyncAction<Optional<List<PlayerDTO>>> getPlayers() {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.getPlayers());
    }

    public BukkitAsyncAction<Boolean> createPlayer(PlayerDTO player) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.createPlayer(player));
    }

    public BukkitAsyncAction<Boolean> updatePlayer(PlayerDTO player) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayer(player));
    }

    public BukkitAsyncAction<Boolean> updatePlayerBalance(String uuid, BigDecimal balance) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerBalance(uuid, balance));
    }

    public BukkitAsyncAction<Boolean> updatePlayerFlytime(String uuid, long flytime) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerFlytime(uuid, flytime));
    }

    public BukkitAsyncAction<Boolean> updatePlayerLastFlyTimeAdded(String uuid, long lastFlyTimeAdded) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerLastFlyTimeAdded(uuid, lastFlyTimeAdded));
    }

    public BukkitAsyncAction<Boolean> updatePlayerLastKeepInventory(String uuid, long lastKeepInventory) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerLastKeepInventory(uuid, lastKeepInventory));
    }

    public BukkitAsyncAction<Boolean> updatePlayerLoginTime(String uuid, long loginTime) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerLoginTime(uuid, loginTime));
    }

    public BukkitAsyncAction<Boolean> updatePlayerTeleportInfo(String uuid, Location location, Long timestamp) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerTeleportInfo(uuid, location, timestamp));
    }

    public BukkitAsyncAction<Boolean> updatePlayerDeathInfo(String uuid, Location location, Long timestamp) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerDeathInfo(uuid, location, timestamp));
    }

    public BukkitAsyncAction<Boolean> updatePlayerLogoutInfo(String uuid, Location location, Long timestamp) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerLogoutInfo(uuid, location, timestamp));
    }

    public BukkitAsyncAction<Boolean> updatePlayerPlayTime(String uuid, long playTime) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerPlayTime(uuid, playTime));
    }

    public BukkitAsyncAction<Boolean> updatePlayerBookEnchantedWithoutMending(String uuid, int bookEnchantedWithoutMending) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerBookEnchantedWithoutMending(uuid, bookEnchantedWithoutMending));
    }

    public BukkitAsyncAction<Boolean> updatePlayerWoodenItemsEnchantedWithoutMending(String uuid, int woodenItemsEnchantedWithoutMending) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerWoodenItemsEnchantedWithoutMending(uuid, woodenItemsEnchantedWithoutMending));
    }

    public BukkitAsyncAction<Boolean> updatePlayerStoneItemsEnchantedWithoutMending(String uuid, int stoneItemsEnchantedWithoutMending) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerStoneItemsEnchantedWithoutMending(uuid, stoneItemsEnchantedWithoutMending));
    }

    public BukkitAsyncAction<Boolean> updatePlayerIronItemsEnchantedWithoutMending(String uuid, int ironItemsEnchantedWithoutMending) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerIronItemsEnchantedWithoutMending(uuid, ironItemsEnchantedWithoutMending));
    }

    public BukkitAsyncAction<Boolean> updatePlayerGoldenItemsEnchantedWithoutMending(String uuid, int goldenItemsEnchantedWithoutMending) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerGoldenItemsEnchantedWithoutMending(uuid, goldenItemsEnchantedWithoutMending));
    }

    public BukkitAsyncAction<Boolean> updatePlayerDiamondItemsEnchantedWithoutMending(String uuid, int diamondItemsEnchantedWithoutMending) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerDiamondItemsEnchantedWithoutMending(uuid, diamondItemsEnchantedWithoutMending));
    }

    public BukkitAsyncAction<Boolean> updatePlayerNetheriteItemsEnchantedWithoutMending(String uuid, int netheriteItemsEnchantedWithoutMending) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerNetheriteItemsEnchantedWithoutMending(uuid, netheriteItemsEnchantedWithoutMending));
    }

    public BukkitAsyncAction<Boolean> updatePlayerMarineItemsEnchantedWithoutMending(String uuid, int marineItemsEnchantedWithoutMending) {
        return BukkitAsyncAction.supplyAsync(plugin, () -> delegate.updatePlayerMarineItemsEnchantedWithoutMending(uuid, marineItemsEnchantedWithoutMending));
    }
}

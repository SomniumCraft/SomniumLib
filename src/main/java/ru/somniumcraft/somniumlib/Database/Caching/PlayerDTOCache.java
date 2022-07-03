package ru.somniumcraft.somniumlib.Database.Caching;

import ru.somniumcraft.somniumlib.Database.Data.AsyncWrappedPlayerData;
import ru.somniumcraft.somniumlib.Database.Data.Objects.PlayerDTO;

import java.util.HashMap;

public class PlayerDTOCache {

    private HashMap<String, PlayerDTO> cache = new HashMap<>();

    private HashMap<String, Long> cacheTimestamp = new HashMap<>();

    private  long cacheLifeTime = 5 * 60 * 1000;

    private AsyncWrappedPlayerData playerData;

    public PlayerDTOCache() {
        this.playerData = new AsyncWrappedPlayerData();
    }

    // public load data
    public void loadData() {
        playerData.getPlayers().queue(players -> {

            if (players.isPresent()) {
                players.get().forEach(player -> {
                    cache.put(player.getUuid(), player);
                    cacheTimestamp.put(player.getUuid(), System.currentTimeMillis());
                });
            }

        });
    }

    public PlayerDTO getPlayer(String uuid) {
        if (cache.containsKey(uuid)) {
            if (System.currentTimeMillis() - cacheTimestamp.get(uuid) > cacheLifeTime) {
                playerData.getPlayer(uuid).queue(player -> {
                    if (player.isPresent()) {
                        cache.put(player.get().getUuid(), player.get());
                        cacheTimestamp.put(player.get().getUuid(), System.currentTimeMillis());
                    }
                });
            }
            return cache.get(uuid);
        }
        playerData.getPlayer(uuid).queue(player -> {
            if (player.isPresent()) {
                cache.put(player.get().getUuid(), player.get());
                cacheTimestamp.put(player.get().getUuid(), System.currentTimeMillis());
            }
        });
        return null;
    }

}

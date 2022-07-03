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

    public boolean containsPlayer(String uuid) {
        return cache.containsKey(uuid);
    }

    public void createPlayer(PlayerDTO player) {
        cache.put(player.getUuid(), player);
        cacheTimestamp.put(player.getUuid(), System.currentTimeMillis());
        playerData.updatePlayer(player).queue();
    }

    public void updatePlayer(PlayerDTO player){
        cache.put(player.getUuid(), player);
        cacheTimestamp.put(player.getUuid(), System.currentTimeMillis());
        playerData.updatePlayer(player).queue();
    }

    public void updateBasicPlayerData(String uuid, String name, String skinUrl){
        PlayerDTO oldData = cache.get(uuid);
        PlayerDTO newData = new PlayerDTO(uuid, name, skinUrl, oldData.getJoinMessage(), oldData.getLeaveMessage());
        cache.put(uuid, newData);
        cacheTimestamp.put(uuid, System.currentTimeMillis());
        playerData.updatePlayer(newData).queue();
    }

    public void updateJoinMessage(String uuid, String message){
        PlayerDTO oldData = cache.get(uuid);
        PlayerDTO newData = new PlayerDTO(oldData.getUuid(), oldData.getName(), oldData.getSkinUrl(), message, oldData.getLeaveMessage());
        cache.put(uuid, newData);
        cacheTimestamp.put(uuid, System.currentTimeMillis());
        playerData.updateJoinMessage(uuid, message).queue();
    }

    public void updateLeaveMessage(String uuid, String message){
        PlayerDTO oldData = cache.get(uuid);
        PlayerDTO newData = new PlayerDTO(oldData.getUuid(), oldData.getName(), oldData.getSkinUrl(), oldData.getJoinMessage(), message);
        cache.put(uuid, newData);
        cacheTimestamp.put(uuid, System.currentTimeMillis());
        playerData.updateLeaveMessage(uuid, message).queue();
    }

}

package ru.somniumcraft.somniumlib.Database.Caching.ServerDatabase;

import org.bukkit.Location;
import ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase.PlayerDTO;

import java.math.BigDecimal;
import java.util.HashMap;

public class PlayerDTOCache {
    private HashMap<String, PlayerDTO> playerMap = new HashMap<>();
    private HashMap<String, long> flyTimeChacheTimestamp = new HashMap<String, long>();

    private  long cacheLifeTime = 5 * 60 * 1000;
    private AsyncWrappedPlayerData playerData;

    public PlayerDTOCache() {
        this.playerData = new AsyncWrappedPlayerData();
    }

    public void loadData() {
        playerData.getPlayers().queue(players -> {
            if (players.isPresent()) {
                players.get().forEach(player -> {
                    playerMap.put(player.getUUID(), player);
                    flyTimeChacheTimestamp.put(player.getUUID(), System.currentTimeMillis());
                });
            }
        });
    }

    public PlayerDTO getPlayer(String uuid){
        if (playerMap.containsKey(uuid)) {
            return playerMap.get(uuid);
        }
        return null;
    }

    public boolean containsPlayer(String uuid){
        return playerMap.containsKey(uuid);
    }

    public boolean createPlayer(PlayerDTO player){
        if(!playerMap.containsKey(player.getUUID())){
            playerMap.put(player.getUUID(), player);
            flyTimeChacheTimestamp.put(player.getUUID(), System.currentTimeMillis());
            playerData.createPlayer(player).queue();
            return true;
        }
        return false;
    }

    public boolean updatePlayer(PlayerDTO player){
        if(playerMap.containsKey(player.getUUID())){
            playerMap.put(player.getUUID(), player);
            flyTimeChacheTimestamp.put(player.getUUID(), System.currentTimeMillis());
            playerData.updatePlayer(player).queue();
            return true;
        }
        return false;
    }

    public boolean updatePlayerBalance(String uuid, BigDecimal balance)
    {
        if(playerMap.containsKey(uuid)) {
            playerMap.get(uuid).setBalance(balance);
            playerData.updatePlayerBalance(uuid, balance).queue();
            return true;
        }
        return false;
    }

    public void updatePlayerFlytime(String uuid, long flytime)
    {
        playerMap.get(uuid).setFlytime(flytime);
        if(System.currentTimeMillis() - flyTimeChacheTimestamp.get(uuid) > cacheLifeTime) {
            playerData.updatePlayerFlytime(uuid, flytime).queue();
            flyTimeChacheTimestamp.put(uuid, System.currentTimeMillis());
        }
    }

    public void updatePlayerLastFlyTimeAdded(String uuid, long lastFlyTimeAdded)
    {
        playerMap.get(uuid).setLastTimeAdded(lastFlyTimeAdded);
        if(System.currentTimeMillis() - flyTimeChacheTimestamp.get(uuid) > cacheLifeTime) {
            playerData.updatePlayerLastFlyTimeAdded(uuid, lastFlyTimeAdded).queue();
            flyTimeChacheTimestamp.put(uuid, System.currentTimeMillis());
        }
    }

    public void updatePlayerLastKeepInventory(String uuid, long lastKeepInventory)
    {
        playerMap.get(uuid).setLastKeepInventory(lastKeepInventory);
        if(System.currentTimeMillis() - flyTimeChacheTimestamp.get(uuid) > cacheLifeTime) {
            playerData.updatePlayerLastKeepInventory(uuid, lastKeepInventory).queue();
            flyTimeChacheTimestamp.put(uuid, System.currentTimeMillis());
        }
    }

    public void updatePlayerLoginTime(String uuid, long loginTime)
    {
        playerMap.get(uuid).setLoginTimestamp(loginTime);
        if(System.currentTimeMillis() - flyTimeChacheTimestamp.get(uuid) > cacheLifeTime) {
            playerData.updatePlayerLoginTime(uuid, loginTime).queue();
            flyTimeChacheTimestamp.put(uuid, System.currentTimeMillis());
        }
    }

    public void updatePlayerTeleportInfo(String uuid, Location location){

    }
}

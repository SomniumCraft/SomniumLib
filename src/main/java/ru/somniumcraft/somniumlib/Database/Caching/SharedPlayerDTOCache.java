package ru.somniumcraft.somniumlib.Database.Caching;

public class SharedPlayerDTOCache {

    // TODO: Переделать

//
//    private HashMap<String, SharedPlayerDTO> cache = new HashMap<>();
//
//    private HashMap<String, Long> cacheTimestamp = new HashMap<>();
//
//    private  long cacheLifeTime = 5 * 60 * 1000;
//
//    private AsyncWrappedSharedPlayerData playerData;
//
//    public SharedPlayerDTOCache() {
//        this.playerData = new AsyncWrappedSharedPlayerData();
//    }
//
//    // public load data
//    public void loadData() {
//        playerData.getPlayers().queue(players -> {
//
//            if (players.isPresent()) {
//                players.get().forEach(player -> {
//                    cache.put(player.getUUID(), player);
//                    cacheTimestamp.put(player.getUUID(), System.currentTimeMillis());
//                });
//            }
//
//        });
//    }
//
//    public SharedPlayerDTO getPlayer(String uuid) {
//        if (cache.containsKey(uuid)) {
//            if (System.currentTimeMillis() - cacheTimestamp.get(uuid) > cacheLifeTime) {
//                playerData.getPlayer(uuid).queue(player -> {
//                    if (player.isPresent()) {
//                        cache.put(player.get().getUUID(), player.get());
//                        cacheTimestamp.put(player.get().getUUID(), System.currentTimeMillis());
//                    }
//                });
//            }
//            return cache.get(uuid);
//        }
//        playerData.getPlayer(uuid).queue(player -> {
//            if (player.isPresent()) {
//                cache.put(player.get().getUUID(), player.get());
//                cacheTimestamp.put(player.get().getUUID(), System.currentTimeMillis());
//            }
//        });
//        return null;
//    }
//
//    public boolean containsPlayer(String uuid) {
//        return cache.containsKey(uuid);
//    }
//
//    public void createPlayer(SharedPlayerDTO player) {
//        cache.put(player.getUUID(), player);
//        cacheTimestamp.put(player.getUUID(), System.currentTimeMillis());
//        playerData.createPlayer(player).queue();
//    }
//
//    public void updatePlayer(SharedPlayerDTO player){
//        cache.put(player.getUUID(), player);
//        cacheTimestamp.put(player.getUUID(), System.currentTimeMillis());
//        playerData.updatePlayer(player).queue();
//    }
//
//    public void updateBasicPlayerData(String uuid, String name, String skinUrl){
//        SharedPlayerDTO oldData = cache.get(uuid);
//        SharedPlayerDTO newData = new SharedPlayerDTO(uuid, name, skinUrl, oldData.getJoinMessage(), oldData.getLeaveMessage());
//        cache.put(uuid, newData);
//        cacheTimestamp.put(uuid, System.currentTimeMillis());
//        playerData.updatePlayer(newData).queue();
//    }
//
//    public void updateJoinMessage(String uuid, String message){
//        SharedPlayerDTO oldData = cache.get(uuid);
//        SharedPlayerDTO newData = new SharedPlayerDTO(oldData.getUUID(), oldData.getName(), oldData.getSkinUrl(), message, oldData.getLeaveMessage());
//        cache.put(uuid, newData);
//        cacheTimestamp.put(uuid, System.currentTimeMillis());
//        playerData.updateJoinMessage(uuid, message).queue();
//    }
//
//    public void updateLeaveMessage(String uuid, String message){
//        SharedPlayerDTO oldData = cache.get(uuid);
//        SharedPlayerDTO newData = new SharedPlayerDTO(oldData.getUUID(), oldData.getName(), oldData.getSkinUrl(), oldData.getJoinMessage(), message);
//        cache.put(uuid, newData);
//        cacheTimestamp.put(uuid, System.currentTimeMillis());
//        playerData.updateLeaveMessage(uuid, message).queue();
//    }
//
}

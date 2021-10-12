package ru.somniumcraft.somniumlib;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Cooldown {
    private HashMap<UUID,Long> cooldownMap;
    private String name;
    private String noCooldownPermsission;

    public Cooldown(String name, String noCooldownPermsission) {
        this.cooldownMap = new HashMap<UUID,Long>();
        this.name = name;
        this.noCooldownPermsission = noCooldownPermsission;
    }

    public boolean isOnCooldown(Player player) {
        if(cooldownMap.containsKey(player.getUniqueId()))
            if(cooldownMap.get(player.getUniqueId()) > System.currentTimeMillis())
                return true;

        return false;
    }

    public void putOnCooldown(Player player, long milisec) {

        cooldownMap.put(player.getUniqueId(), System.currentTimeMillis() + milisec);
    }

    public long getRemainingTime(Player player){
        if(isOnCooldown(player))
            return cooldownMap.get(player.getUniqueId())-System.currentTimeMillis();

        return 0;
    }

    public boolean checkAndPutOnCooldown(Player player, long millisec){
        if (!player.hasPermission(noCooldownPermsission)) {
            if(isOnCooldown(player))
                return false;
            putOnCooldown(player, millisec);
        }
        return true;
    }

    public HashMap<UUID, Long> getCooldownMap() {
        return cooldownMap;
    }

    public void setCooldownMap(HashMap<UUID, Long> cooldownMap) {
        this.cooldownMap = cooldownMap;
    }
}

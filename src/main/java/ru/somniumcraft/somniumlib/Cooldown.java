package ru.somniumcraft.somniumlib;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Cooldown {

    @Getter @Setter
    private HashMap<UUID,Long> cooldownMap = new HashMap<>();
    private final String name;
    private final String noCooldownPermission;

    public Cooldown(String name, String noCooldownPermission) {
        this.name = name;
        this.noCooldownPermission = noCooldownPermission;
    }

    public boolean isOnCooldown(Player player) {
        if(cooldownMap.containsKey(player.getUniqueId()))
            return cooldownMap.get(player.getUniqueId()) > System.currentTimeMillis();
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

    public boolean checkAndPutOnCooldown(Player player, long millis){
        if (!player.hasPermission(noCooldownPermission)) {
            if(isOnCooldown(player))
                return false;
            putOnCooldown(player, millis);
        }
        return true;
    }
}

package ru.somniumcraft.somniumlib.Util;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SafeLander {

    public static void land(Player player){
        Location playerLocation = player.getLocation();

        if(playerLocation.getY() > 384)
        {
            playerLocation.setY(384);
            player.teleport(playerLocation);
        }
        int x = playerLocation.getBlockX();
        int y = playerLocation.getBlockY();
        int z = playerLocation.getBlockZ();

        for(; y > 0; y--)
        {
            if(playerLocation.getWorld().getBlockAt(x,y,z).getType().isSolid())
            {
                break;
            }
        }

        double fallDistance = playerLocation.getY() - y;
        if (fallDistance > 3) {

            int duration = (int) Math.ceil(0.125 * (fallDistance + 10)) * 20;

            PotionEffect potionEffect = new PotionEffect(PotionEffectType.SLOW_FALLING, duration, 1);
            player.addPotionEffect(potionEffect);
        }
    }
}

package ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.util.List;

public class InventoriesDTO {

    @Getter @Setter @NotNull
    private int id;

    @Getter @Setter @NotNull
    private String playerUUID;

    @Getter @Setter
    private String killerUUID;

    @Getter @Setter @NotNull
    private Date timestamp;

    @Getter @Setter
    private String deathReason;

    @Getter @Setter @NotNull
    private float experience;

    @Getter @Setter @NotNull
    private double health;

    @Getter @Setter @NotNull
    private float foodLevel;

    @Getter @Setter @NotNull
    private float saturation;

    @Getter @Setter @NotNull
    private List<PotionEffect> potionEffects;

    @Getter @Setter @NotNull
    private List<ItemStack> inventory;

    @Getter @Setter @NotNull
    private boolean keepInventory;

    @Getter @Setter @NotNull
    private Location location;

    public InventoriesDTO(int id, String playerUUID, String killerUUID, Date timestamp, String deathReason, float experience, double health, float foodLevel, float saturation,
                          List<PotionEffect> potionEffects, List<ItemStack> inventory, boolean keepInventory, Location deathLocation) {
        this.id = id;
        this.playerUUID = playerUUID;
        this.killerUUID = killerUUID;
        this.timestamp = timestamp;
        this.deathReason = deathReason;
        this.experience = experience;
        this.health = health;
        this.foodLevel = foodLevel;
        this.saturation = saturation;
        this.potionEffects = potionEffects;
        this.inventory = inventory;
        this.keepInventory = keepInventory;
        this.location = deathLocation;
    }
}

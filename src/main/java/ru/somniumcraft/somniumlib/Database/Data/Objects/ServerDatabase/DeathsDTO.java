package ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class DeathsDTO {

    @Getter @Setter @NotNull
    private int id;

    @Getter @Setter @NotNull
    private String playerUUID;

    @Getter @Setter
    private String killerUUID;

    @Getter @Setter @NotNull
    private long timestamp;

    @Getter @Setter @NotNull
    private String deathReason;

    @Getter @Setter @NotNull
    private String deathLocation;

    @Getter @Setter @NotNull
    private float experience;

    @Getter @Setter @NotNull
    private double health;

    @Getter @Setter @NotNull
    private float foodLevel;

    @Getter @Setter @NotNull
    private float saturation;

    @Getter @Setter @NotNull
    private String[] potionEffects;

    @Getter @Setter @NotNull
    private String[] inventory;

    @Getter @Setter @NotNull
    private boolean keepInventory;

    public DeathsDTO(int id, String playerUUID, String killerUUID, long timestamp, String deathReason, String deathLocation, float experience, double health, float foodLevel, float saturation, String[] potionEffects, String[] inventory, boolean keepInventory) {
        this.id = id;
        this.playerUUID = playerUUID;
        this.killerUUID = killerUUID;
        this.timestamp = timestamp;
        this.deathReason = deathReason;
        this.deathLocation = deathLocation;
        this.experience = experience;
        this.health = health;
        this.foodLevel = foodLevel;
        this.saturation = saturation;
        this.potionEffects = potionEffects;
        this.inventory = inventory;
        this.keepInventory = keepInventory;
    }
}

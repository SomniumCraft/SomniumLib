package ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class PlayerDTO {

    @Getter @Setter @NotNull
    private String uuid;

    @Getter @Setter @NotNull
    private String displayName;

    @Getter @Setter
    private long deathTimestamp;

    @Getter @Setter
    private long logoutTimestamp;

    @Getter @Setter
    private long loginTimestamp;

    @Getter @Setter
    private long teleportTimestamp;

    @Getter @Setter
    private long playtime;

    @Getter @Setter
    private BigDecimal balance;

    @Getter @Setter
    private long flytime;

    @Getter @Setter
    private long lastTimeAdded;

    @Getter @Setter
    private long lastKeepInventory;

    @Getter @Setter
    private Location deathLocation;

    @Getter @Setter
    private Location logoutLocation;

    @Getter @Setter
    private Location teleportLocation;

    @Getter @Setter @NotNull
    private double bookEnchantedWithoutMending;

    @Getter @Setter @NotNull
    private double woodenEnchantedWithoutMending;

    @Getter @Setter @NotNull
    private double stoneEnchantedWithoutMending;

    @Getter @Setter @NotNull
    private double ironEnchantedWithoutMending;

    @Getter @Setter @NotNull
    private double goldEnchantedWithoutMending;

    @Getter @Setter @NotNull
    private double diamondEnchantedWithoutMending;

    @Getter @Setter @NotNull
    private double netheriteEnchantedWithoutMending;

    @Getter @Setter @NotNull
    private double marineEnchantedWithoutMending;


    public PlayerDTO(@NotNull String uuid, @NotNull String displayName, long deathTimestamp, long logoutTimestamp, long loginTimestamp, long teleportTimestamp, long playtime, BigDecimal balance, long flytime, long lastTimeAdded, long lastKeepInventory, Location deathLocation, Location logoutLocation, Location teleportLocation, double bookEnchantedWithoutMending, double woodenEnchantedWithoutMending, double stoneEnchantedWithoutMending, double ironEnchantedWithoutMending, double goldEnchantedWithoutMending, double diamondEnchantedWithoutMending, double netheriteEnchantedWithoutMending, double marineEnchantedWithoutMending) {
        this.uuid = uuid;
        this.displayName = displayName;
        this.deathTimestamp = deathTimestamp;
        this.logoutTimestamp = logoutTimestamp;
        this.loginTimestamp = loginTimestamp;
        this.teleportTimestamp = teleportTimestamp;
        this.playtime = playtime;
        this.balance = balance;
        this.flytime = flytime;
        this.lastTimeAdded = lastTimeAdded;
        this.lastKeepInventory = lastKeepInventory;
        this.deathLocation = deathLocation;
        this.logoutLocation = logoutLocation;
        this.teleportLocation = teleportLocation;
        this.bookEnchantedWithoutMending = bookEnchantedWithoutMending;
        this.woodenEnchantedWithoutMending = woodenEnchantedWithoutMending;
        this.stoneEnchantedWithoutMending = stoneEnchantedWithoutMending;
        this.ironEnchantedWithoutMending = ironEnchantedWithoutMending;
        this.goldEnchantedWithoutMending = goldEnchantedWithoutMending;
        this.diamondEnchantedWithoutMending = diamondEnchantedWithoutMending;
        this.netheriteEnchantedWithoutMending = netheriteEnchantedWithoutMending;
        this.marineEnchantedWithoutMending = marineEnchantedWithoutMending;
    }

}

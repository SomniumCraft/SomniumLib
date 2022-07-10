package ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KitsDTO {

    @Getter @Setter @NotNull
    private int id;

    @Getter @Setter @NotNull
    private String name;

    @Getter @Setter
    private String description;

    @Getter @Setter @NotNull
    private String itemIcon;

    @Getter @Setter @NotNull
    private int price;

    @Getter @Setter
    private int cooldown;

    @Getter @Setter
    private String permission;

    @Getter @Setter @NotNull
    private List<ItemStack> items;

    @Getter @Setter @NotNull
    private boolean isDefault;

    @Getter @Setter @NotNull
    private boolean isActive;

    @Getter @Setter @NotNull
    private int experienceCost;

    @Getter @Setter
    private int maxUseTimes;

    @Getter @Setter @NotNull
    private String activationCommand;

    public KitsDTO(int id, String name, String description, String itemIcon, int price, int cooldown, String permission, List<ItemStack> items, boolean isDefault, boolean isActive, int experienceCost, int maxUseTimes, String activationCommand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.itemIcon = itemIcon;
        this.price = price;
        this.cooldown = cooldown;
        this.permission = permission;
        this.items = items;
        this.isDefault = isDefault;
        this.isActive = isActive;
        this.experienceCost = experienceCost;
        this.maxUseTimes = maxUseTimes;
        this.activationCommand = activationCommand;
    }

}

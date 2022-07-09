package ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class WarpsDTO {

    @Getter @Setter @NotNull
    private int id;

    @Getter @Setter @NotNull
    private String creatorUUID;

    @Getter @Setter @NotNull
    private String name;

    @Getter @Setter @NotNull
    private Location location;

    @Getter @Setter @NotNull
    private String itemIcon;

    @Getter @Setter @NotNull
    private String description;

    @Getter @Setter
    private String permission;

    public WarpsDTO(int id, String creatorUUID, String name, Location location, String itemIcon, String description, String permission) {
        this.id = id;
        this.creatorUUID = creatorUUID;
        this.name = name;
        this.location = location;
        this.itemIcon = itemIcon;
        this.description = description;
        this.permission = permission;
    }

}

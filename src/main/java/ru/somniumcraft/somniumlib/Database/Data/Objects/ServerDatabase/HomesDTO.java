package ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class HomesDTO {

    @Getter @Setter @NotNull
    private int id;

    @Getter @Setter @NotNull
    private String uuid;

    @Getter @Setter @NotNull
    private String name;

    @Getter @Setter @NotNull
    private Location location;

    @Getter @Setter @NotNull
    private String itemIcon;

    public HomesDTO(int id, String uuid, String name, Location location, String itemIcon) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.location = location;
        this.itemIcon = itemIcon;
    }

}

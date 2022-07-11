package ru.somniumcraft.somniumlib.Database.Data.Objects.SharedDatabase;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class SharedPlayerDTO {

    @Getter @Setter @NotNull
    private String UUID;

    @Getter @Setter @NotNull
    private String name;

    @Getter @Setter @NotNull
    private String skinUrl;

    @Getter @Setter
    private String joinMessage;

    @Getter @Setter
    private String leaveMessage;

    public SharedPlayerDTO(@NotNull String uuid, @NotNull String name, @NotNull String skinUrl, String joinMessage, String leaveMessage) {
        this.UUID = uuid;
        this.name = name;
        this.skinUrl = skinUrl;
        this.joinMessage = joinMessage;
        this.leaveMessage = leaveMessage;
    }
}

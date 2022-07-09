package ru.somniumcraft.somniumlib.Database.Data.Objects.SharedDatabase;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class SharedIpsDTO {

    @Getter @Setter @NotNull
    private int id;

    @Getter @Setter @NotNull
    private String ip;

    @Getter @Setter @NotNull
    private String playerUUID;

    @Getter @Setter @NotNull
    private int usedTimes;

    public SharedIpsDTO(@NotNull String ip, @NotNull String playerUUID, int usedTimes) {
        this.ip = ip;
        this.playerUUID = playerUUID;
        this.usedTimes = usedTimes;
    }
}

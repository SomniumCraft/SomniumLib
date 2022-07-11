package ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class PlayerKitsDTO {

    @Getter @Setter @NotNull
    private String playerUUID;

    @Getter @Setter @NotNull
    private int kitId;

    @Getter @Setter @NotNull
    private int usedTimes;

    @Getter @Setter @NotNull
    private int lastUseTimestamp;

    public PlayerKitsDTO(@NotNull String uuid, int kitId, int usedTimes, int lastUseTimestamp) {
        this.playerUUID = uuid;
        this.kitId = kitId;
        this.usedTimes = usedTimes;
        this.lastUseTimestamp = lastUseTimestamp;
    }
}

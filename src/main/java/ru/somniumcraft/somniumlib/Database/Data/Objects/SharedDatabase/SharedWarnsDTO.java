package ru.somniumcraft.somniumlib.Database.Data.Objects.SharedDatabase;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class SharedWarnsDTO {

    @Getter @Setter @NotNull
    private int id;

    @Getter @Setter @NotNull
    private String playerUUID;

    @Getter @Setter @NotNull
    private String issuedByUUID;

    @Getter @Setter @NotNull
    private String reason;

    @Getter @Setter
    private long issuedDate;

    @Getter @Setter
    private long duration;

    @Getter @Setter
    private int warnTypeId;

    public SharedWarnsDTO(int id, String playerUUID, String issuedByUUID, String reason, long issuedDate, long duration, int warnTypeId) {
        this.id = id;
        this.playerUUID = playerUUID;
        this.issuedByUUID = issuedByUUID;
        this.reason = reason;
        this.issuedDate = issuedDate;
        this.duration = duration;
        this.warnTypeId = warnTypeId;
    }
}

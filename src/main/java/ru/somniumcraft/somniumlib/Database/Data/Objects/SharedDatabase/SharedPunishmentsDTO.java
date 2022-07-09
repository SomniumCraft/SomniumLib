package ru.somniumcraft.somniumlib.Database.Data.Objects.SharedDatabase;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class SharedPunishmentsDTO {

    @Getter @Setter @NotNull
    private int id;

    @Getter @Setter @NotNull
    private String punishmentType;

    @Getter @Setter @NotNull
    private String playerUUID;

    @Getter @Setter @NotNull
    private String issuedByUUID;

    @Getter @Setter @NotNull
    private String reason;

    @Getter @Setter @NotNull
    private long issuedDate;

    @Getter @Setter
    private long duration;

    @Getter @Setter
    private long remainingTime;

    public SharedPunishmentsDTO(int id, String punishmentType, String playerUUID, String issuedByUUID, String reason, long issuedDate, long duration, long remainingTime) {
        this.id = id;
        this.punishmentType = punishmentType;
        this.playerUUID = playerUUID;
        this.issuedByUUID = issuedByUUID;
        this.reason = reason;
        this.issuedDate = issuedDate;
        this.duration = duration;
        this.remainingTime = remainingTime;
    }

}

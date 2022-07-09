package ru.somniumcraft.somniumlib.Database.Data.Objects.SharedDatabase;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class SharedRecommendedPunishments {

    @Getter @Setter @NotNull
    private int warnThreshold;

    @Getter @Setter @NotNull
    private int warnTypeId;

    @Getter @Setter @NotNull
    private String message;

    @Getter @Setter
    private String[] commands;

    public SharedRecommendedPunishments(int warnThreshold, int warnTypeId, String message, String[] commands) {
        this.warnThreshold = warnThreshold;
        this.warnTypeId = warnTypeId;
        this.message = message;
        this.commands = commands;
    }
}

package ru.somniumcraft.somniumlib.Database.Data.Objects;

import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

public class PlayerDTO {

    @Getter @NonNull
    private final String uuid;

    @Getter @NonNull
    private final String name;

    @Getter
    private final String joinMessage;

    @Getter
    private final String leaveMessage;

    public PlayerDTO(@NotNull String uuid, @NonNull String name, String joinMessage, String leaveMessage) {
        this.uuid = uuid;
        this.name = name;
        this.joinMessage = joinMessage;
        this.leaveMessage = leaveMessage;
    }
}

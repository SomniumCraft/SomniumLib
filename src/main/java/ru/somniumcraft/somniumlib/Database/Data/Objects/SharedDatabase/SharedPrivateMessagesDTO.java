package ru.somniumcraft.somniumlib.Database.Data.Objects.SharedDatabase;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class SharedPrivateMessagesDTO {

    @Getter @Setter @NotNull
    private int id;

    @Getter @Setter @NotNull
    private String senderUUID;

    @Getter @Setter @NotNull
    private String receiverUUID;

    @Getter @Setter @NotNull
    private String message;

    @Getter @Setter @NotNull
    private long sendTime;

    @Getter @Setter
    private long readTime;

    public SharedPrivateMessagesDTO(int id, String senderUUID, String receiverUUID, String message, long sendTime, long readTime) {
        this.id = id;
        this.senderUUID = senderUUID;
        this.receiverUUID = receiverUUID;
        this.message = message;
        this.sendTime = sendTime;
        this.readTime = readTime;
    }
}

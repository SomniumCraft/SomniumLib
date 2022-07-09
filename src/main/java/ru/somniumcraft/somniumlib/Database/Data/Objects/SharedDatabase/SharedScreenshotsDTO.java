package ru.somniumcraft.somniumlib.Database.Data.Objects.SharedDatabase;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class SharedScreenshotsDTO {


    @Getter @Setter @NotNull
    private int id;

    @Getter @Setter @NotNull
    private String path;

    public SharedScreenshotsDTO(int id, @NotNull String path) {
        this.id = id;
        this.path = path;
    }
}

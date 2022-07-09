package ru.somniumcraft.somniumlib.Database.Data.Objects.SharedDatabase;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class SharedWarnTypesDTO {

    @Getter @Setter @NotNull
    private int id;

    @Getter @Setter @NotNull
    private String name;

    @Getter @Setter @NotNull
    private String description;

    public SharedWarnTypesDTO(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

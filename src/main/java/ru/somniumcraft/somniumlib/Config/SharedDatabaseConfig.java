package ru.somniumcraft.somniumlib.Config;

import lombok.Getter;

@SomniumConfig
public class SharedDatabaseConfig {

    @Getter
    private String host = "localhost";

    @Getter
    private String port = "3306";

    @Getter
    private String database = "somnium_shared";

    @Getter
    private String user = "root";

    @Getter
    private String password = "";

}

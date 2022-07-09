package ru.somniumcraft.somniumlib.Config;

import lombok.Getter;

@SomniumConfig
public class ServerDatabaseConfig {

    @Getter
    private String host = "localhost";

    @Getter
    private String port = "3306";

    @Getter
    private String database = "somnium_server";

    @Getter
    private String user = "root";

    @Getter
    private String password = "";

}

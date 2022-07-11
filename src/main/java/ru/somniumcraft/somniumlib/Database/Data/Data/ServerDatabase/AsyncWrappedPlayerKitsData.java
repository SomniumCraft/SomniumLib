package ru.somniumcraft.somniumlib.Database.Data.Data.ServerDatabase;

import ru.somniumcraft.somniumlib.SomniumLib;

public class AsyncWrappedPlayerKitsData {

    private final PlayerKitsData delegate;
    private final SomniumLib plugin;

    public AsyncWrappedPlayerKitsData() {
        plugin = SomniumLib.getInstance();
        delegate = new PlayerKitsData();
    }


}

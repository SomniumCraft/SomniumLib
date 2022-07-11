package ru.somniumcraft.somniumlib.Database.Data.Data.ServerDatabase;

import ru.somniumcraft.somniumlib.SomniumLib;

public class AsyncWrappedWarpsData {

    private final WarpsData delegate;
    private final SomniumLib plugin;

    public AsyncWrappedWarpsData() {
        plugin = SomniumLib.getInstance();
        delegate = new WarpsData();
    }


}

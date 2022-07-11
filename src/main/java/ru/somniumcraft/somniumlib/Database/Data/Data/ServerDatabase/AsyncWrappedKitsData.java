package ru.somniumcraft.somniumlib.Database.Data.Data.ServerDatabase;

import ru.somniumcraft.somniumlib.SomniumLib;

public class AsyncWrappedKitsData {

    private final KitsData delegate;
    private final SomniumLib plugin;

    public AsyncWrappedKitsData() {
        plugin = SomniumLib.getInstance();
        delegate = new KitsData();
    }

}

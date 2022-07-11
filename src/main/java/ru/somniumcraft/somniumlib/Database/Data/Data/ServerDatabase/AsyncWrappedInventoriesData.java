package ru.somniumcraft.somniumlib.Database.Data.Data.ServerDatabase;

import ru.somniumcraft.somniumlib.SomniumLib;

public class AsyncWrappedInventoriesData {


    private final InventoriesData delegate;
    private final SomniumLib plugin;

    public AsyncWrappedInventoriesData() {
        plugin = SomniumLib.getInstance();
        delegate = new InventoriesData();
    }



}

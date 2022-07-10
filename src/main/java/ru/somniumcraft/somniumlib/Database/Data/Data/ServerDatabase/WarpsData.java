package ru.somniumcraft.somniumlib.Database.Data.Data.ServerDatabase;

import ru.somniumcraft.somniumlib.Database.Data.Util.PluginDataHolder;
import ru.somniumcraft.somniumlib.SomniumLib;

public class WarpsData extends PluginDataHolder {

    public WarpsData() {
        super(SomniumLib.getInstance().getSharedDatabaseConnector(), SomniumLib.getInstance().getServerDatabaseConnector());
    }


}

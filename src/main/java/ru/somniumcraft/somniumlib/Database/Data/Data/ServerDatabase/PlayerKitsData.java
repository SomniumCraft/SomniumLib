package ru.somniumcraft.somniumlib.Database.Data.Data.ServerDatabase;

import ru.somniumcraft.somniumlib.Database.Data.Util.PluginDataHolder;
import ru.somniumcraft.somniumlib.SomniumLib;

public class PlayerKitsData extends PluginDataHolder {

    public PlayerKitsData() {
        super(SomniumLib.getInstance().getSharedDatabaseConnector(), SomniumLib.getInstance().getServerDatabaseConnector());
    }


}

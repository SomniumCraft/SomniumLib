package ru.somniumcraft.somniumlib.Database.Data;

import ru.somniumcraft.somniumlib.Database.Connector.IDatabaseConnector;
import ru.somniumcraft.somniumlib.Database.Data.Util.DatabaseConfigurator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class SharedDatabaseConfigurator extends DatabaseConfigurator {

    public SharedDatabaseConfigurator() {
        super("mysql/SharedDatabase");
    }

    @Override
    protected void loadPatches(IDatabaseConnector connector) throws IOException, SQLException {
        Optional<Integer> latestPatch = getLatestPatch(connector);
        if (latestPatch.isPresent()) {
            var patch = latestPatch.get();
            if(patch == 0)
            {
                executeSqlFile(connector, this.sqlPath + "/patches/patch_1.sql");
                patch = 1;
            }
        }
    }
}

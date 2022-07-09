package ru.somniumcraft.somniumlib.Database.Data.Util;

import ru.somniumcraft.somniumlib.Database.Connector.IDatabaseConnector;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;

public abstract class DatabaseConfigurator {

    protected final String sqlPath;

    protected DatabaseConfigurator(String sqlPath) {
        this.sqlPath = sqlPath;
    }


    public void setup(IDatabaseConnector connector) throws SQLException, IOException {
        try (Connection conn = connector.getConnection()){
            try (PreparedStatement stmt = conn.prepareStatement("SHOW TABLES LIKE 'patches';")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    if (!rs.next()) {
                        initialize(connector);
                    }
                    loadPatches(connector);
                }
            }
        }
    }

    protected void initialize(IDatabaseConnector connector) throws IOException, SQLException {
        executeSqlFile(connector, sqlPath + "/full.sql");
    }


    //get optional int of latest patch in database
    protected Optional<Integer> getLatestPatch(IDatabaseConnector connector) throws SQLException {
        try (Connection conn = connector.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement("SELECT patch FROM `patches` ORDER BY patch DESC LIMIT 1")) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return Optional.of(rs.getInt("patch"));
                }
            }
        }
        return Optional.empty();
    }

    protected void executeSqlFile(IDatabaseConnector connector, String path) throws IOException, SQLException {
        String setup;
        try (InputStream in = DatabaseConfigurator.class.getClassLoader().getResourceAsStream(path)) {
            setup = new String(in.readAllBytes());
        } catch (IOException e) {
            SomniumLib.getInstance().getLogger().log(Level.SEVERE, "Could not read " + path + " file.", e);
            throw e;
        }
        // Mariadb can only handle a single query per statement. We need to split at ;.
        String[] queries = setup.split(";");
        try (Connection conn = connector.getConnection()) {
            conn.setAutoCommit(false);
            // execute each query to the database.
            for (String query : queries) {
                // If you use the legacy way you have to check for empty queries here.
                if (query.isBlank()) continue;
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    SomniumLib.getInstance().getLogger().info(query);
                    stmt.execute();
                }
            }
            conn.commit();
        }
        SomniumLib.getInstance().getLogger().info( path + " setup complete.");
    }

    protected abstract void loadPatches(IDatabaseConnector connector) throws IOException, SQLException;
}

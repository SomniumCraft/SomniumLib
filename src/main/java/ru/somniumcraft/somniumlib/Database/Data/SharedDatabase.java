package ru.somniumcraft.somniumlib.Database.Data;

import ru.somniumcraft.somniumlib.Database.Connector.IDatabaseConnector;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;

public class SharedDatabase {

    public void setup(IDatabaseConnector connector) throws SQLException, IOException {
        try (Connection conn = connector.getConnection()){
            try (PreparedStatement stmt = conn.prepareStatement("SHOW TABLES LIKE 'patches';")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    if (!rs.next()) {
                        initialize(connector);
                    }
                    else{
                        loadPatches(connector);
                    }
                }
            }
        }
    }

    private void initialize(IDatabaseConnector connector) throws IOException, SQLException {
        String setup;
        try (InputStream in = SharedDatabase.class.getClassLoader().getResourceAsStream("mysql/SharedDatabase/full.sql")) {
            setup = new String(in.readAllBytes());
        } catch (IOException e) {
            SomniumLib.getInstance().getLogger().log(Level.SEVERE, "Could not read db setup file.", e);
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
        SomniumLib.getInstance().getLogger().info("§2Database setup complete.");
    }


    //get optional int of latest patch in database
    private Optional<Integer> getLatestPatch(IDatabaseConnector connector) throws SQLException {
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

    //load all resource files in folder /mysql/SharedDatabase/patches
    private List<String> getResourceFiles(String path) throws IOException {
        List<String> files = new LinkedList<>();
        try(InputStream in = SharedDatabase.class.getClassLoader().getResourceAsStream(path)) {
            if (in == null) {
                throw new IOException("Could not find resource file: " + path);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    files.add(line);
                }
            }
        }
        return files;
    }


    //load several files from resources with patch_*.sql mask and execute them.
    public void loadPatches(IDatabaseConnector connector) throws IOException, SQLException {
      /*  Optional<Integer> patchVersion = getLatestPatch(connector);
        List<String> patches = getResourceFiles("mysql/SharedDatabase/patches/");
        List<String> toSort = new ArrayList<>();
        for (String patch : patches) {
            toSort.add(patch);
        }
        toSort.sort(Comparator.comparing(s -> Integer.parseInt(s.split("_")[s.split("_").length - 1])));
        try (Connection conn = connector.getConnection()) {
            conn.setAutoCommit(false);
            String setup;
            for (String patch : toSort) {
                if(patchVersion.isPresent() && patchVersion.get() >= Integer.parseInt(patch.split("_")[patch.split("_").length - 1])) {
                    continue;
                }
                try (InputStream in = SharedDatabase.class.getClassLoader().getResourceAsStream(patch)) {
                    setup = new String(in.readAllBytes());
                } catch (IOException e) {
                    SomniumLib.getInstance().getLogger().log(Level.SEVERE, "Could not read db " + patch + " file.", e);
                    throw e;
                }
                String[] queries = setup.split(";");
                for (String query : queries) {
                    if (query.isBlank()) continue;
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        SomniumLib.getInstance().getLogger().info(query);
                        stmt.execute();
                    }
                }
            }
            conn.commit();
            conn.setAutoCommit(true);
        }*/
    }



}

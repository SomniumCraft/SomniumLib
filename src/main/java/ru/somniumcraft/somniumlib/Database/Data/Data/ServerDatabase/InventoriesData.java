package ru.somniumcraft.somniumlib.Database.Data.Data.ServerDatabase;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import ru.somniumcraft.somniumlib.Database.Data.Data.SusUtils;
import ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase.InventoriesDTO;
import ru.somniumcraft.somniumlib.Database.Data.Util.PluginDataHolder;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InventoriesData extends PluginDataHolder {

    public InventoriesData() {
        super(SomniumLib.getInstance().getSharedDatabaseConnector(), SomniumLib.getInstance().getServerDatabaseConnector());
    }

    public Optional<InventoriesDTO> getPlayerInventories(String uuid) {
        try (Connection connection = getServerConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM inventories WHERE uuid = ?")) {
                preparedStatement.setString(1, uuid);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(new InventoriesDTO(
                                resultSet.getInt("id"),
                                resultSet.getString("player_uuid"),
                                resultSet.getString("killer_uuid"),
                                resultSet.getDate("timestamp"),
                                resultSet.getString("death_reason"),
                                resultSet.getFloat("experience"),
                                resultSet.getDouble("health"),
                                resultSet.getFloat("food_level"),
                                resultSet.getFloat("saturation"),
                                SusUtils.jsonToPotionEffects(resultSet.getString("potion_effects")),
                                SusUtils.jsonToItemStackList(resultSet.getString("inventory")),
                                (resultSet.getInt("keep_inventory") != 0),
                                new Location(Bukkit.getWorld(resultSet.getString("location_world")),
                                        resultSet.getFloat("location_x"),
                                        resultSet.getFloat("location_y"),
                                        resultSet.getFloat("location_z"),
                                        resultSet.getFloat("location_pitch"),
                                        resultSet.getFloat("location_yaw"))
                        ));
                    }
                }
            }
        } catch (SQLException ex) {
            logSQLError("Failed to get inventories", ex);
            return Optional.empty();
        }

        return Optional.empty();
    }

    // get optional list of all players from database
    public Optional<List<InventoriesDTO>> getPlayersInventories() {
        try (Connection connection = getServerConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM inventories")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<InventoriesDTO> players = new ArrayList<>();
                    while (resultSet.next()) {
                        players.add(new InventoriesDTO(
                                resultSet.getInt("id"),
                                resultSet.getString("player_uuid"),
                                resultSet.getString("killer_uuid"),
                                resultSet.getDate("timestamp"),
                                resultSet.getString("death_reason"),
                                resultSet.getFloat("experience"),
                                resultSet.getDouble("health"),
                                resultSet.getFloat("food_level"),
                                resultSet.getFloat("saturation"),
                                SusUtils.jsonToPotionEffects(resultSet.getString("potion_effects")),
                                SusUtils.jsonToItemStackList(resultSet.getString("inventory")),
                                (resultSet.getInt("keep_inventory") != 0),
                                new Location(Bukkit.getWorld(resultSet.getString("location_world")),
                                        resultSet.getFloat("location_x"),
                                        resultSet.getFloat("location_y"),
                                        resultSet.getFloat("location_z"),
                                        resultSet.getFloat("location_pitch"),
                                        resultSet.getFloat("location_yaw"))
                        ));
                    }
                    return Optional.of(players);
                }
            }
        } catch (SQLException ex) {
            logSQLError("Failed to get inventories", ex);
            return Optional.empty();
        }
    }

    // create new record in database
    public boolean addPlayerInventory(InventoriesDTO inventoriesDTO) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO inventories (player_uuid, killer_uuid, timestamp, death_reason, experience, health, food_level, saturation, potion_effects, inventory, " +
                            "keep_inventory, location_world, location_x, location_y, location_z, location_pitch, location_yaw) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                var susdfgdf = new Gson().toJson(inventoriesDTO.getPotionEffects().stream().map(x -> new Gson().toJson(x.serialize())).collect(Collectors.toList()));
                preparedStatement.setString(1, inventoriesDTO.getPlayerUUID());
                preparedStatement.setString(2, inventoriesDTO.getKillerUUID());
                preparedStatement.setDate(3, inventoriesDTO.getTimestamp());
                preparedStatement.setString(4, inventoriesDTO.getDeathReason());
                preparedStatement.setFloat(5, inventoriesDTO.getExperience());
                preparedStatement.setDouble(6, inventoriesDTO.getHealth());
                preparedStatement.setFloat(7, inventoriesDTO.getFoodLevel());
                preparedStatement.setFloat(8, inventoriesDTO.getSaturation());
                preparedStatement.setString(9, SusUtils.potionEffectsToJson(inventoriesDTO.getPotionEffects()));
                preparedStatement.setString(10, SusUtils.itemStackListToJson(inventoriesDTO.getInventory()));
                preparedStatement.setInt(11, inventoriesDTO.isKeepInventory() ? 1 : 0);
                preparedStatement.setString(12, inventoriesDTO.getLocation().getWorld().getName());
                preparedStatement.setFloat(13, (float) inventoriesDTO.getLocation().getX());
                preparedStatement.setFloat(14, (float) inventoriesDTO.getLocation().getY());
                preparedStatement.setFloat(15, (float) inventoriesDTO.getLocation().getZ());
                preparedStatement.setFloat(16, inventoriesDTO.getLocation().getPitch());
                preparedStatement.setFloat(17, inventoriesDTO.getLocation().getYaw());
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to add player inventory", ex);
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        } catch (SQLException ex) {
            logSQLError("Failed to add player inventory", ex);
            return false;
        }
    }

    //remove record from database
    public boolean removePlayerInventory(int id) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement
                    ("DELETE FROM inventories WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to remove player inventory", ex);
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        } catch (SQLException ex) {
            logSQLError("Failed to remove player inventory", ex);
            return false;
        }
    }

}

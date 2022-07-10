package ru.somniumcraft.somniumlib.Database.Data.Data.ServerDatabase;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase.HomesDTO;
import ru.somniumcraft.somniumlib.Database.Data.Util.PluginDataHolder;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HomesData extends PluginDataHolder {

        public HomesData() {
            super(SomniumLib.getInstance().getSharedDatabaseConnector(), SomniumLib.getInstance().getServerDatabaseConnector());
        }

        public Optional<HomesDTO> getPlayerHome(String uuid, String name) {

            try (Connection connection = getServerConnection()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM homes WHERE uuid = ? AND name = ?")) {
                    preparedStatement.setString(1, uuid);
                    preparedStatement.setString(2, name);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            return Optional.of(new HomesDTO(
                                    // id, uuid, name, location, itemicon
                                    resultSet.getInt("id"),
                                    resultSet.getString("uuid"),
                                    resultSet.getString("name"),
                                    new Location(Bukkit.getWorld(
                                            resultSet.getString("location_world")),
                                            resultSet.getFloat("location_x"),
                                            resultSet.getFloat("location_y"),
                                            resultSet.getFloat("location_z"),
                                            resultSet.getFloat("location_yaw"),
                                            resultSet.getFloat("location_pitch")),
                                    resultSet.getString("item_icon")
                            ));
                        }
                    }
                }

            } catch (SQLException ex) {
                logSQLError("Failed to get home", ex);
                return Optional.empty();
            }

            return Optional.empty();
        }

        // get player homes

        public Optional<List<HomesDTO>> getPlayerHomes(String uuid) {

            try (Connection connection = getServerConnection()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM homes WHERE uuid = ?")) {
                    preparedStatement.setString(1, uuid);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        List<HomesDTO> homes = new ArrayList<>();
                        while (resultSet.next()) {
                            homes.add(new HomesDTO(
                                    resultSet.getInt("id"),
                                    resultSet.getString("uuid"),
                                    resultSet.getString("name"),
                                    new Location(Bukkit.getWorld(
                                            resultSet.getString("location_world")),
                                            resultSet.getFloat("location_x"),
                                            resultSet.getFloat("location_y"),
                                            resultSet.getFloat("location_z"),
                                            resultSet.getFloat("location_yaw"),
                                            resultSet.getFloat("location_pitch")),
                                    resultSet.getString("item_icon")
                            ));
                        }
                        return Optional.of(homes);
                    }
                }

            } catch (SQLException ex) {
                logSQLError("Failed to get homes", ex);
                return Optional.empty();
            }
        }

        public boolean createHome(HomesDTO home) {

            try (Connection connection = getServerConnection()) {
                connection.setAutoCommit(false);
                try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO homes (uuid, name, location_world, location_x, location_y, location_z, location_yaw, location_pitch, item_icon) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    preparedStatement.setString(1, home.getUuid());
                    preparedStatement.setString(2, home.getName());
                    preparedStatement.setString(3, home.getLocation().getWorld().getName());
                    preparedStatement.setFloat(4, (float) home.getLocation().getX());
                    preparedStatement.setFloat(5, (float) home.getLocation().getY());
                    preparedStatement.setFloat(6, (float) home.getLocation().getZ());
                    preparedStatement.setFloat(7, home.getLocation().getYaw());
                    preparedStatement.setFloat(8, home.getLocation().getPitch());
                    preparedStatement.setString(9, home.getItemIcon());
                    preparedStatement.executeUpdate();
                    connection.commit();
                    connection.setAutoCommit(true);
                    return true;
                } catch (SQLException ex) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    logSQLError("Failed to create home", ex);
                    return false;
                }
            } catch (SQLException ex) {
                logSQLError("Failed to create home", ex);
                return false;
            }
        }

        public boolean deletePlayerHome(String uuid, String name) {

            try (Connection connection = getServerConnection()) {
                connection.setAutoCommit(false);
                try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM homes WHERE uuid = ? AND name = ?")) {
                    preparedStatement.setString(1, uuid);
                    preparedStatement.setString(2, name);
                    preparedStatement.executeUpdate();
                    connection.commit();
                    connection.setAutoCommit(true);
                    return true;
                } catch (SQLException ex) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    logSQLError("Failed to remove home", ex);
                    return false;
                }
            } catch (SQLException ex) {
                logSQLError("Failed to remove home", ex);
                return false;
            }
        }

        // update player

        public boolean updatePlayerHome(HomesDTO home) {

            try (Connection connection = getServerConnection()) {
                connection.setAutoCommit(false);
                try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE homes SET location_world = ?, location_x = ?, location_y = ?, location_z = ?, location_yaw = ?, location_pitch = ?, item_icon = ? WHERE uuid = ? AND name = ?")) {
                    preparedStatement.setString(1, home.getLocation().getWorld().getName());
                    preparedStatement.setFloat(2, (float) home.getLocation().getX());
                    preparedStatement.setFloat(3, (float) home.getLocation().getY());
                    preparedStatement.setFloat(4, (float) home.getLocation().getZ());
                    preparedStatement.setFloat(5, home.getLocation().getYaw());
                    preparedStatement.setFloat(6, home.getLocation().getPitch());
                    preparedStatement.setString(7, home.getItemIcon());
                    preparedStatement.executeUpdate();
                    connection.commit();
                    connection.setAutoCommit(true);
                    return true;
                } catch (SQLException ex) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    logSQLError("Failed to update home", ex);
                    return false;
                }
            } catch (SQLException ex) {
                logSQLError("Failed to update home", ex);
                return false;
            }
        }
}

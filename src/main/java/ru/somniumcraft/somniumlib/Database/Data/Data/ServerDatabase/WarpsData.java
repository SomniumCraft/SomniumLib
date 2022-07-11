package ru.somniumcraft.somniumlib.Database.Data.Data.ServerDatabase;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase.WarpsDTO;
import ru.somniumcraft.somniumlib.Database.Data.Util.PluginDataHolder;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class WarpsData extends PluginDataHolder {

    public WarpsData() {
        super(SomniumLib.getInstance().getSharedDatabaseConnector(), SomniumLib.getInstance().getServerDatabaseConnector());
    }

    // create warp, update warp, remove warp, update warp location

    public boolean createWarp(WarpsDTO warp) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO warps (creator_uuid, name, location_world, location_x, location_y, location_z, location_pitch, location_yaw, item_icon, description, permission) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                preparedStatement.setString(1, warp.getCreatorUUID());
                preparedStatement.setString(2, warp.getName());
                preparedStatement.setString(3, warp.getLocation().getWorld().getName());
                preparedStatement.setFloat(4, (float) warp.getLocation().getX());
                preparedStatement.setFloat(5, (float) warp.getLocation().getY());
                preparedStatement.setFloat(6, (float) warp.getLocation().getZ());
                preparedStatement.setFloat(7, warp.getLocation().getPitch());
                preparedStatement.setFloat(8, warp.getLocation().getYaw());
                preparedStatement.setString(9, warp.getItemIcon());
                preparedStatement.setString(10, warp.getDescription());
                preparedStatement.setString(11, warp.getPermission());
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                connection.rollback();
                connection.setAutoCommit(true);
                logSQLError("Failed to create warp", ex);
                return false;
            }
        } catch (SQLException e) {
            logSQLError("Failed to create warp", e);
            return false;
        }
    }

    public boolean updateWarp(WarpsDTO warp) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE warps SET creator_uuid = ?, name = ?, location_world = ?, location_x = ?, location_y = ?, location_z = ?, location_pitch = ?, location_yaw = ?, item_icon = ?, description = ?, permission = ? WHERE id = ?")) {
                preparedStatement.setString(1, warp.getCreatorUUID());
                preparedStatement.setString(2, warp.getName());
                preparedStatement.setString(3, warp.getLocation().getWorld().getName());
                preparedStatement.setFloat(4, (float) warp.getLocation().getX());
                preparedStatement.setFloat(5, (float) warp.getLocation().getY());
                preparedStatement.setFloat(6, (float) warp.getLocation().getZ());
                preparedStatement.setFloat(7, warp.getLocation().getPitch());
                preparedStatement.setFloat(8, warp.getLocation().getYaw());
                preparedStatement.setString(9, warp.getItemIcon());
                preparedStatement.setString(10, warp.getDescription());
                preparedStatement.setString(11, warp.getPermission());
                preparedStatement.setInt(12, warp.getId());
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                connection.rollback();
                connection.setAutoCommit(true);
                logSQLError("Failed to update warp", ex);
                return false;
            }
        } catch (SQLException e) {
            logSQLError("Failed to update warp", e);
            return false;
        }
    }

    public boolean removeWarp(String name) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM warps WHERE name = ?")) {
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                connection.rollback();
                connection.setAutoCommit(true);
                logSQLError("Failed to remove warp", ex);
                return false;
            }
        } catch (SQLException e) {
            logSQLError("Failed to remove warp", e);
            return false;
        }
    }

    public boolean updateWarpLocation(String name, Location location) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE warps SET location_world = ?, location_x = ?, location_y = ?, location_z = ?, location_pitch = ?, location_yaw = ? WHERE name = ?")) {
                preparedStatement.setString(1, location.getWorld().getName());
                preparedStatement.setFloat(2, (float) location.getX());
                preparedStatement.setFloat(3, (float) location.getY());
                preparedStatement.setFloat(4, (float) location.getZ());
                preparedStatement.setFloat(5, location.getPitch());
                preparedStatement.setFloat(6, location.getYaw());
                preparedStatement.setString(7, name);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                connection.rollback();
                connection.setAutoCommit(true);
                logSQLError("Failed to update warp location", ex);
                return false;
            }
        } catch (SQLException e) {
            logSQLError("Failed to update warp location", e);
            return false;
        }
    }

    public Optional<WarpsDTO> getWarp(String name) {
        try (Connection connection = getServerConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM warps WHERE name = ?")) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(new WarpsDTO(
                                resultSet.getInt("id"),
                                resultSet.getString("creator_uuid"),
                                resultSet.getString("name"),
                                new Location(
                                        Bukkit.getWorld(resultSet.getString("location_world")),
                                        resultSet.getDouble("location_x"),
                                        resultSet.getDouble("location_y"),
                                        resultSet.getDouble("location_z"),
                                        resultSet.getFloat("location_yaw"),
                                        resultSet.getFloat("location_pitch")
                                ),
                                resultSet.getString("item_icon"),
                                resultSet.getString("description"),
                                resultSet.getString("permission")
                        ));
                    }
                }
            } catch (SQLException ex) {
                logSQLError("Failed to get warp", ex);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logSQLError("Failed to get warp", e);
            return Optional.empty();
        }
        return Optional.empty();
    }

}

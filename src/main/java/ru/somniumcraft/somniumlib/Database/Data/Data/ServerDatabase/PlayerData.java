package ru.somniumcraft.somniumlib.Database.Data.Data.ServerDatabase;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase.PlayerDTO;
import ru.somniumcraft.somniumlib.Database.Data.Util.PluginDataHolder;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerData extends PluginDataHolder {

    public PlayerData() {
        super(SomniumLib.getInstance().getSharedDatabaseConnector(), SomniumLib.getInstance().getServerDatabaseConnector());
    }

    public Optional<PlayerDTO> getPlayer(String uuid) {

        try (Connection connection = getServerConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM players WHERE uuid = ?")) {
                preparedStatement.setString(1, uuid);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(new PlayerDTO(
                                resultSet.getString("uuid"),
                                resultSet.getString("display_name"),
                                resultSet.getLong("death_timestamp"),
                                resultSet.getLong("logout_timestamp"),
                                resultSet.getLong("login_timestamp"),
                                resultSet.getLong("teleport_timestamp"),
                                resultSet.getLong("total_playtime"),
                                resultSet.getBigDecimal("balance"),
                                resultSet.getLong("flytime"),
                                resultSet.getLong("last_fly_time_added"),
                                resultSet.getLong("last_keep_inventory"),
                                new Location(Bukkit.getWorld(resultSet.getString("death_location_world")),
                                        resultSet.getDouble("death_location_x"),
                                        resultSet.getDouble("death_location_y"),
                                        resultSet.getDouble("death_location_z"),
                                        resultSet.getFloat("death_location_yaw"),
                                        resultSet.getFloat("death_location_pitch")
                                ),
                                new Location(Bukkit.getWorld(resultSet.getString("death_location_world")),
                                        resultSet.getDouble("logout_location_x"),
                                        resultSet.getDouble("dlogout_location_y"),
                                        resultSet.getDouble("logout_location_z"),
                                        resultSet.getFloat("logout_location_yaw"),
                                        resultSet.getFloat("logout_location_pitch")
                                ),
                                new Location(Bukkit.getWorld(resultSet.getString("death_location_world")),
                                        resultSet.getDouble("teleport_location_x"),
                                        resultSet.getDouble("teleport_location_y"),
                                        resultSet.getDouble("teleport_location_z"),
                                        resultSet.getFloat("teleport_location_yaw"),
                                        resultSet.getFloat("teleport_location_pitch")
                                ),
                                resultSet.getDouble("book_enchanted_without_mending"),
                                resultSet.getDouble("wooden_enchanted_without_mending"),
                                resultSet.getDouble("stone_enchanted_without_mending"),
                                resultSet.getDouble("iron_enchanted_without_mending"),
                                resultSet.getDouble("golden_enchanted_without_mending"),
                                resultSet.getDouble("diamond_enchanted_without_mending"),
                                resultSet.getDouble("netherite_enchanted_without_mending"),
                                resultSet.getDouble("marine_enchanted_without_mending")
                        ));
                    }
                }
            }

        } catch (SQLException ex) {
            logSQLError("Failed to get player", ex);
            return Optional.empty();
        }
        return Optional.empty();
    }

    public Optional<List<PlayerDTO>> getPlayers() {

        try (Connection connection = getServerConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM players")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<PlayerDTO> players = new ArrayList<>();
                    while (resultSet.next()) {
                        players.add(new PlayerDTO(
                                resultSet.getString("uuid"),
                                resultSet.getString("display_name"),
                                resultSet.getLong("death_timestamp"),
                                resultSet.getLong("logout_timestamp"),
                                resultSet.getLong("login_timestamp"),
                                resultSet.getLong("teleport_timestamp"),
                                resultSet.getLong("total_playtime"),
                                resultSet.getBigDecimal("balance"),
                                resultSet.getLong("fly_time"),
                                resultSet.getLong("last_fly_time_added"),
                                resultSet.getLong("last_keep_inventory"),
                                new Location(Bukkit.getWorld(resultSet.getString("death_location_world")),
                                        resultSet.getDouble("death_location_x"),
                                        resultSet.getDouble("death_location_y"),
                                        resultSet.getDouble("death_location_z"),
                                        resultSet.getFloat("death_location_yaw"),
                                        resultSet.getFloat("death_location_pitch")
                                ),
                                new Location(Bukkit.getWorld(resultSet.getString("logout_location_world")),
                                        resultSet.getDouble("logout_location_x"),
                                        resultSet.getDouble("logout_location_y"),
                                        resultSet.getDouble("logout_location_z"),
                                        resultSet.getFloat("logout_location_yaw"),
                                        resultSet.getFloat("logout_location_pitch")
                                ),
                                new Location(Bukkit.getWorld(resultSet.getString("teleport_location_world")),
                                        resultSet.getDouble("teleport_location_x"),
                                        resultSet.getDouble("teleport_location_y"),
                                        resultSet.getDouble("teleport_location_z"),
                                        resultSet.getFloat("teleport_location_yaw"),
                                        resultSet.getFloat("teleport_location_pitch")
                                ),
                                resultSet.getDouble("book_enchanted_without_mending"),
                                resultSet.getDouble("wooden_enchanted_without_mending"),
                                resultSet.getDouble("stone_enchanted_without_mending"),
                                resultSet.getDouble("iron_enchanted_without_mending"),
                                resultSet.getDouble("golden_enchanted_without_mending"),
                                resultSet.getDouble("diamond_enchanted_without_mending"),
                                resultSet.getDouble("netherite_enchanted_without_mending"),
                                resultSet.getDouble("marine_enchanted_without_mending")
                        ));
                    }
                    return Optional.of(players);
                }
            }

        } catch (SQLException ex) {
            logSQLError("Failed to get players", ex);
            return Optional.empty();
        }
    }

    public boolean createPlayer(PlayerDTO playerDTO) {

        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO players (" +
                    "uuid, " +
                    "display_name, " +
                    "death_timestamp, " +
                    "logout_timestamp, " +
                    "login_timestamp, " +
                    "teleport_timestamp, " +
                    "total_playtime, " +
                    "balance, " +
                    "fly_time, " +
                    "last_fly_time_added, " +
                    "last_keep_inventory, " +
                    "death_location_world, " +
                    "death_location_x, " +
                    "death_location_y, " +
                    "death_location_z, " +
                    "death_location_yaw, " +
                    "death_location_pitch, " +
                    "logout_location_world, " +
                    "logout_location_x, " +
                    "logout_location_y, " +
                    "logout_location_z, " +
                    "logout_location_yaw, " +
                    "logout_location_pitch, " +
                    "teleport_location_world, " +
                    "teleport_location_x, " +
                    "teleport_location_y, " +
                    "teleport_location_z, " +
                    "teleport_location_yaw, " +
                    "teleport_location_pitch, " +
                    "book_enchanted_without_mending, " +
                    "wooden_enchanted_without_mending, " +
                    "stone_enchanted_without_mending, " +
                    "iron_enchanted_without_mending, " +
                    "golden_enchanted_without_mending, " +
                    "diamond_enchanted_without_mending, " +
                    "netherite_enchanted_without_mending, " +
                    "marine_enchanted_without_mending" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                preparedStatement.setString(1, playerDTO.getUUID());
                preparedStatement.setString(2, playerDTO.getDisplayName());
                preparedStatement.setLong(3, playerDTO.getDeathTimestamp());
                preparedStatement.setLong(4, playerDTO.getLogoutTimestamp());
                preparedStatement.setLong(5, playerDTO.getLoginTimestamp());
                preparedStatement.setLong(6, playerDTO.getTeleportTimestamp());
                preparedStatement.setLong(7, playerDTO.getPlaytime());
                preparedStatement.setBigDecimal(8, playerDTO.getBalance());
                preparedStatement.setLong(9, playerDTO.getFlytime());
                preparedStatement.setLong(10, playerDTO.getLastTimeAdded());
                preparedStatement.setLong(11, playerDTO.getLastKeepInventory());
                preparedStatement.setString(12, playerDTO.getDeathLocation().getWorld().getName());
                preparedStatement.setDouble(13, playerDTO.getDeathLocation().getX());
                preparedStatement.setDouble(14, playerDTO.getDeathLocation().getY());
                preparedStatement.setDouble(15, playerDTO.getDeathLocation().getZ());
                preparedStatement.setFloat(16, playerDTO.getDeathLocation().getYaw());
                preparedStatement.setFloat(17, playerDTO.getDeathLocation().getPitch());
                preparedStatement.setString(18, playerDTO.getLogoutLocation().getWorld().getName());
                preparedStatement.setDouble(19, playerDTO.getLogoutLocation().getX());
                preparedStatement.setDouble(20, playerDTO.getLogoutLocation().getY());
                preparedStatement.setDouble(21, playerDTO.getLogoutLocation().getZ());
                preparedStatement.setFloat(22, playerDTO.getLogoutLocation().getYaw());
                preparedStatement.setFloat(23, playerDTO.getLogoutLocation().getPitch());
                preparedStatement.setString(24, playerDTO.getTeleportLocation().getWorld().getName());
                preparedStatement.setDouble(25, playerDTO.getTeleportLocation().getX());
                preparedStatement.setDouble(26, playerDTO.getTeleportLocation().getY());
                preparedStatement.setDouble(27, playerDTO.getTeleportLocation().getZ());
                preparedStatement.setFloat(28, playerDTO.getTeleportLocation().getYaw());
                preparedStatement.setFloat(29, playerDTO.getTeleportLocation().getPitch());
                preparedStatement.setDouble(30, playerDTO.getBookEnchantedWithoutMending());
                preparedStatement.setDouble(31, playerDTO.getWoodenEnchantedWithoutMending());
                preparedStatement.setDouble(32, playerDTO.getStoneEnchantedWithoutMending());
                preparedStatement.setDouble(33, playerDTO.getIronEnchantedWithoutMending());
                preparedStatement.setDouble(34, playerDTO.getGoldEnchantedWithoutMending());
                preparedStatement.setDouble(35, playerDTO.getDiamondEnchantedWithoutMending());
                preparedStatement.setDouble(36, playerDTO.getNetheriteEnchantedWithoutMending());
                preparedStatement.setDouble(37, playerDTO.getMarineEnchantedWithoutMending());
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to create player", ex);
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        } catch (SQLException ex) {
            logSQLError("Failed to create player", ex);
            return false;
        }
    }

    public boolean updatePlayer(PlayerDTO playerDTO) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET " +
                    "uuid = ?, " +
                    "display_name = ?, " +
                    "death_timestamp = ?, " +
                    "logout_timestamp = ?, " +
                    "login_timestamp = ?, " +
                    "teleport_timestamp = ?, " +
                    "total_playtime = ?, " +
                    "balance = ?, " +
                    "fly_time = ?, " +
                    "last_fly_time_added = ?, " +
                    "last_keep_inventory = ?, " +
                    "death_location_world = ?, " +
                    "death_location_x = ?, " +
                    "death_location_y = ?, " +
                    "death_location_z = ?, " +
                    "death_location_yaw = ?, " +
                    "death_location_pitch = ?, " +
                    "logout_location_world = ?, " +
                    "logout_location_x = ?, " +
                    "logout_location_y = ?, " +
                    "logout_location_z = ?, " +
                    "logout_location_yaw = ?, " +
                    "logout_location_pitch = ?, " +
                    "teleport_location_world = ?, " +
                    "teleport_location_x = ?, " +
                    "teleport_location_y = ?, " +
                    "teleport_location_z = ?, " +
                    "teleport_location_yaw = ?, " +
                    "teleport_location_pitch = ?, " +
                    "book_enchanted_without_mending = ?, " +
                    "wooden_enchanted_without_mending = ?, " +
                    "stone_enchanted_without_mending = ?, " +
                    "iron_enchanted_without_mending = ?, " +
                    "golden_enchanted_without_mending = ?, " +
                    "diamond_enchanted_without_mending = ?, " +
                    "netherite_enchanted_without_mending = ?, " +
                    "marine_enchanted_without_mending = ? " +
                    "WHERE uuid = ?")) {
                preparedStatement.setString(1, playerDTO.getUUID());
                preparedStatement.setString(2, playerDTO.getDisplayName());
                preparedStatement.setLong(3, playerDTO.getDeathTimestamp());
                preparedStatement.setLong(4, playerDTO.getLogoutTimestamp());
                preparedStatement.setLong(5, playerDTO.getLoginTimestamp());
                preparedStatement.setLong(6, playerDTO.getTeleportTimestamp());
                preparedStatement.setLong(7, playerDTO.getPlaytime());
                preparedStatement.setBigDecimal(8, playerDTO.getBalance());
                preparedStatement.setLong(9, playerDTO.getFlytime());
                preparedStatement.setLong(10, playerDTO.getLastTimeAdded());
                preparedStatement.setLong(11, playerDTO.getLastKeepInventory());
                preparedStatement.setString(12, playerDTO.getDeathLocation().getWorld().getName());
                preparedStatement.setDouble(13, playerDTO.getDeathLocation().getX());
                preparedStatement.setDouble(14, playerDTO.getDeathLocation().getY());
                preparedStatement.setDouble(15, playerDTO.getDeathLocation().getZ());
                preparedStatement.setFloat(16, playerDTO.getDeathLocation().getYaw());
                preparedStatement.setFloat(17, playerDTO.getDeathLocation().getPitch());
                preparedStatement.setString(18, playerDTO.getLogoutLocation().getWorld().getName());
                preparedStatement.setDouble(19, playerDTO.getLogoutLocation().getX());
                preparedStatement.setDouble(20, playerDTO.getLogoutLocation().getY());
                preparedStatement.setDouble(21, playerDTO.getLogoutLocation().getZ());
                preparedStatement.setFloat(22, playerDTO.getLogoutLocation().getYaw());
                preparedStatement.setFloat(23, playerDTO.getLogoutLocation().getPitch());
                preparedStatement.setString(24, playerDTO.getTeleportLocation().getWorld().getName());
                preparedStatement.setDouble(25, playerDTO.getTeleportLocation().getX());
                preparedStatement.setDouble(26, playerDTO.getTeleportLocation().getY());
                preparedStatement.setDouble(27, playerDTO.getTeleportLocation().getZ());
                preparedStatement.setFloat(28, playerDTO.getTeleportLocation().getYaw());
                preparedStatement.setFloat(29, playerDTO.getTeleportLocation().getPitch());
                preparedStatement.setDouble(30, playerDTO.getBookEnchantedWithoutMending());
                preparedStatement.setDouble(31, playerDTO.getWoodenEnchantedWithoutMending());
                preparedStatement.setDouble(32, playerDTO.getStoneEnchantedWithoutMending());
                preparedStatement.setDouble(33, playerDTO.getIronEnchantedWithoutMending());
                preparedStatement.setDouble(34, playerDTO.getGoldEnchantedWithoutMending());
                preparedStatement.setDouble(35, playerDTO.getDiamondEnchantedWithoutMending());
                preparedStatement.setDouble(36, playerDTO.getNetheriteEnchantedWithoutMending());
                preparedStatement.setDouble(37, playerDTO.getMarineEnchantedWithoutMending());
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to update player", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            logSQLError("Failed to update player", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerBalance(String uuid, BigDecimal balance) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET balance = ? WHERE uuid = ?")) {
                preparedStatement.setBigDecimal(1, balance);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to update player", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update player balance", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerFlytime(String uuid, long flytime) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET fly_time = ? WHERE uuid = ?")) {
                preparedStatement.setLong(1, flytime);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to update player", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update player flytime", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerLastFlyTimeAdded(String uuid, long lastTimeAdded) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET last_fly_time_added = ? WHERE uuid = ?")) {
                preparedStatement.setLong(1, lastTimeAdded);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to update player", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update player last time added", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerLastKeepInventory(String uuid, long lastKeepInventory) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET last_keep_inventory = ? WHERE uuid = ?")) {
                preparedStatement.setLong(1, lastKeepInventory);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to update player", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update player last keep inventory", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerLoginTime(String uuid, long loginTime) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET login_timestamp = ? WHERE uuid = ?")) {
                preparedStatement.setLong(1, loginTime);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to update player", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update player login time", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerTeleportInfo(String uuid, Location location, Long timestamp) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET teleport_location_x = ?, teleport_location_y = ?, teleport_location_z = ?, teleport_location_world = ?, teleport_location_yaw = ?, teleport_location_pitch = ?, teleport_timestamp = ? WHERE uuid = ?")) {
                preparedStatement.setDouble(1, location.getX());
                preparedStatement.setDouble(2, location.getY());
                preparedStatement.setDouble(3, location.getZ());
                preparedStatement.setString(4, location.getWorld().getName());
                preparedStatement.setFloat(5, location.getYaw());
                preparedStatement.setFloat(6, location.getPitch());
                preparedStatement.setString(7, uuid);
                preparedStatement.setDate(8, new Date(timestamp));
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to update player", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update player teleport location", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerDeathInfo(String uuid, Location location, Long timestamp) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET death_location_x = ?, death_location_y = ?, death_location_z = ?, death_location_world = ?, death_location_yaw = ?, death_location_pitch = ?, death_timestamp = ? WHERE uuid = ?")) {
                preparedStatement.setDouble(1, location.getX());
                preparedStatement.setDouble(2, location.getY());
                preparedStatement.setDouble(3, location.getZ());
                preparedStatement.setString(4, location.getWorld().getName());
                preparedStatement.setFloat(5, location.getYaw());
                preparedStatement.setFloat(6, location.getPitch());
                preparedStatement.setString(7, uuid);
                preparedStatement.setDate(8, new Date(timestamp));
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to update player", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update player death location", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerLogoutInfo(String uuid, Location location, Long timestamp) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET logout_location_x = ?, logout_location_y = ?, logout_location_z = ?, logout_location_world = ?, logout_location_yaw = ?, logout_location_pitch = ?, logout_timestamp = ? WHERE uuid = ?")) {
                preparedStatement.setDouble(1, location.getX());
                preparedStatement.setDouble(2, location.getY());
                preparedStatement.setDouble(3, location.getZ());
                preparedStatement.setString(4, location.getWorld().getName());
                preparedStatement.setFloat(5, location.getYaw());
                preparedStatement.setFloat(6, location.getPitch());
                preparedStatement.setString(7, uuid);
                preparedStatement.setDate(8, new Date(timestamp));
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to update player", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update player logout location", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerPlayTime(String uuid, long playTime) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET total_playtime = ? WHERE uuid = ?")) {
                preparedStatement.setLong(1, playTime);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to update player", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update player play time", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerBookEnchantedWithoutMending(String uuid, int enchanted) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET book_enchanted_without_mending = ? WHERE uuid = ?")) {
                preparedStatement.setInt(1, enchanted);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to updatePlayerBookEnchantedWithoutMending", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update updatePlayerBookEnchantedWithoutMending", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerWoodenItemsEnchantedWithoutMending(String uuid, int enchanted) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET wooden_enchanted_without_mending = ? WHERE uuid = ?")) {
                preparedStatement.setInt(1, enchanted);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to updatePlayerWoodenItemEnchantedWithoutMending", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update updatePlayerWoodenItemEnchantedWithoutMending", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerStoneItemsEnchantedWithoutMending(String uuid, int enchanted) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET stone_enchanted_without_mending = ? WHERE uuid = ?")) {
                preparedStatement.setInt(1, enchanted);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to updatePlayerStoneItemEnchantedWithoutMending", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update updatePlayerStoneItemEnchantedWithoutMending", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerIronItemsEnchantedWithoutMending(String uuid, int enchanted) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET iron_enchanted_without_mending = ? WHERE uuid = ?")) {
                preparedStatement.setInt(1, enchanted);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to updatePlayerIronItemEnchantedWithoutMending", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update updatePlayerIronItemEnchantedWithoutMending", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerGoldenItemsEnchantedWithoutMending(String uuid, int enchanted) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET golden_enchanted_without_mending = ? WHERE uuid = ?")) {
                preparedStatement.setInt(1, enchanted);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to updatePlayerGoldenItemEnchantedWithoutMending", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update updatePlayerGoldenItemEnchantedWithoutMending", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerDiamondItemsEnchantedWithoutMending(String uuid, int enchanted) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET diamond_enchanted_without_mending = ? WHERE uuid = ?")) {
                preparedStatement.setInt(1, enchanted);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to updatePlayerDiamondItemEnchantedWithoutMending", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update updatePlayerDiamondItemEnchantedWithoutMending", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerNetheriteItemsEnchantedWithoutMending(String uuid, int enchanted) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET netherite_enchanted_without_mending = ? WHERE uuid = ?")) {
                preparedStatement.setInt(1, enchanted);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to updatePlayerNetheriteItemEnchantedWithoutMending", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update updatePlayerNetheriteItemEnchantedWithoutMending", ex);
            return false;
        }
        return false;
    }

    public boolean updatePlayerMarineItemsEnchantedWithoutMending(String uuid, int enchanted) {
        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET marine_enchanted_without_mending = ? WHERE uuid = ?")) {
                preparedStatement.setInt(1, enchanted);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to updatePlayerMarineItemEnchantedWithoutMending", ex);
                connection.rollback();
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update updatePlayerMarineItemEnchantedWithoutMending", ex);
            return false;
        }
        return false;
    }

}

package ru.somniumcraft.somniumlib.Database.Data.Data.SharedDatabase;

import ru.somniumcraft.somniumlib.Database.Data.Objects.SharedDatabase.SharedPlayerDTO;
import ru.somniumcraft.somniumlib.Database.Data.Util.PluginDataHolder;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SharedPlayerData extends PluginDataHolder {

    public SharedPlayerData() {
        super(SomniumLib.getInstance().getSharedDatabaseConnector(), SomniumLib.getInstance().getServerDatabaseConnector());
    }

    public Optional<SharedPlayerDTO> getPlayer(String uuid) {

        try (Connection connection = getSharedConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM players WHERE uuid = ?")) {
                preparedStatement.setString(1, uuid);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(new SharedPlayerDTO(
                                resultSet.getString("uuid"),
                                resultSet.getString("name"),
                                resultSet.getString("skin_url"),
                                resultSet.getString("join_message"),
                                resultSet.getString("leave_message")
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

    // get optional list of all players from database
    public Optional<List<SharedPlayerDTO>> getPlayers() {

        try (Connection connection = getSharedConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM players")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<SharedPlayerDTO> players = new ArrayList<>();
                    while (resultSet.next()) {
                        players.add(new SharedPlayerDTO(
                                resultSet.getString("uuid"),
                                resultSet.getString("name"),
                                resultSet.getString("skin_url"),
                                resultSet.getString("join_message"),
                                resultSet.getString("leave_message")
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

    // update join_message by uuid as transaction
    public boolean updateJoinMessage(String uuid, String joinMessage) {

        try (Connection connection = getSharedConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET join_message = ? WHERE uuid = ?")) {
                preparedStatement.setString(1, joinMessage);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to update join message", ex);
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        } catch (SQLException ex) {
            logSQLError("Failed to update join message", ex);
            return false;
        }
    }

    // update leave_message by uuid as transaction
    public boolean updateLeaveMessage(String uuid, String leaveMessage) {

        try (Connection connection = getSharedConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET leave_message = ? WHERE uuid = ?")) {
                preparedStatement.setString(1, leaveMessage);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to update leave message", ex);
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        } catch (SQLException ex) {
            logSQLError("Failed to update leave message", ex);
            return false;
        }
    }

    // create new player in database as transaction
    public boolean createPlayer(SharedPlayerDTO player) {

        try (Connection connection = getSharedConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO players (uuid, name, skin_url, join_message, leave_message) VALUES (?, ?, ?, ?, ?)")) {
                preparedStatement.setString(1, player.getUuid());
                preparedStatement.setString(2, player.getName());
                preparedStatement.setString(3, player.getSkinUrl());
                preparedStatement.setString(4, player.getJoinMessage());
                preparedStatement.setString(5, player.getLeaveMessage());
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

    // update player by uuid as transaction
    public boolean updatePlayer(SharedPlayerDTO player) {

        try (Connection connection = getSharedConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET name = ?, skin_url = ?, join_message = ?, leave_message = ? WHERE uuid = ?")) {
                preparedStatement.setString(1, player.getName());
                preparedStatement.setString(2, player.getSkinUrl());
                preparedStatement.setString(3, player.getJoinMessage());
                preparedStatement.setString(4, player.getLeaveMessage());
                preparedStatement.setString(5, player.getUuid());
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to update player", ex);
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        } catch (SQLException ex) {
            logSQLError("Failed to update player", ex);
            return false;
        }
    }


}

package ru.somniumcraft.somniumlib.Database.Data.Data.ServerDatabase;

import ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase.PlayerKitsDTO;
import ru.somniumcraft.somniumlib.Database.Data.Util.PluginDataHolder;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PlayerKitsData extends PluginDataHolder {

    public PlayerKitsData() {
        super(SomniumLib.getInstance().getSharedDatabaseConnector(), SomniumLib.getInstance().getServerDatabaseConnector());
    }

    public Optional<PlayerKitsDTO> getPlayerKitsData(String uuid, int kitId) {

        try (Connection connection = getServerConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM player_kits WHERE kit_id = ? AND player_uuid = ?")) {
                preparedStatement.setInt(1, kitId);
                preparedStatement.setString(2, uuid);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(new PlayerKitsDTO(
                                resultSet.getString("uuid"),
                                resultSet.getInt("kit_id"),
                                resultSet.getInt("used_times"),
                                resultSet.getInt("last_use_timestamp")
                        ));
                    }
                }

            } catch (SQLException ex) {
                logSQLError("Failed to get player kits data", ex);
                return Optional.empty();
            }

        } catch (SQLException ex) {
            logSQLError("Failed to get player kits data", ex);
            return Optional.empty();
        }

        return Optional.empty();
    }

    public boolean createPlayerKitsData(PlayerKitsDTO playerKitsDTO) {

        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO player_kits (player_uuid, kit_id, used_times, last_use_timestamp) VALUES (?, ?, ?, ?)")) {
                preparedStatement.setString(1, playerKitsDTO.getPlayerUUID());
                preparedStatement.setInt(2, playerKitsDTO.getKitId());
                preparedStatement.setInt(3, playerKitsDTO.getUsedTimes());
                preparedStatement.setInt(4, playerKitsDTO.getLastUseTimestamp());
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to create player kit data", ex);
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

        } catch (SQLException ex) {
            logSQLError("Failed to create player kit data", ex);
            return false;
        }

    }

    public boolean removePlayerKitsData(String uuid, int kitId) {

        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM player_kits WHERE kit_id = ? AND player_uuid = ?")) {
                preparedStatement.setInt(1, kitId);
                preparedStatement.setString(2, uuid);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to remove player kit data", ex);
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

        } catch (SQLException ex) {
            logSQLError("Failed to remove player kit data", ex);
            return false;
        }

    }

    public boolean updatePlayerKitsData(PlayerKitsDTO playerKitsDTO) {

        try (Connection connection = getServerConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE player_kits SET used_times = ?, last_use_timestamp = ? WHERE kit_id = ? AND player_uuid = ?")) {
                preparedStatement.setInt(1, playerKitsDTO.getUsedTimes());
                preparedStatement.setInt(2, playerKitsDTO.getLastUseTimestamp());
                preparedStatement.setInt(3, playerKitsDTO.getKitId());
                preparedStatement.setString(4, playerKitsDTO.getPlayerUUID());
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                logSQLError("Failed to update player kit data", ex);
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

        } catch (SQLException ex) {
            logSQLError("Failed to update player kit data", ex);
            return false;
        }

    }
}

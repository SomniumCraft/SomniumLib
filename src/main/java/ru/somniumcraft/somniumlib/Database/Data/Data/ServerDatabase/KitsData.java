package ru.somniumcraft.somniumlib.Database.Data.Data.ServerDatabase;

import ru.somniumcraft.somniumlib.Database.Data.Data.SusUtils;
import ru.somniumcraft.somniumlib.Database.Data.Objects.ServerDatabase.KitsDTO;
import ru.somniumcraft.somniumlib.Database.Data.Util.PluginDataHolder;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KitsData extends PluginDataHolder {

    public KitsData() {
        super(SomniumLib.getInstance().getSharedDatabaseConnector(), SomniumLib.getInstance().getServerDatabaseConnector());
    }

    public Optional<KitsDTO> getKit(String name) {

        try (Connection connection = getSharedConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM kits WHERE name = ?")) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(new KitsDTO(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("description"),
                                resultSet.getString("item_icon"),
                                resultSet.getInt("price"),
                                resultSet.getInt("cooldown"),
                                resultSet.getString("permission"),
                                SusUtils.jsonToItemStackList(resultSet.getString("items")),
                                resultSet.getBoolean("is_default"),
                                resultSet.getBoolean("is_active"),
                                resultSet.getInt("experience_cost"),
                                resultSet.getInt("max_use_times"),
                                resultSet.getString("activation_command")
                        ));
                    }
                }
            }

        } catch (SQLException ex) {
            logSQLError("Failed to get kit", ex);
            return Optional.empty();
        }

        return Optional.empty();
    }

    // get kits

    public Optional<List<KitsDTO>> getKits() {

        try (Connection connection = getSharedConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM kits")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<KitsDTO> kits = new ArrayList<>();
                    while (resultSet.next()) {
                        kits.add(new KitsDTO(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("description"),
                                resultSet.getString("item_icon"),
                                resultSet.getInt("price"),
                                resultSet.getInt("cooldown"),
                                resultSet.getString("permission"),
                                SusUtils.jsonToItemStackList(resultSet.getString("items")),
                                resultSet.getBoolean("is_default"),
                                resultSet.getBoolean("is_active"),
                                resultSet.getInt("experience_cost"),
                                resultSet.getInt("max_use_times"),
                                resultSet.getString("activation_command")
                        ));
                    }
                    return Optional.of(kits);
                }
            }

        } catch (SQLException ex) {
            logSQLError("Failed to get kits", ex);
            return Optional.empty();
        }

    }

    // get default kit

    public Optional<KitsDTO> getDefaultKit() {

        try (Connection connection = getSharedConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM kits WHERE is_default = ?")) {
                preparedStatement.setBoolean(1, true);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(new KitsDTO(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("description"),
                                resultSet.getString("item_icon"),
                                resultSet.getInt("price"),
                                resultSet.getInt("cooldown"),
                                resultSet.getString("permission"),
                                SusUtils.jsonToItemStackList(resultSet.getString("items")),
                                resultSet.getBoolean("is_default"),
                                resultSet.getBoolean("is_active"),
                                resultSet.getInt("experience_cost"),
                                resultSet.getInt("max_use_times"),
                                resultSet.getString("activation_command")
                        ));
                    }
                }
            }

        } catch (SQLException ex) {
            logSQLError("Failed to get default kit", ex);
            return Optional.empty();
        }

        return Optional.empty();
    }

    // create kit using transaction

    public boolean createKit(KitsDTO kit) {

        try (Connection connection = getSharedConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO kits (name, description, item_icon, price, cooldown, permission, items, is_default, is_active, experience_cost, max_use_times, activation_command) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                preparedStatement.setString(1, kit.getName());
                preparedStatement.setString(2, kit.getDescription());
                preparedStatement.setString(3, kit.getItemIcon());
                preparedStatement.setInt(4, kit.getPrice());
                preparedStatement.setInt(5, kit.getCooldown());
                preparedStatement.setString(6, kit.getPermission());
                preparedStatement.setString(7, SusUtils.itemStackListToJson(kit.getItems()));
                preparedStatement.setBoolean(8, kit.isDefault());
                preparedStatement.setBoolean(9, kit.isActive());
                preparedStatement.setInt(10, kit.getExperienceCost());
                preparedStatement.setInt(11, kit.getMaxUseTimes());
                preparedStatement.setString(12, kit.getActivationCommand());
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                connection.rollback();
                connection.setAutoCommit(true);
                logSQLError("Failed to create kit", ex);
                return false;
            }
        } catch (SQLException ex) {
            logSQLError("Failed to create kit", ex);
            return false;
        }

    }

    // update kit using transaction

    public boolean updateKit(KitsDTO kit) {

        try (Connection connection = getSharedConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE kits SET name = ?, description = ?, item_icon = ?, price = ?, cooldown = ?, permission = ?, items = ?, is_default = ?, is_active = ?, experience_cost = ?, max_use_times = ?, activation_command = ? WHERE id = ?")) {

                if (kit.isDefault()) {
                    try (PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE kits SET is_default = ? WHERE id != ?")) {
                        preparedStatement2.setBoolean(1, false);
                        preparedStatement2.setInt(2, kit.getId());
                        preparedStatement2.executeUpdate();
                    } catch (SQLException ex) {
                        connection.rollback();
                        connection.setAutoCommit(true);
                        logSQLError("Failed to update kit", ex);
                        return false;
                    }
                }

                preparedStatement.setString(1, kit.getName());
                preparedStatement.setString(2, kit.getDescription());
                preparedStatement.setString(3, kit.getItemIcon());
                preparedStatement.setInt(4, kit.getPrice());
                preparedStatement.setInt(5, kit.getCooldown());
                preparedStatement.setString(6, kit.getPermission());
                preparedStatement.setString(7, SusUtils.itemStackListToJson(kit.getItems()));
                preparedStatement.setBoolean(8, kit.isDefault());
                preparedStatement.setBoolean(9, kit.isActive());
                preparedStatement.setInt(10, kit.getExperienceCost());
                preparedStatement.setInt(11, kit.getMaxUseTimes());
                preparedStatement.setString(12, kit.getActivationCommand());
                preparedStatement.setInt(13, kit.getId());
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                connection.rollback();
                connection.setAutoCommit(true);
                logSQLError("Failed to update kit", ex);
                return false;
            }
        } catch (SQLException ex) {
            logSQLError("Failed to update kit", ex);
            return false;
        }

    }

    // delete kit using transaction

    public boolean deleteKit(int id) {

        try (Connection connection = getSharedConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM kits WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException ex) {
                connection.rollback();
                connection.setAutoCommit(true);
                logSQLError("Failed to delete kit", ex);
                return false;
            }
        } catch (SQLException ex) {
            logSQLError("Failed to delete kit", ex);
            return false;
        }

    }



}

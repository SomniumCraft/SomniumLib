package ru.somniumcraft.somniumlib.IUI.View;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface IUIView<D> extends Listener {

    void setItems(D data);
    void open(Player player, D data);
    void close(Player player);
    @EventHandler
    void handleClick(InventoryClickEvent event);
}

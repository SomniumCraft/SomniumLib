package ru.somniumcraft.somniumlib.IUI.Element;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.function.Consumer;

public interface IUIElement {

    ItemStack getItem();
    void setItem(ItemStack item);
    Optional<ClickHandler> getClickHandler();
    void setClickHandler(ClickHandler handler);
}

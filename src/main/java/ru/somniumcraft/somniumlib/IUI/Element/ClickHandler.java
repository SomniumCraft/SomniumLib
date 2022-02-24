package ru.somniumcraft.somniumlib.IUI.Element;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface ClickHandler {
    void click(Player player, ClickInformation click);
}
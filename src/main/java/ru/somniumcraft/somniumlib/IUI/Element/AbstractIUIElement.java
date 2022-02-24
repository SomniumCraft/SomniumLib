package ru.somniumcraft.somniumlib.IUI.Element;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public abstract class AbstractIUIElement {
    protected ItemStack item;
    protected int slotIndex;
    protected Boolean isLocked;
    protected Consumer<InventoryClickEvent> clickHandler;

    public AbstractIUIElement setClickHandler(Consumer<InventoryClickEvent> clickHandler) {
        this.clickHandler = clickHandler;
        return this;
    }

    public ItemStack getItem() {
        return item;
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public Consumer<InventoryClickEvent> getClickHandler() {
        return clickHandler;
    }
}

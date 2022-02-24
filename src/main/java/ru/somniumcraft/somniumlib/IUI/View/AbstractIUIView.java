package ru.somniumcraft.somniumlib.IUI.View;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import ru.somniumcraft.somniumlib.IUI.Element.AbstractIUIElement;
import ru.somniumcraft.somniumlib.IUI.Presenter.AbstractIUIPresenter;

import java.util.List;

public abstract class AbstractIUIView<P extends AbstractIUIPresenter, D> implements IUIView<D>, InventoryHolder, Listener {
    protected P presenter;
    protected Inventory inventory;
    protected List<AbstractIUIElement> IUIElements;
    protected int size;
    protected TextComponent name;

    public AbstractIUIView(P presenter, int rowCount, TextComponent name) throws IllegalArgumentException{
        this.presenter = presenter;

        if(rowCount < 1)
            throw new IllegalArgumentException("Row count cannot be less than 1");

        if(rowCount > 6)
            throw new IllegalArgumentException("Row count cannot be more than 6");

        this.size = rowCount * 9;
        this.name = name;
    }

    protected abstract void setItems(D data);

    public void open(Player player, D data){
        inventory = Bukkit.createInventory(null,size,name);
        setItems(data);
        player.openInventory(inventory);
    }

    @EventHandler
    public void handleClick(InventoryClickEvent event){
        if(!event.getInventory().equals(this.inventory))
            return;

        for (AbstractIUIElement element: IUIElements) {
            if(event.getSlot() == element.getSlotIndex())
                if(element.getLocked())
                    event.setCancelled(true);

                element.getClickHandler().accept(event);
        }
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}


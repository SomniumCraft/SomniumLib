package ru.somniumcraft.somniumlib.IUI.Presenter;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import ru.somniumcraft.somniumlib.IUI.View.AbstractPaginatedIUIView;

import java.util.List;

public class AbstractPaginatedIUIPresnter extends AbstractIUIPresenter{
    List<Object> data;
    protected int page = 0;
    protected int maxItemsPerPage = 45;
    protected int index = 0;

    public AbstractPaginatedIUIPresnter(Player player) {
        this.player = player;
    }




    @Override
    public void open() {

    }
}

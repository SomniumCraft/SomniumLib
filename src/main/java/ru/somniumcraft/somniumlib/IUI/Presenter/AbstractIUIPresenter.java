package ru.somniumcraft.somniumlib.IUI.Presenter;

import org.bukkit.entity.Player;
import ru.somniumcraft.somniumlib.IUI.View.AbstractIUIView;

public abstract class AbstractIUIPresenter {
    protected AbstractIUIView view;
    protected Player player;

    public abstract void open();

}

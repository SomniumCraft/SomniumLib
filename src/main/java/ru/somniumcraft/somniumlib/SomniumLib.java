package ru.somniumcraft.somniumlib;

import org.bukkit.plugin.java.JavaPlugin;

public final class SomniumLib extends JavaPlugin {

    private static SomniumLib instance;
    public static SomniumLib getInstance(){
        return instance;
    }

    @Override
    public void onLoad(){
        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

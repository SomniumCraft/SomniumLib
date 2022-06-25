package ru.somniumcraft.somniumlib.BasePlugin;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import ru.somniumcraft.somniumlib.Config.ConfigManager;
import ru.somniumcraft.somniumlib.Config.ConfigUtils;

public abstract class SomniumPlugin extends JavaPlugin {

    private SomniumPlugin pluginInstance;
    @Getter
    private ConfigManager configManager;

    public abstract void Load();
    public abstract void Enable();
    public abstract void Disable();

    @Override
    public void onLoad(){
        pluginInstance = this;
        Load();
    }

    @Override
    public void onEnable(){
        this.configManager = new ConfigManager(pluginInstance);
        configManager.Reload();
        Enable();
    }

    @Override
    public void onDisable(){
        Disable();
    }
}

package ru.somniumcraft.somniumlib.BasePlugin;

import org.bukkit.plugin.java.JavaPlugin;
import ru.somniumcraft.somniumlib.Config.ConfigManager;
import ru.somniumcraft.somniumlib.Config.ConfigUtils;

public abstract class SomniumPlugin extends JavaPlugin {
    private SomniumPlugin pluginInstance;
    private ConfigManager configManager;
    private ConfigUtils configUtils;

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
        this.configUtils = new ConfigUtils();
        this.configManager = new ConfigManager(pluginInstance, configUtils);
        configManager.Reload();
        Enable();
    }

    @Override
    public void onDisable(){
        Disable();
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}

package ru.somniumcraft.somniumlib.Config;

import ru.somniumcraft.somniumlib.BasePlugin.SomniumPlugin;

import java.io.File;

public class ConfigManager {

    private final SomniumPlugin plugin;
    private final ConfigUtils configUtils;

    public ConfigManager(SomniumPlugin plugin, ConfigUtils configUtils) {
        this.plugin = plugin;
        this.configUtils = configUtils;
    }

    public void Reload() {
        var fields = this.plugin.getClass().getDeclaredFields();
        for(var field : fields){
            var clazz = field.getType();
            if(clazz.isAnnotationPresent(SomniumConfig.class)){
                var fileName = clazz.getSimpleName() + ".yml";
                var file = new File(this.plugin.getDataFolder(), fileName);
                field.setAccessible(true);
                var aboba = configUtils.load(file,clazz);
                try {
                    field.set(plugin, aboba);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

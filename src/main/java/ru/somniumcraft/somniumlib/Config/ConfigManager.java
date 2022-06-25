package ru.somniumcraft.somniumlib.Config;

import ru.somniumcraft.somniumlib.BasePlugin.SomniumPlugin;

import java.io.File;

public class ConfigManager {

    private final SomniumPlugin plugin;

    public ConfigManager(SomniumPlugin plugin) {
        this.plugin = plugin;
    }

    public void Reload() {
        var fields = this.plugin.getClass().getDeclaredFields();
        for(var field : fields){
            var clazz = field.getType();
            if(clazz.isAnnotationPresent(SomniumConfig.class)){
                var fileName = clazz.getSimpleName() + ".yml";
                var file = new File(this.plugin.getDataFolder(), fileName);
                field.setAccessible(true);
                var newConfig = ConfigUtils.load(file,clazz);
                try {
                    field.set(plugin, newConfig);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

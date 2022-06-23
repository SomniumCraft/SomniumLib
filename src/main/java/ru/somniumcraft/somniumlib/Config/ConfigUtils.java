package ru.somniumcraft.somniumlib.Config;

import org.apache.commons.io.FileUtils;
import ru.somniumcraft.somniumlib.SomniumLib;
import ru.somniumcraft.somniumlib.Util.JacksonUtils;

import java.io.File;
import java.io.IOException;

public class ConfigUtils {

    private final SomniumLib plugin;
    private final JacksonUtils jacksonUtils;

    public ConfigUtils() {
        this.plugin = SomniumLib.getInstance();
        this.jacksonUtils = plugin.getJacksonUtils();
    }

    public void save(File target, Object value) {
        String data = jacksonUtils.objectToYaml(value);
        try {
            FileUtils.writeStringToFile(target, data);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void saveIfNotExists(File target, Object value) {
        String data = jacksonUtils.objectToYaml(value);
        try {
            FileUtils.writeStringToFile(target, data);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public <T> T load(File target, Class<T> tClass) {
        if (target.exists()) {
            String data = null;
            try {
                data = FileUtils.readFileToString(target);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return jacksonUtils.yamlToObject(data, tClass);
        }
        T t = null;
        try {
            t = tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        save(target, t);
        return t;
    }
}

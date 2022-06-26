package ru.somniumcraft.somniumlib.Util;

import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import ru.somniumcraft.somniumlib.SomniumLib;

@UtilityClass
public class PersistentDataUtils {

    public <D extends PersistentDataType<T, Z>, T, Z> boolean hasPersistentData (PersistentDataContainer data, String key, D type) {
        return data.has(new NamespacedKey(SomniumLib.getInstance(), key), type);
    }

    public <D extends PersistentDataType<T, Z>, T, Z> void setPersistentData (PersistentDataContainer data, String key, D type, Z value) {
        data.set(new NamespacedKey(SomniumLib.getInstance(), key), type, type.getComplexType().cast(value));
    }

    public void removePersistentData (PersistentDataContainer data, String key) {
        data.remove(new NamespacedKey(SomniumLib.getInstance(), key));
    }

    public <D extends PersistentDataType<T, Z>, T, Z> Z getPersistentData(PersistentDataContainer data, String key, D type) {
        return data.get(new NamespacedKey(SomniumLib.getInstance(), key), type);
    }

}

package ru.somniumcraft.somniumlib.Database.Data.Data;

import com.google.gson.Gson;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SusUtils {

    private static final Gson gson = new Gson();

    public static String itemStackListToJson(List<ItemStack> itemStackList) {
        return gson.toJson(itemStackList.stream().map(x -> gson.toJson(x.serialize())).collect(Collectors.toList()));
    }

    public static List<ItemStack> jsonToItemStackList(String json) {
        List<HashMap<String, Object>> hashMapList = gson.fromJson(json, List.class);
        return hashMapList.stream().map(x -> ItemStack.deserialize(x)).collect(Collectors.toList());
    }

    public static String potionEffectsToJson(List<PotionEffect> potionEffects) {
        return gson.toJson(potionEffects.stream().map(x -> gson.toJson(x.serialize())).collect(Collectors.toList()));
    }

    public static List<PotionEffect> jsonToPotionEffects(String json) {
        List<HashMap<String, Object>> hashMapList = gson.fromJson(json, List.class);
        return hashMapList.stream().map(x -> new PotionEffect(x)).collect(Collectors.toList());
    }
}

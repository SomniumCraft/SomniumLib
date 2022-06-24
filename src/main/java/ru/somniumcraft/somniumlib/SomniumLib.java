package ru.somniumcraft.somniumlib;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.somniumcraft.somniumlib.BasePlugin.SomniumPlugin;
import ru.somniumcraft.somniumlib.Config.SharedConfig;
import ru.somniumcraft.somniumlib.Util.*;

public class SomniumLib extends SomniumPlugin {
    private static SomniumLib instanse;
    private FlatFileUtils flatFileUtils;
    private ImageUtils imageUtils;
    private JacksonUtils jacksonUtils;
    private MessageUtils messageUtils;
    private SafeLander safeLander;
    private PersistentDataUtils persistentDataUtils;

    private SharedConfig sharedConfig;

    public static SomniumLib getInstance() {
        return instanse;
    }

    @Override
    public void Load(){
        instanse = this;
        flatFileUtils = new FlatFileUtils();
        imageUtils = new ImageUtils();
        jacksonUtils = new JacksonUtils();
        messageUtils = new MessageUtils();
        safeLander = new SafeLander();
        persistentDataUtils = new PersistentDataUtils();
    }

    @Override
    public void Enable() {
    }

    @Override
    public void Disable() {
    }

    public FlatFileUtils getFlatFileUtils() {
        return flatFileUtils;
    }

    public ImageUtils getImageUtils() {
        return imageUtils;
    }

    public JacksonUtils getJacksonUtils() {
        return jacksonUtils;
    }

    public MessageUtils getMessageUtils() {
        return messageUtils;
    }

    public SafeLander getSafeLander() {
        return safeLander;
    }

    public PersistentDataUtils getPersistentDataUtils() {
        return persistentDataUtils;
    }

    public SharedConfig getSharedConfig() { return sharedConfig; }
}

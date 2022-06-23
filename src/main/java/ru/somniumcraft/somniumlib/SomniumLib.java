package ru.somniumcraft.somniumlib;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.somniumcraft.somniumlib.BasePlugin.SomniumPlugin;
import ru.somniumcraft.somniumlib.Util.*;

public final class SomniumLib extends SomniumPlugin {

    private FlatFileUtils flatFileUtils;
    private ImageUtils imageUtils;
    private JacksonUtils jacksonUtils;
    private MessageUtils messageUtils;
    private SafeLander safeLander;

    public static SomniumLib getInstance() {
        return (SomniumLib)pluginInstance;
    }

    @Override
    public void Load(){
        flatFileUtils = new FlatFileUtils();
        imageUtils = new ImageUtils();
        jacksonUtils = new JacksonUtils();
        messageUtils = new MessageUtils();
        safeLander = new SafeLander();
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
}

package ru.somniumcraft.somniumlib;

import lombok.Getter;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.somniumcraft.somniumlib.BasePlugin.SomniumPlugin;
import ru.somniumcraft.somniumlib.Config.SharedConfig;
import ru.somniumcraft.somniumlib.Util.*;

public class SomniumLib extends SomniumPlugin {
    private static SomniumLib instanse;

    @Getter
    private SharedConfig sharedConfig;

    public static SomniumLib getInstance() {
        return instanse;
    }

    @Override
    public void Load() {
        instanse = this;
    }

    @Override
    public void Enable() {
    }

    @Override
    public void Disable() {
    }
}

package ru.somniumcraft.somniumlib.Util;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.somniumcraft.somniumlib.SomniumLib;
import net.kyori.adventure.bossbar.BossBar;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class MessageUtils {

    private  final char COLOR_CHAR = '\u00A7';
    private  final Pattern COLOR_PATTERN = Pattern.compile("#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})|\\{#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})\\}|§(.)|&(.)");

    public  Component translateColorCodes(String message)
    {
        final Pattern hexPattern = Pattern.compile("\\{#([A-Fa-f0-9]{6})\\}" );
        Matcher matcher = hexPattern.matcher(message);
        StringBuilder buffer = new StringBuilder(message.length() + 4 * 8);
        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, '&' + "x"
                    + '&' + group.charAt(0) + '&' + group.charAt(1)
                    + '&' + group.charAt(2) + '&' + group.charAt(3)
                    + '&' + group.charAt(4) + '&' + group.charAt(5)
            );
        }

        return LegacyComponentSerializer.legacySection().deserialize(ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString()));
    }

    public  String removeColorCodes(String message) {
        Matcher matcher = COLOR_PATTERN.matcher(message);
        if(matcher.find()) {
            message = matcher.replaceAll("");
        }
        return message;
    }

    public  void sendChatMessage(String message, CommandSender... senders) {
        Component component = translateColorCodes(message);
        sendChatMessage(component, Arrays.asList(senders));
    }

    public  void sendChatMessage(String message, Collection<? extends CommandSender> senders) {
        Component component = translateColorCodes(message);
        sendChatMessage(component, senders);
    }

    public  void sendChatMessage(Component component, CommandSender... senders) {
        sendChatMessage(component, Arrays.asList(senders));
    }

    public  void sendChatMessage(Component component, Collection<? extends CommandSender> senders) {
        for (CommandSender sender: senders) {
            sender.sendMessage(component);
        }
    }


    public  void sendActionBarMessage(String message, CommandSender... senders) {
        Component component = translateColorCodes(message);
        sendActionBarMessage(component, senders);
    }

    public  void sendActionBarMessage(String message, Collection<? extends CommandSender> senders) {
        Component component = translateColorCodes(message);
        sendActionBarMessage(component, senders);
    }

    public  void sendActionBarMessage(Component component, CommandSender... senders) {
        sendActionBarMessage(component,Arrays.asList(senders));
    }

    public  void sendActionBarMessage(Component component, Collection<? extends CommandSender> senders) {
        for (CommandSender sender: senders) {
            if (sender instanceof Player) {
                sender.sendActionBar(component);
            }
        }
    }

    public  void sendBossBarMessage(String message, BossBar.Color color, BossBar.Overlay overlay, Long duration, CommandSender... senders) {
        Component component = translateColorCodes(message);
        sendBossBarMessage(component, color, overlay, duration, Arrays.asList(senders));
    }

    public  void sendBossBarMessage(String message, BossBar.Color color, BossBar.Overlay overlay, Long duration, Collection<? extends CommandSender> senders) {
        Component component = translateColorCodes(message);
        sendBossBarMessage(component, color, overlay, duration, senders);
    }

    public  void sendBossBarMessage(Component component, BossBar.Color color, BossBar.Overlay overlay, Long duration, CommandSender... senders) {
        sendBossBarMessage(component, color, overlay, duration, Arrays.asList(senders));
    }

    public  void sendBossBarMessage(Component component, BossBar.Color color, BossBar.Overlay overlay, Long duration, Collection<? extends CommandSender> senders) {
        BossBar bossBar = BossBar.bossBar(component, 1, color, overlay);

        for (CommandSender sender: senders) {
            if (sender instanceof Player) {
                sender.showBossBar(bossBar);
            }

            new BukkitRunnable() {

                Long remainingDuration = duration;

                @Override
                public void run() {
                    float progress = (float) remainingDuration/(float) duration;
                    if (remainingDuration <= 0) {
                        sender.hideBossBar(bossBar);
                        this.cancel();
                    }
                    bossBar.progress(progress);
                    remainingDuration -= 20L;
                }
            }.runTaskTimer(SomniumLib.getInstance(), 0L, 20L);
        }
    }

    public  String getFormattedTime(long milliseconds)
    {
        String time = "";

        long d = Math.floorDiv(milliseconds, 86400000L);
        milliseconds %= 86400000L;
        long h = Math.floorDiv(milliseconds,3600000L);
        milliseconds %= 3600000L;
        long m = Math.floorDiv(milliseconds,60000L);
        milliseconds %= 60000L;
        long s = Math.floorDiv(milliseconds,1000L);

        if (d > 0)
            time += d + "д ";
        if (h > 0)
            time += h + "ч ";
        if (m > 0)
            time += m + "м ";
        if (milliseconds > 0)
            time += s + "c";

        return time;
    }


}

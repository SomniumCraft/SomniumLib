package ru.somniumcraft.somniumlib.Util;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MessageUtils {

    private static final char COLOR_CHAR = '\u00A7';
    private static final Pattern COLOR_PATTERN = Pattern.compile("#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})|\\{#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})\\}|§(.)|&(.)");

    public static String translateHexColorCodes(String message)
    {
        final Pattern hexPattern = Pattern.compile("\\{#([A-Fa-f0-9]{6})\\}" );
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }

        return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
    }

    public static String removeColorCodes(String message) {
        Matcher matcher = COLOR_PATTERN.matcher(message);
        if(matcher.find()) {
            message = matcher.replaceAll("");
        }
        return message;
    }

    public static void sendChatMessage(String message, CommandSender... senders) {
        Component component = Component.text(translateHexColorCodes(message));
        sendChatMessage(component, senders);
    }

    public static void sendChatMessage(Component component, CommandSender... senders) {
        for (CommandSender sender: senders) {
            sender.sendMessage(component);
        }
    }

    public static void sendActionBarMessage(String message, CommandSender... senders) {
        Component component = Component.text(translateHexColorCodes(message));
        sendActionBarMessage(component, senders);
    }

    public static void sendActionBarMessage(Component component, CommandSender... senders) {
        for (CommandSender sender: senders) {
            if (sender instanceof Player) {
                sender.sendActionBar(component);
            }
        }
    }

    public static void sendBossBarMessage(String message, net.kyori.adventure.bossbar.BossBar.Color color, net.kyori.adventure.bossbar.BossBar.Overlay overlay, Long duration, CommandSender... senders)
    {
        Component component = Component.text(translateHexColorCodes(message));
        sendBossBarMessage(component, color, overlay, duration, senders);
    }

    public static void sendBossBarMessage(Component component, net.kyori.adventure.bossbar.BossBar.Color color, net.kyori.adventure.bossbar.BossBar.Overlay overlay, Long duration, CommandSender... senders)
    {

        net.kyori.adventure.bossbar.BossBar bossBar = net.kyori.adventure.bossbar.BossBar.bossBar(component, 1, color, overlay);

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

    public static String getFormattedTime(long seconds)
    {
        String time = "";

        long d = Math.floorDiv(seconds, 86400);
        seconds %= 86400;
        long h = Math.floorDiv(seconds,3600);
        seconds %= 3600;
        long m = Math.floorDiv(seconds,60);
        long s = seconds % 60;

        if (d > 0)
            time += d + "д ";
        if (h > 0)
            time += h + "ч ";
        if (m > 0)
            time += m + "м ";
        if (seconds > 0)
            time += s + "c";

        return time;
    }


}

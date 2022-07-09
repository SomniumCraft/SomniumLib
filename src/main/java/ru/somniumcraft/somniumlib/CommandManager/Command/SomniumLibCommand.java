package ru.somniumcraft.somniumlib.CommandManager.Command;

import dev.jorel.commandapi.CommandAPICommand;
import ru.somniumcraft.somniumlib.SomniumLib;
import ru.somniumcraft.somniumlib.Util.MessageUtils;

import java.util.ArrayList;
import java.util.List;

public class SomniumLibCommand {

    private final SomniumLib somniumlib;

    public SomniumLibCommand() {
        this.somniumlib = SomniumLib.getInstance();

        new CommandAPICommand("somniumlib")
                .withSubcommand(new CommandAPICommand("reload")
                        .withPermission("somniumlib.admin")
                        .executes(((sender, args) -> {
                            somniumlib.getConfigManager().Reload();
                            MessageUtils.sendChatMessage(somniumlib.getSharedConfig().getReload()
                                    .replace("{pluginName}", SomniumLib.getInstance().getName()), sender);
                        }))
                )
                .executes(((sender, args) -> {
                    List<String> message = new ArrayList<String>();

                    message.add("&8⤝⎯⎯⤛⤟ ⤄ ⤠⤜⎯⎯⤞");
                    message.add("");
                    message.add("&f[{#77add6}SomniumLib&f] v" + somniumlib.getDescription().getVersion());
                    message.add("Плагин, разрабатываемый нашей командой чтобы сделать вашу игру на проекте интереснее.");
                    message.add("");
                    message.add("&8⤝⎯⎯⤛⤟ ⤄ ⤠⤜⎯⎯⤞");

                    for(String line : message) {
                        MessageUtils.sendChatMessage(line, sender);
                    }
                })).register();
    }
}

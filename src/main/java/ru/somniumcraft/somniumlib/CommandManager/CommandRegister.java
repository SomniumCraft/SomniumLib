package ru.somniumcraft.somniumlib.CommandManager;

import ru.somniumcraft.somniumlib.CommandManager.Command.SomniumLibCommand;
import ru.somniumcraft.somniumlib.SomniumLib;

import java.util.logging.Level;

public class CommandRegister {
    private final SomniumLib plugin;

    public CommandRegister() {
        plugin = SomniumLib.getInstance();
    }

    public void register(){
        try{
            new SomniumLibCommand();
        }catch(Exception e){
            plugin.getLogger().log(Level.SEVERE, "Error while registering commands", e);
        }
    }
}

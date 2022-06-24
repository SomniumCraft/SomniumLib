package ru.somniumcraft.somniumlib.Config;

@SomniumConfig
public class SharedConfig {
    private String reload = "&f[{#77add6}SomniumRTP&f] Плагин успешно перезагружен";
    private String noPermissions = "{#f9f871}Ошибка! &fУ вас недостаточно прав для использования данной команды.";
    private String playerNotFound = "{#f9f871}Ошибка! &fИгрок не найден.";
    private String playerOutOfRange = "{#f9f871}Ошибка! &fЦель вне зоне доступа";
    private String cooldownMessage = "Подождите ещё {#f9f871}{cooldown} сек";

    private String primaryColor = "#77add6";
    private String secondaryColor = "#f9f871";
    private String globalChatPrefix = "&f[{primaryColor}G&f]";
    private String localChatPrefix = "&f[&7L&f]";
    private String spyChatPrefix = "&f[{#bf1f4c}☄&f]";

    public String getGlobalChatPrefix() {
        return globalChatPrefix;
    }

    public String getLocalChatPrefix() {
        return localChatPrefix;
    }

    public String getSpyChatPrefix() {
        return spyChatPrefix;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public int getLocalChatRange() {
        return localChatRange;
    }

    private int localChatRange = 100;

    public String getReload() {
        return reload;
    }

    public String getNoPermissions() {
        return noPermissions;
    }

    public String getPlayerNotFound() {
        return playerNotFound;
    }

    public String getPlayerOutOfRange() {
        return playerOutOfRange;
    }

    public String getCooldownMessage() {
        return cooldownMessage;
    }
}

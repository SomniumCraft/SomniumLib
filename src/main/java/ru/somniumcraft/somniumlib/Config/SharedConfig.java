package ru.somniumcraft.somniumlib.Config;

@SomniumConfig
public class SharedConfig {
    private String reload = "&f[{#77add6}SomniumRTP&f] Плагин успешно перезагружен";
    private String noPermissions = "{#f9f871}Ошибка! &fУ вас недостаточно прав для использования данной команды.";
    private String playerNotFound = "{#f9f871}Ошибка! &fИгрок не найден.";
    private String playerOutOfRange = "{#f9f871}Ошибка! &fЦель вне зоне доступа";
    private String cooldownMessage = "Подождите ещё {#f9f871}{cooldown} сек";

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

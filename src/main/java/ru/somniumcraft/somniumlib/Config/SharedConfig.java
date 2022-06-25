package ru.somniumcraft.somniumlib.Config;

import lombok.Getter;

@SomniumConfig
public class SharedConfig {
    @Getter
    private String reload = "&f[{#77add6}SomniumRTP&f] Плагин успешно перезагружен";
    @Getter
    private String noPermissions = "{#f9f871}Ошибка! &fУ вас недостаточно прав для использования данной команды.";
    @Getter
    private String playerNotFound = "{#f9f871}Ошибка! &fИгрок не найден.";
    @Getter
    private String playerOutOfRange = "{#f9f871}Ошибка! &fЦель вне зоне доступа";
    @Getter
    private String cooldownMessage = "Подождите ещё {#f9f871}{cooldown} сек";
    @Getter
    private String primaryColor = "#77add6";
    @Getter
    private String secondaryColor = "#f9f871";
    @Getter
    private String globalChatPrefix = "&f[{primaryColor}G&f]";
    @Getter
    private String localChatPrefix = "&f[&7L&f]";
    @Getter
    private String spyChatPrefix = "&f[{#bf1f4c}☄&f]";
}

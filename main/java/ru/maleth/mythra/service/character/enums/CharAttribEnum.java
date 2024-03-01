package ru.maleth.mythra.service.character.enums;

import lombok.Getter;

@Getter
public enum CharAttribEnum {

    STRENGTH("Сила"),
    DEXTERITY("Ловкость"),
    CONSTITUTION("Телосложение"),
    INTELLIGENCE("Интеллект"),
    WISDOM("Мудрость"),
    CHARISMA("Харизма");

    private final String name;

    CharAttribEnum(String name) {
        this.name = name;
    }

    public static CharAttribEnum getAttribByName(String name) {
        for (CharAttribEnum c : CharAttribEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

}

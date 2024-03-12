package ru.maleth.mythra.enums;

import lombok.Getter;

@Getter
public enum AttribEnum {

    STRENGTH("Сила"),
    DEXTERITY("Ловкость"),
    CONSTITUTION("Телосложение"),
    INTELLIGENCE("Интеллект"),
    WISDOM("Мудрость"),
    CHARISMA("Харизма");

    private final String name;

    AttribEnum(String name) {
        this.name = name;
    }

    public static AttribEnum getAttribByName(String name) {
        for (AttribEnum c : AttribEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

}

package ru.maleth.mythra.service.character.enums;

import lombok.Getter;

@Getter
public enum CharSizeEnum {

    SMALL("Маленький"),
    MEDIUM("Средний"),
    LARGE("Большой"),
    HUGE("Огромный");

    private final String name;

    CharSizeEnum(String name) {
        this.name = name;
    }

    public static CharSizeEnum getSizeByName(String name) {
        for (CharSizeEnum c : CharSizeEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

}

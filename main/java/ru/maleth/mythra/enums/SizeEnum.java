package ru.maleth.mythra.enums;

import lombok.Getter;

@Getter
public enum SizeEnum {

    SMALL("Маленький"),
    MEDIUM("Средний"),
    LARGE("Большой"),
    HUGE("Огромный");

    private final String name;

    SizeEnum(String name) {
        this.name = name;
    }

    public static SizeEnum getSizeByName(String name) {
        for (SizeEnum c : SizeEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

}

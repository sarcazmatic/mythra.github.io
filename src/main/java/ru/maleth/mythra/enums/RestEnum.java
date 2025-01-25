package ru.maleth.mythra.enums;

import lombok.Getter;

@Getter
public enum RestEnum {

    NONE("Не требует отдыха"),
    SHORT("Короткий отдых"),
    LONG("Продолжительный отдых");

    private final String name;

    RestEnum(String name) {
        this.name = name;
    }

    public static RestEnum getRestByName(String name) {
        for (RestEnum c : RestEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

}

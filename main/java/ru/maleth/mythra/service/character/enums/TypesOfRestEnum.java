package ru.maleth.mythra.service.character.enums;

import lombok.Getter;

@Getter
public enum TypesOfRestEnum {

    SHORT("Короткий отдых"),
    LONG("Продолжительный отдых");

    private final String name;

    TypesOfRestEnum(String name) {
        this.name = name;
    }

    public static TypesOfRestEnum getRestByName(String name) {
        for (TypesOfRestEnum c : TypesOfRestEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

}

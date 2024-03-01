package ru.maleth.mythra.service.character.enums;

import lombok.Getter;

@Getter
public enum CharProfEnum {

    ACROBATICS("Акробатика"),
    ATHLETICS("Атлетика"),
    ARCANA("Аркана"),
    DECEPTION("Обман"),
    HISTORY("История"),
    INSIGHT("Проницательность"),
    INTIMIDATION("Запугивание"),
    INVESTIGATION("Расследование"),
    MEDICINE("Медицина"),
    NATURE("Природа"),
    PERCEPTION("Внимательность"),
    PERFORMANCE("Выступление"),
    PERSUASION("Убеждение"),
    RELIGION("Религия"),
    SLEIGHT_OF_HAND("Ловкость рук"),
    STEALTH("Скрытность"),
    SURVIVAL("Выживание"),
    ANIMAL_HANDLING("Обращение с животными");

    private final String name;

    CharProfEnum(String name) {
        this.name = name;
    }

    public static CharProfEnum getProfByName(String name) {
        for (CharProfEnum c : CharProfEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

}

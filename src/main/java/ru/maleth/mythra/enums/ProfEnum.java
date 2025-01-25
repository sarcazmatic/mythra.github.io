package ru.maleth.mythra.enums;

import lombok.Getter;

@Getter
public enum ProfEnum {

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

    ProfEnum(String name) {
        this.name = name;
    }

    public static ProfEnum getProfByName(String name) {
        for (ProfEnum c : ProfEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

}

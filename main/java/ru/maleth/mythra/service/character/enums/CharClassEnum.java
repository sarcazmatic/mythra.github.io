package ru.maleth.mythra.service.character.enums;

import lombok.Getter;

@Getter
public enum CharClassEnum {

    BARD("Бард"),
    BARBARIAN("Варвар"),
    WARRIOR("Воин"),
    WIZARD("Волшебник"),
    DRUID("Друид"),
    ARTIFICER("Изобретатель"),
    WARLOCK("Колдун"),
    BLOOD_HUNTER("Кровавый охотник"),
    MONK("Монах"),
    PALADIN("Паладин"),
    ROGUE("Плут"),
    RANGER("Следопыт"),
    SORCERER("Чародей");

    private final String name;

    CharClassEnum(String name) {
        this.name = name;
    }

    public static CharClassEnum getClassByName(String name) {
        for (CharClassEnum c : CharClassEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

}

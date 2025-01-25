package ru.maleth.mythra.enums;

import lombok.Getter;

@Getter
public enum ClassEnum {

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

    ClassEnum(String name) {
        this.name = name;
    }

    public static ClassEnum getClassByName(String name) {
        for (ClassEnum c : ClassEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

}

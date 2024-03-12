package ru.maleth.mythra.enums;

import lombok.Getter;

@Getter
public enum RaceEnum {

    AARAKOCRA("Ааракокра"),
    PROTECTOR_AASIMAR("Аасимар защитник"),
    SCOURGE_AASIMAR("Аасимар каратель"),
    FALLEN_AASIMAR("Падший аасимар"),
    GITHYANKI("Гитьянки"),
    GITHZERAI("Гитцерай"),
    FOREST_GNOME("Лесной гном"),
    ROCK_GNOME("Скальный гном"),
    GOLIATH("Голиаф"),
    MOUNTAIN_DWARF("Горный дварф"),
    HILL_DWARF("Холмовой дварф"),
    DRAGONBORN("Драконорожденный"),
    HALFORC("Полуорк"),
    STOUT_HALFLING("Коренастый полурослик"),
    LIGHTFOOT_HALFLING("Легконогий полурослик"),
    HALFELF("Полуэльф"),
    TIEFLING("Тифлинг"),
    HUMAN("Человек"),
    WOOD_ELF("Лесной эльф"),
    HIGH_ELF("Высший эльф"),
    DROW("Дроу");

    private final String name;

    RaceEnum(String name) {
        this.name = name;
    }

    public static RaceEnum getRaceByName(String name) {
        for (RaceEnum c : RaceEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

}

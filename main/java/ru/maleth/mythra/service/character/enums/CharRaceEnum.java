package ru.maleth.mythra.service.character.enums;

import lombok.Getter;

@Getter
public enum CharRaceEnum {

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

    CharRaceEnum(String name) {
        this.name = name;
    }

    public static CharRaceEnum getRaceByName(String name) {
        for (CharRaceEnum c : CharRaceEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

}

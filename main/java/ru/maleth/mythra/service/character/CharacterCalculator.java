package ru.maleth.mythra.service.character;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.enums.ClassEnum;
import ru.maleth.mythra.enums.RaceEnum;

@NoArgsConstructor
@Component
public class CharacterCalculator {

    public static int calculateDieHit(ClassEnum charClassEnum) {
        return switch (charClassEnum.getName()) {
            case "Волшебник", "Чардей" -> 6;
            case "Воин", "Паладин", "Следопыт", "Кровавый охотник" -> 10;
            case "Варвар" -> 12;
            default -> 8;
        };
    }

    public static int calculateAttributeModifier(int attributeValue) {
        return (int) Math.floor(((double) attributeValue - 10) / 2);
    }

    public static int getLevel(int experience) {
        if (experience >= 0 && experience < 300) {
            return 1;
        } else if (experience >= 300 && experience < 900) {
            return 2;
        } else if (experience >= 900 && experience < 2700) {
            return 3;
        } else if (experience >= 2700 && experience < 6500) {
            return 4;
        } else if (experience >= 6500 && experience < 14000) {
            return 5;
        } else if (experience >= 14000 && experience < 23000) {
            return 6;
        } else if (experience >= 23000 && experience < 34000) {
            return 7;
        } else if (experience >= 34000 && experience < 48000) {
            return 8;
        } else if (experience >= 48000 && experience < 64000) {
            return 9;
        } else if (experience >= 64000 && experience < 85000) {
            return 10;
        } else if (experience >= 85000 && experience < 100000) {
            return 11;
        } else if (experience >= 100000 && experience < 120000) {
            return 12;
        } else if (experience >= 120000 && experience < 140000) {
            return 13;
        } else if (experience >= 140000 && experience < 165000) {
            return 14;
        } else if (experience >= 165000 && experience < 195000) {
            return 15;
        } else if (experience >= 195000 && experience < 225000) {
            return 16;
        } else if (experience >= 225000 && experience < 265000) {
            return 17;
        } else if (experience >= 265000 && experience < 305000) {
            return 18;
        } else if (experience >= 305000 && experience < 355000) {
            return 19;
        } else if (experience >= 355000) {
            return 20;
        } else {
            return 0;
        }
    }

    public static int getProfBonus(int experience) {
        int level = getLevel(experience);
        if (level >= 1 && level <= 4) {
            return 2;
        } else if (level >= 5 && level <= 8) {
            return 3;
        } else if (level >= 9 && level <= 12) {
            return 4;
        } else if (level >= 13 && level <= 16) {
            return 5;
        } else if (level >= 17 && level <= 20) {
            return 6;
        } else {
            return 0;
        }
    }

    public static int getSpeed(RaceEnum charRaceEnum, ClassEnum charClassEnum) {
        return 1;
    }

    public static int getArmorClass(int attributeValue) {
        return 1;
    }

}

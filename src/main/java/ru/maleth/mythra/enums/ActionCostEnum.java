package ru.maleth.mythra.enums;

import lombok.Getter;

@Getter
public enum ActionCostEnum {

    BLANK("-"),
    BONUS_ACTION("Бонусное действие"),
    ACTION("Действие"),
    FREE_ACTION("Свободное действие"),
    REACTION("Реакция");

    private final String name;

    ActionCostEnum(String name) {
        this.name = name;
    }

    public static ActionCostEnum getCostByName(String name) {
        for (ActionCostEnum c : ActionCostEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

}

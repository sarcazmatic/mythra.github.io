package ru.maleth.mythra.service.levelup;

import ru.maleth.mythra.dto.CharClassToLevelUp;

import java.util.Map;

public interface LevelUpService {


    Map<String, String> formLvlUpPage(String userName, String charName);

    void levelUp(CharClassToLevelUp charClassToLevelUp);

    void multiClass(CharClassToLevelUp charClassToLevelUp);

}

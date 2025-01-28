package ru.maleth.mythra.service.levelup;

import ru.maleth.mythra.dto.CharClassToLevelUpDTO;

import java.util.Map;

public interface LevelUpService {


    Map<String, String> formLvlUpPage(String userName, String charName);

    Map<String, String> formRaiseAttributesPage(String userName, String charName);

    void levelUp(CharClassToLevelUpDTO charClassToLevelUp);

    void multiClass(CharClassToLevelUpDTO charClassToLevelUp);

}

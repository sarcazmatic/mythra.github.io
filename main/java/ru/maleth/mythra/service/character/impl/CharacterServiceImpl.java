package ru.maleth.mythra.service.character.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.CharacterRepo;
import ru.maleth.mythra.service.character.CharacterService;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepo characterRepo;

    @Override
    public Character findByUserNameAndCharName(String userName, String charName) {
        Character character = characterRepo.findByCreator_NameAndCharName(userName, charName).get();
        return character;
    }
}

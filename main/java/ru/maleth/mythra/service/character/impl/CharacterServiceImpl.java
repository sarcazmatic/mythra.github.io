package ru.maleth.mythra.service.character.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.model.CharClass;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.model.Proficiency;
import ru.maleth.mythra.model.User;
import ru.maleth.mythra.repo.CharacterRepo;
import ru.maleth.mythra.repo.ClassesRepo;
import ru.maleth.mythra.repo.ProficiencyRepo;
import ru.maleth.mythra.repo.UserRepo;
import ru.maleth.mythra.service.character.CharacterCalculator;
import ru.maleth.mythra.service.character.CharacterService;
import ru.maleth.mythra.service.character.enums.CharClassEnum;
import ru.maleth.mythra.service.character.enums.CharProfEnum;
import ru.maleth.mythra.service.character.enums.CharRaceEnum;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepo characterRepo;
    private final ClassesRepo classesRepo;
    private final ProficiencyRepo proficiencyRepo;
    private final UserRepo userRepo;

    @Override
    public Character createNewCharacter(String charName,
                                        String charClass,
                                        String charRace,
                                        int strength,
                                        int dexterity,
                                        int constitution,
                                        int intelligence,
                                        int wisdom,
                                        int charisma,
                                        List<String> profs,
                                        int hitPoints) {

        Set<Proficiency> proficiencies = new HashSet<>();

        for (String s : profs) {
            try {
                proficiencies.add(proficiencyRepo.findByName(CharProfEnum.valueOf(s.toUpperCase().replace('-','_')).getName()));
            } catch (RuntimeException e) {
                throw new RuntimeException("Нет такого владения");
            }
        }

        Set<CharClass> charClasses = new HashSet<>();
        charClasses.add(classesRepo.findByName(CharClassEnum.getClassByName(charClass).getName()));

        User user = userRepo.findById(1L).get();

        Character character = Character.builder()
                .charName(charName)
                .charRaceEnum(CharRaceEnum.getRaceByName(charRace))
                .strength(strength)
                .dexterity(dexterity)
                .constitution(constitution)
                .intelligence(intelligence)
                .wisdom(wisdom)
                .charisma(charisma)
                .maxHP(hitPoints)
                .currentHP(hitPoints)
                .experience(0)
                .armorClass(10 + CharacterCalculator.calculateAttributeModifier(dexterity))
                .speed(30) //тут нужно будет через калькулятор делать
                .initiative(CharacterCalculator.calculateAttributeModifier(dexterity))
                .proficiencies(proficiencies)
                .characterClasses(charClasses)
                .creator(user)
                .build();

        System.out.println(character.getProficiencies().stream().toList());

        return characterRepo.save(character);
    }
}

package ru.maleth.mythra.service.character.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.model.*;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.*;
import ru.maleth.mythra.service.character.CharacterCalculator;
import ru.maleth.mythra.service.character.CharacterService;
import ru.maleth.mythra.service.character.enums.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepo characterRepo;
    private final RaceRepo raceRepo;
    private final ClassesRepo classesRepo;
    private final ProficiencyRepo proficiencyRepo;
    private final UserRepo userRepo;
    private final AbilityRepo abilityRepo;

    @Override
    public Map<String, String> goToAttributes(String charName, String charClass, String charRace, String charSubrace) {
        Map<String, String> attributes = new HashMap<>();

        if (charClass.contains("-")) {
            String[] charClassArray = charClass.split("-");
            String charClassOne = charClassArray[0];
            attributes.put("charClassOne", charClassOne);
            String charClassTwo = charClassArray[1];
            attributes.put("charClassTwo", " " + charClassTwo);
        } else {
            attributes.put("charClassOne", charClass);
        }

        if (charRace.equals("Аасимар")
                || charRace.equals("Гит")
                || charRace.equals("Гном")
                || charRace.equals("Дварф")
                || charRace.equals("Полурослик")
                || charRace.equals("Эльф")) {
            if (charSubrace.contains("-")) {
                String[] charRaceArray = charSubrace.split("-");
                String charRaceOne = charRaceArray[0];
                attributes.put("charRaceOne", charRaceOne);
                if (charRaceArray.length > 1) {
                    String charRaceTwo = charRaceArray[1];
                    attributes.put("charRaceTwo", " " + charRaceTwo);
                }
            } else {
                attributes.put("charRaceOne", charSubrace);
            }
        } else {
            attributes.put("charRaceOne", charRace);
        }

        attributes.put("charName", charName);
        attributes.put("directToPage", "attributes");

        return attributes;
    }

    @Override
    public Map<String, String> goToSkills(String charName,
                                          String charClass,
                                          String charRace,
                                          int strength,
                                          int dexterity,
                                          int constitution,
                                          int intelligence,
                                          int wisdom,
                                          int charisma) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("charName", charName);
        attributes.put("charRace", charRace);
        attributes.put("charClass", charClass);
        attributes.put("hitPoints",
                String.valueOf(CharacterCalculator.calculateDieHit(CharClassEnum.getClassByName(charClass)) +
                        CharacterCalculator.calculateAttributeModifier(constitution)));
        Race race = raceRepo.findByName(CharRaceEnum.getRaceByName(charRace).toString());
        int strengthAtr = strength + race.getStrengthBonus();
        int dexterityAtr = dexterity + race.getDexterityBonus();
        int constitutionAtr = constitution + race.getConstitutionBonus();
        int intelligenceAtr = intelligence + race.getIntelligenceBonus();
        int wisdomAtr = wisdom + race.getWisdomBonus();
        int charismaAtr = charisma + race.getCharismaBonus();
        attributes.put("strength", String.valueOf(strengthAtr));
        attributes.put("dexterity", String.valueOf(dexterityAtr));
        attributes.put("constitution", String.valueOf(constitutionAtr));
        attributes.put("intelligence", String.valueOf(intelligenceAtr));
        attributes.put("wisdom", String.valueOf(wisdomAtr));
        attributes.put("charisma", String.valueOf(charismaAtr));
        attributes.put("strengthMod", String.valueOf(CharacterCalculator.calculateAttributeModifier(strengthAtr)));
        attributes.put("dexterityMod", String.valueOf(CharacterCalculator.calculateAttributeModifier(dexterityAtr)));
        attributes.put("intelligenceMod", String.valueOf(CharacterCalculator.calculateAttributeModifier(intelligenceAtr)));
        attributes.put("wisdomMod", String.valueOf(CharacterCalculator.calculateAttributeModifier(wisdomAtr)));
        attributes.put("charismaMod", String.valueOf(CharacterCalculator.calculateAttributeModifier(charismaAtr)));

        attributes.put("directToPage", "skills");

        return attributes;
    }

    @Override
    public Map<String, String> goToSheet(String charName,
                                         String charClass,
                                         String charRace,
                                         int strength,
                                         int dexterity,
                                         int constitution,
                                         int intelligence,
                                         int wisdom,
                                         int charisma,
                                         int hitPoints,
                                         List<String> profs) {
        Map<String, String> attributes = new HashMap<>();

        Character character = createNewCharacter(charName, charClass, charRace, strength, dexterity, constitution,
                intelligence, wisdom, charisma, profs, hitPoints);
        attributes.put("charName", character.getCharName());
        attributes.put("charRace", character.getCharRace().getRaceName());
        attributes.put("charClass", character.getClassName());
        attributes.put("ac", String.valueOf(character.getArmorClass()));
        attributes.put("speed", String.valueOf(character.getCharRace().getSpeed()));
        attributes.put("initiative", formatMods(character.getInitiative()));
        int experience = character.getExperience();
        attributes.put("experience", String.valueOf(experience));
        attributes.put("level", String.valueOf(CharacterCalculator.getLevel(experience)));
        attributes.put("proficiency", formatMods(CharacterCalculator.getProfBonus(experience)));

        attributes.put("strength", String.valueOf(strength));
        attributes.put("strengthmod", formatMods(CharacterCalculator.calculateAttributeModifier(strength)));
        attributes.put("dexterity", String.valueOf(dexterity));
        attributes.put("dexteritymod", formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)));
        attributes.put("constitution", String.valueOf(constitution));
        attributes.put("constitutionmod", formatMods(CharacterCalculator.calculateAttributeModifier(constitution)));
        attributes.put("intelligence", String.valueOf(intelligence));
        attributes.put("intelligencemod", formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)));
        attributes.put("wisdom", String.valueOf(wisdom));
        attributes.put("wisdommod", formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)));
        attributes.put("charisma", String.valueOf(charisma));
        attributes.put("charismamod", formatMods(CharacterCalculator.calculateAttributeModifier(charisma)));

        List<String> proficienciesFromEnum = Arrays.stream(CharProfEnum.values())
                .map(charProfEnum -> charProfEnum.toString().toLowerCase().replace('_', '-'))
                .toList(); //тут мы собрали список скиллов (NN-NN вместо NN_NN)
        for (String s : proficienciesFromEnum) {
            if (profs.contains(s)) {
                Proficiency proficiency = character.getProficiencies().stream().filter(p
                        -> p.getName().equals(CharProfEnum.valueOf(s.toUpperCase().replace("-", "_")).toString())).findFirst().get();
                CharAttribEnum baseAttrib = CharAttribEnum.valueOf(proficiency.getBaseAttribute().toString());
                switch (baseAttrib) {
                    case STRENGTH ->
                            attributes.put(CharProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                                    formatMods(CharacterCalculator.calculateAttributeModifier(strength)
                                            + CharacterCalculator.getProfBonus(character.getExperience())));
                    case DEXTERITY -> {
                        attributes.put(CharProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                                formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)
                                        + CharacterCalculator.getProfBonus(character.getExperience())));
                    }
                    case INTELLIGENCE ->
                            attributes.put(CharProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                                    formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)
                                            + CharacterCalculator.getProfBonus(character.getExperience())));
                    case WISDOM -> attributes.put(CharProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)
                                    + CharacterCalculator.getProfBonus(character.getExperience())));
                    case CHARISMA ->
                            attributes.put(CharProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                                    formatMods(CharacterCalculator.calculateAttributeModifier(charisma)
                                            + CharacterCalculator.getProfBonus(character.getExperience())));
                    default -> throw new RuntimeException("Тут такое вообще произошло");
                }
            } else {
                switch (s) {
                    case "athletics" ->
                            attributes.put(s.replace("-", "_"), formatMods(CharacterCalculator.calculateAttributeModifier(strength)));
                    case "acrobatics", "stealth", "sleight-of-hand" ->
                            attributes.put(s.replace("-", "_"), formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)));
                    case "arcana", "history", "investigation", "nature", "religion" ->
                            attributes.put(s.replace("-", "_"), formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)));
                    case "insight", "medicine", "perception", "survival", "animal-handling" ->
                            attributes.put(s.replace("-", "_"), formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)));
                    case "deception", "intimidation", "performance", "persuasion" ->
                            attributes.put(s.replace("-", "_"), formatMods(CharacterCalculator.calculateAttributeModifier(charisma)));
                    default -> throw new RuntimeException("Тут такое вообще произошло");
                }
            }
        }

        List<CharAttribEnum> savingThrows = new ArrayList<>();
        savingThrows.add(character.getCharacterClasses().stream().findFirst().get().getSavingThrowOne());
        savingThrows.add(character.getCharacterClasses().stream().findFirst().get().getSavingThrowTwo());

        attributes.put("strengthsave", formatMods(CharacterCalculator.calculateAttributeModifier(strength)));
        attributes.put("dexteritysave", formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)));
        attributes.put("constitutionsave", formatMods(CharacterCalculator.calculateAttributeModifier(constitution)));
        attributes.put("intelligencesave", formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)));
        attributes.put("wisdomsave", formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)));
        attributes.put("charismasave", formatMods(CharacterCalculator.calculateAttributeModifier(charisma)));

        for (int k = 0; k < savingThrows.size(); k++) {
            switch (savingThrows.get(k).toString()) {
                case "STRENGTH" -> attributes.put(savingThrows.get(k).toString().toLowerCase() + "save",
                        formatMods(CharacterCalculator.calculateAttributeModifier(strength) + CharacterCalculator.getProfBonus(character.getExperience())));
                case "DEXTERITY" -> attributes.put(savingThrows.get(k).toString().toLowerCase() + "save",
                        formatMods(CharacterCalculator.calculateAttributeModifier(dexterity) + CharacterCalculator.getProfBonus(character.getExperience())));
                case "CONSTITUTION" -> attributes.put(savingThrows.get(k).toString().toLowerCase() + "save",
                        formatMods(CharacterCalculator.calculateAttributeModifier(constitution) + CharacterCalculator.getProfBonus(character.getExperience())));
                case "INTELLIGENCE" -> attributes.put(savingThrows.get(k).toString().toLowerCase() + "save",
                        formatMods(CharacterCalculator.calculateAttributeModifier(intelligence) + CharacterCalculator.getProfBonus(character.getExperience())));
                case "WISDOM" -> attributes.put(savingThrows.get(k).toString().toLowerCase() + "save",
                        formatMods(CharacterCalculator.calculateAttributeModifier(wisdom) + CharacterCalculator.getProfBonus(character.getExperience())));
                case "CHARISMA" -> attributes.put(savingThrows.get(k).toString().toLowerCase() + "save",
                        formatMods(CharacterCalculator.calculateAttributeModifier(charisma) + CharacterCalculator.getProfBonus(character.getExperience())));
                default -> throw new RuntimeException("Не получилось проставить спасброски");
            }
        }

        attributes.put("hitPoints", String.valueOf(hitPoints));

        if (character.getCharRace().isHasDarkvision()) {
            attributes.put("darkvision", "Да");
        } else {
            attributes.put("darkvision", "Нет");
        }

        List<Ability> abilitiesAtLevelOne = abilityRepo.findAllByCharClassAndClassLevel(CharClassEnum.getClassByName(character.getClassName()).toString(), 1); //тут не получится, т.е. он находит по классу и уровню, а кол-во использований не учитывает

        for (int k = 0; k < abilitiesAtLevelOne.size(); k++) {
            attributes.put("abilUseButton"+(k+1), "abilUseButton"+(k+1));
            attributes.put("abilName"+(k+1), abilitiesAtLevelOne.get(k).getName());
            attributes.put("abilDesc"+(k+1), abilitiesAtLevelOne.get(k).getDescription());
            attributes.put("abilCost"+(k+1), abilitiesAtLevelOne.get(k).getCost().getName());
            //attributes.put("abilCharges"+(k+1), String.valueOf(abilitiesAtLevelOne.get(k).getNumberOfUses())); нужно увести из ability и вычислять иначе
            attributes.put("abilRest"+(k+1), abilitiesAtLevelOne.get(k).getTypeOfRest().getName());
        }

        attributes.put("directToPage", "charsheet");

        return attributes;
    }

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
                proficiencies.add(proficiencyRepo.findByName(s.toUpperCase().replace('-', '_')));
            } catch (RuntimeException e) {
                throw new RuntimeException("Нет такого владения");
            }
        }

        Set<CharClass> charClasses = new HashSet<>();
        CharClass charClassPrep = classFormer(classesRepo.findByName(CharClassEnum.getClassByName(charClass).toString()), charisma);
        charClasses.add(charClassPrep);

        Race race = raceRepo.findByName(CharRaceEnum.getRaceByName(charRace).toString());

        User user = userRepo.findById(1L).get(); //тут дописать

        Character character = Character.builder()
                .charName(charName)
                .charRace(race)
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
                .initiative(CharacterCalculator.calculateAttributeModifier(dexterity))
                .proficiencies(proficiencies)
                .characterClasses(charClasses)
                .creator(user)
                .build();

        return characterRepo.save(character);
    }

    private String formatMods(int mod) {
        if (mod > 0) {
            return ("+" + mod);
        }
        return String.valueOf(mod);
    }

    private CharClass classFormer(CharClass charClass, int charisma) {
        if (charClass.getName().equals("BARD")) {
            int numberOfUses = Math.max(CharacterCalculator.calculateAttributeModifier(charisma), 1);
            Ability ability = Ability.builder()
                    .name("ВДОХНОВЕНИЕ БАРДА (к6)")
                    .classLevel(1)
                    .description("""
                            Своими словами или музыкой вы можете вдохновлять других. Для этого вы должны бонусным \
                            действием выбрать одно существо, отличное от вас, в пределах 60 футов, которое может вас \
                            слышать. Это существо получает кость бардовского вдохновения — к6.<br>
                            
                            В течение следующих 10 минут это существо может один раз бросить эту кость и добавить \
                            результат к проверке характеристики, броску атаки или спасброску, который оно совершает. \
                            Существо может принять решение о броске кости вдохновения уже после броска к20, но должно \
                            сделать это прежде, чем Мастер объявит результат броска. Как только кость бардовского \
                            вдохновения брошена, она исчезает. Существо может иметь только одну такую кость одновременно.<br>
                            
                            Вы можете использовать это умение количество раз, равное модификатору вашей Харизмы, но \
                            как минимум один раз. Потраченные использования этого умения восстанавливаются после \
                            продолжительного отдыха.<br>
                            
                            Ваша кость бардовского вдохновения изменяется с ростом вашего уровня в этом классе. Она \
                            становится к8 на 5-м уровне, к10 на 10-м уровне и к12 на 15-м уровне.""")
                    .isActive(true)
                    .requiresRest(true)
                    .typeOfRest(TypesOfRestEnum.LONG)
                    .charClass(charClass)
                    .cost(ActionCostEnum.BONUS_ACTION)
                    .build();
            System.out.println(ability);
            abilityRepo.save(ability);
        } else if (charClass.getName().equals("BARBARIAN")) {
            Ability ability = Ability.builder()
                    .name("ЯРОСТЬ")
                    .classLevel(1)
                    .description("""
                            В бою вы сражаетесь с первобытной свирепостью. В свой ход вы можете бонусным действием \
                            войти в состояние ярости.<br>
                            В состоянии ярости вы получаете следующие преимущества, если не носите тяжёлую броню:<br>
                            – Вы совершаете с преимуществом проверки и спасброски Силы.<br>
                            – Если вы совершаете рукопашную атаку оружием, используя Силу, вы получаете бонус к броску \
                            урона, соответствующий вашему уровню варвара, как показано в колонке «урон ярости» таблицы «Варвар».<br>
                            – Вы получаете сопротивление дробящему, колющему и рубящему урону.<br>
                            Если вы способны накладывать заклинания, то вы не можете накладывать или концентрироваться \
                            на заклинаниях, пока находитесь в состоянии ярости.<br>
                            Ваша ярость длится 1 минуту. Она прекращается раньше, если вы потеряли сознание или если вы \
                            закончили свой ход, не получив урон или не атаковав враждебное по отношению к вам существо \
                            с момента окончания вашего прошлого хода. Также вы можете прекратить свою ярость бонусным действием.<br>
                            Если вы впадали в состояние ярости максимальное для вашего уровня количество раз (смотрите колонку «ярость»), то вы должны совершить продолжительный отдых, прежде чем сможете использовать ярость ещё раз.""")
                    .isActive(true)
                    .requiresRest(true)
                    .typeOfRest(TypesOfRestEnum.LONG)
                    .charClass(charClass)
                    .cost(ActionCostEnum.BONUS_ACTION)
                    .build();
            abilityRepo.save(ability);
        }
        return charClass;
    }

}

package ru.maleth.mythra.service.character.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.enums.*;
import ru.maleth.mythra.model.*;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.*;
import ru.maleth.mythra.service.character.CharacterCalculator;
import ru.maleth.mythra.service.character.CharacterService;

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
    private final CharClassAbilityRepo charClassAbilityRepo;

    @Override
    public Map<String, String> goToAttributes(String charName, String charClass, String charRace, String charSubrace) {
        /*
        Создаем мапу, чтобы собирать в нее атрибуты.
         */
        Map<String, String> attributes = new HashMap<>();

        /*
        Суем в мапу имя.
         */
        attributes.put("charName", charName);

        /*
        Здесь мы заменяем переданные сюда названия классов:
        Из "Кровавый-охотник" делаем "Кровавый охотник", разделяем на два на дефисе ('-') и сохраняем в двух переменных,
        чтобы передать на страницу. Если дефиса нет, вторая переменная остается пустой.
        */
        if (charClass.contains("-")) {
            String[] charClassArray = charClass.split("-");
            String charClassOne = charClassArray[0];
            attributes.put("charClassOne", charClassOne);
            String charClassTwo = charClassArray[1];
            attributes.put("charClassTwo", " " + charClassTwo);
        } else {
            attributes.put("charClassOne", charClass);
        }

        /*
        Здесь мы заменяем сабрасы на расы, также заботясь о названиях.
        Например "Аасимар-каратель" првращается в "Аасимар каратель".
        И заодно собираем все в мапу. Это неидеально, лучше бы переменные завести.
        */
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
                String charRaceTwo = charRaceArray[1];
                attributes.put("charRaceTwo", " " + charRaceTwo);
            } else {
                attributes.put("charRaceOne", charSubrace);
            }
        } else {
            attributes.put("charRaceOne", charRace);
        }

        /*
        Здесь передаем мапу с атрибутами в контролер и указываем, на какую страницу будет осуществляться переход.
        */
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
        /*
        Создаем мапу, чтобы собирать в нее атрибуты.
         */
        Map<String, String> attributes = new HashMap<>();

        /*
        Собираем переменные, которые будем передавать в мапу: hitPoints и race, которая нам нужна, чтобы
        верно высчитать модификаторы атрибутов.
         */
        int hitPoints = CharacterCalculator.calculateDieHit(ClassEnum.getClassByName(charClass)) +
                CharacterCalculator.calculateAttributeModifier(constitution);
        Race race = raceRepo.findByName(RaceEnum.getRaceByName(charRace).toString());
        int strengthAtr = strength + race.getStrengthBonus();
        int dexterityAtr = dexterity + race.getDexterityBonus();
        int constitutionAtr = constitution + race.getConstitutionBonus();
        int intelligenceAtr = intelligence + race.getIntelligenceBonus();
        int wisdomAtr = wisdom + race.getWisdomBonus();
        int charismaAtr = charisma + race.getCharismaBonus();

        /*
        Передаем в мапу переменные и стринг со страницей, на которую переводим. Отдаем в контролер.
         */
        attributes.put("charName", charName);
        attributes.put("charRace", charRace);
        attributes.put("charClass", charClass);
        attributes.put("hitPoints", String.valueOf(hitPoints));
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
    public Map<String, String> goToSheet(String userName,
                                         String charName,
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
        /*
        Создаем мапу, чтобы собирать в нее атрибуты.
         */
        Map<String, String> attributes = new HashMap<>();

        /*
        Собираем переменные, которые будем передавать в мапу: создаем через метод ПЕРСОНАЖА из имеющихся параметров и
        сохраняем опыт в переменной для рассчетов дальше.
         */
        List<String> allProficienciesList = Arrays.stream(ProfEnum.values()).map(Enum::toString).toList();
        profs = profs.stream().map(p -> p.toUpperCase().replace('-', '_')).toList();

        Character character = createNewCharacter(userName, charName, charClass, charRace, strength, dexterity, constitution,
                intelligence, wisdom, charisma, profs, hitPoints);
        int experience = character.getExperience();

        /*
        Продолжаем собирать переменные.
        allProficienciesList – список всех имеющихся специализаций в навыках в формате SLEIGHT_OF_HAND.
        Чтобы сравнить со специализациями в списке profs, содержимое profs нужно перевести в формат NN_NN.
        Когда мы сравниваем, смотрим, присутствует ли специализация в списке специализаций персонажа – т.е.
        находим, в каких специализациях из всех профишиент наш персонаж. Если совпал, выцепляем эту специализацию
        из персонажа, чтобы увеличить показатель.
         */
        for (String s : allProficienciesList) {
            if (profs.contains(s)) {
                /*
                Выцепив одну (например SLEIGHT_OF_HAND), берем ее атрибут (в нашем случае Ловкость).
                И засовываем в мапу. Имя атрибута – имя профы в нижнем регистре (т.е. sleight_of_hand), значение –
                модификатор Ловкости + бонус мастерства.
                 */
                Proficiency proficiency = character.getProficiencies().stream().filter(p
                        -> p.getName().equals(s)).findFirst().get();
                AttribEnum baseAttrib = AttribEnum.valueOf(proficiency.getBaseAttribute().toString());
                switch (baseAttrib) {
                    case STRENGTH ->
                            attributes.put(ProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                                    formatMods(CharacterCalculator.calculateAttributeModifier(strength)
                                            + CharacterCalculator.getProfBonus(character.getExperience())));
                    case DEXTERITY ->
                        attributes.put(ProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                                formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)
                                        + CharacterCalculator.getProfBonus(character.getExperience())));
                    case INTELLIGENCE ->
                            attributes.put(ProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                                    formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)
                                            + CharacterCalculator.getProfBonus(character.getExperience())));
                    case WISDOM ->
                            attributes.put(ProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)
                                    + CharacterCalculator.getProfBonus(character.getExperience())));
                    case CHARISMA ->
                            attributes.put(ProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                                    formatMods(CharacterCalculator.calculateAttributeModifier(charisma)
                                            + CharacterCalculator.getProfBonus(character.getExperience())));
                    default -> throw new RuntimeException("Тут такое вообще произошло");
                }
            } else {
                /*
                Если навык из общего списка отсутствует в специализациях персонажа, ему присваивается базовое значение.
                И засовывается в мапу. Формат навыка, который отправляется в мапу в итоге получается sleight_of_hand.
                 */
                switch (s) {
                    case "ATHLETICS" ->
                            attributes.put(s.toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(strength)));
                    case "ACROBATICS", "STEALTH", "SLEIGHT_OF_HAND" ->
                            attributes.put(s.toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)));
                    case "ARCANA", "HISTORY", "INVESTIGATION", "NATURE", "RELIGION" ->
                            attributes.put(s.toLowerCase(),
                                    formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)));
                    case "INSIGHT", "MEDICINE", "PERCEPTION", "SURVIVAL", "ANIMAL_HANDLING" ->
                            attributes.put(s.toLowerCase(),
                                    formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)));
                    case "DECEPTION", "INTIMIDATION", "PERFORMANCE", "PERSUASION" ->
                            attributes.put(s.toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(charisma)));
                    default -> throw new RuntimeException("Тут такое вообще произошло");
                }
            }
        }

        /*
        На странице нужно вывести еще и спасброски. В двух из них персонаж специализируется.
        Специализацию дает класс – находим класс персонажа и выцепляем из него две переменные спасбросков, а затем
        добавляем в список спасбросков.
        */
        List<AttribEnum> savingThrows = new ArrayList<>();
        savingThrows.add(character.getCharacterClasses().stream().findFirst().get().getSavingThrowOne());
        savingThrows.add(character.getCharacterClasses().stream().findFirst().get().getSavingThrowTwo());

        /*
        Проходимся по списку спасбросков, в которых есть специализация и выставляем повышенное значение.
        Проставили – и засовываем в мапу в формате(strengthsave, 10).

        ТУТ МОЖНО ПОСТУПИТЬ, КАК С НАВЫКАМИ – собрать список атрибутов и смотреть, в каких специализируются
        персонаж, но я сделал иначе – заполнил сперва не модифицированно, а затем переписал.
        */
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

        /*
        В зависимости от класса и уровня этого класса, у персонажа есть умения.
        Зачастую кол-во раз использования умений зависит от персонажа.
        Тут мы формируем список умений (по сути одно умение на класс) и сохраняем его в двух репозиториях.
        В AbilityRepo и в CharClassAbilityRepo (cca).
        Второй репо нужен, чтобы у нас не было миллиона одинаковых Ability, разница в которых исключительно в кол-ве
        применений. Плюс, полагаю, в дальнейшем именно этим репо буду пользоваться, чтобы обновлять кол-во использований.
        */
        List<CharClassAbility> abilitiesAtLevelOne = charClassAbilityFormer(character,
                character.getCharacterClasses().stream().findFirst().get());
        /*
        Тут переносим умения в мапу через цикл. А что, а вдруг больше одного.
        */
        for (int k = 0; k < abilitiesAtLevelOne.size(); k++) {
            attributes.put("abilUseButton" + (k + 1), "abilUseButton" + (k + 1));
            attributes.put("abilName" + (k + 1), abilitiesAtLevelOne.get(k).getAbility().getName());
            attributes.put("abilDesc" + (k + 1), abilitiesAtLevelOne.get(k).getAbility().getDescription());
            attributes.put("abilCost" + (k + 1), abilitiesAtLevelOne.get(k).getAbility().getCost().getName());
            attributes.put("abilCharges" + (k + 1), String.valueOf(abilitiesAtLevelOne.get(k).getNumberOfUses())); //нужно увести из ability и вычислять иначе
            attributes.put("abilRest" + (k + 1), abilitiesAtLevelOne.get(k).getAbility().getTypeOfRest().getName());
        }

        /*
        Тут переносим атрибуты в мапу. И указываем адрес страницы.
        */
        attributes.put("charName", character.getCharName());
        attributes.put("charRace", character.getCharRace().getRaceName());
        attributes.put("charClass", character.getClassName());
        attributes.put("ac", String.valueOf(character.getArmorClass()));
        attributes.put("speed", String.valueOf(character.getCharRace().getSpeed()));
        attributes.put("initiative", formatMods(character.getInitiative()));
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
        attributes.put("hitPoints", String.valueOf(hitPoints));

        if (character.getCharRace().isHasDarkvision()) {
            attributes.put("darkvision", "Да");
        } else {
            attributes.put("darkvision", "Нет");
        }

        attributes.put("directToPage", "charsheet");

        return attributes;
    }

    public Character createNewCharacter(String userName,
                                        String charName,
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
        /*
        Тут создаем нового персонажа. Для начала создаем SET (в котором не повторяются данные).
        В него отправляем все специализации персонажа. Чтобы все было чин по чину, мы находим специализации
        в репозитории по имени, которое берем в списе profs
        */
        Set<Proficiency> proficiencies = new HashSet<>();
        for (String s : profs) {
            try {
                proficiencies.add(proficiencyRepo.findByName(s));
            } catch (RuntimeException e) {
                throw new RuntimeException("Нет такого владения");
            }
        }

        /*
        Теперь создаем SET с классами (на этом этапе класс у персонажа может быть только один, но далее возможно
        мультиклассирование, и нужно будет куда-то засовывать доп классы.
        В сет засовываем класс из репозитория; в репозитории класс находим по названию.
        */
        Set<CharClass> charClasses = new HashSet<>();
        charClasses.add(classesRepo.findByName(ClassEnum.getClassByName(charClass).toString()));

        /*
        Тут просто - находим в репозитории расу и присваиваем персонажу.
        */
        Race race = raceRepo.findByName(RaceEnum.getRaceByName(charRace).toString());

        /*
        Тут находим юзера по имени, переданному из контроера
        */
        User user = userRepo.findByName(userName).get();

        /*
        Ну и создаем персонажа через @Builder
        */
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

    private List<CharClassAbility> charClassAbilityFormer(Character character, CharClass charClass) {
        List<CharClassAbility> charClassAbilitiesList = new ArrayList<>();
        Ability ability;
        if (charClass.getName().equals("BARD")) {
            int numberOfUses = Math.max(CharacterCalculator.calculateAttributeModifier(character.getCharisma()), 1);
            Optional<Ability> abilityPresent = Optional.ofNullable(abilityRepo.findByName("ВДОХНОВЕНИЕ БАРДА (к6)"));
            if (abilityPresent.isEmpty()) {
                ability = Ability.builder()
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
                        .typeOfRest(RestEnum.LONG)
                        .charClass(charClass)
                        .cost(ActionCostEnum.BONUS_ACTION)
                        .build();
                abilityRepo.save(ability);
            } else {
                ability = abilityPresent.get();
            }
            CharClassAbility charClassAbility = CharClassAbility.builder()
                    .character(character)
                    .ability(ability)
                    .charClass(charClass)
                    .numberOfUses(numberOfUses)
                    .build();
            charClassAbilitiesList.add(charClassAbilityRepo.save(charClassAbility));
        } else if (charClass.getName().equals("BARBARIAN")) {
            int numberOfUses = 2;
            Optional<Ability> abilityPresent = Optional.ofNullable(abilityRepo.findByName("ЯРОСТЬ"));
            if (abilityPresent.isEmpty()) {
                ability = Ability.builder()
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
                        .typeOfRest(RestEnum.LONG)
                        .charClass(charClass)
                        .cost(ActionCostEnum.BONUS_ACTION)
                        .build();
                abilityRepo.save(ability);
            } else {
                ability = abilityPresent.get();
            }
            CharClassAbility charClassAbility = CharClassAbility.builder()
                    .character(character)
                    .ability(ability)
                    .charClass(charClass)
                    .numberOfUses(numberOfUses)
                    .build();
            charClassAbilitiesList.add(charClassAbilityRepo.save(charClassAbility));
        }
        return charClassAbilitiesList;
    }

}

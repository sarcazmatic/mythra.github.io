package ru.maleth.mythra.utility.classes.artificer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.enums.AbilityEnum;
import ru.maleth.mythra.enums.ActionCostEnum;
import ru.maleth.mythra.enums.RestEnum;
import ru.maleth.mythra.model.Ability;
import ru.maleth.mythra.model.CharClass;
import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.service.character.CharacterCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ArtificerUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;

    public List<CharClassAbility> formAbilities(Character character, CharClass charClass) {
        List<CharClassAbility> charClassAbilitiesList = new ArrayList<>();
        Ability ability;
        int characterLevel = CharacterCalculator.getLevel(character.getExperience());
        int numberOfUses = Math.max(CharacterCalculator.calculateAttributeModifier(character.getIntelligence()), 1);
        Optional<Ability> abilityPresent = Optional.ofNullable(abilityRepo.findByName("МАГИЧЕСКИЙ МАСТЕРОВОЙ"));
        if (abilityPresent.isEmpty()) {
            ability = Ability.builder()
                    .name("МАГИЧЕСКИЙ МАСТЕРОВОЙ")
                    .description("""
                            Вы научились вкладывать искру магии в обычные предметы. Чтобы использовать это умение, вы \
                            должны держать в руках воровские инструменты или инструменты ремесленника. Затем действием \
                            вы касаетесь Крошечного немагического объекта и наделяете его одним из следующих магических \
                            свойств на ваш выбор:
                            • Зачарованный объект излучает яркий свет в радиусе 5 футов и тусклый свет в радиусе еще 5 футов.
                            • Объект проигрывает записанное сообщение, которое можно услышать в пределах 10 футов каждый \
                            раз, когда до него дотрагивается существо. Вы произносите это сообщение, когда наделяете \
                            объект данным свойством, а сама запись не может быть длиннее 6 секунд.
                            • Объект непрерывно испускает запах или издаёт звук на ваш выбор (ветер, волны, стрекотание и \
                            прочее). Выбранное явление можно ощутить на расстоянии 10 футов.
                            • Статичный визуальный эффект появляется на одной из поверхностей объекта. Этот эффект может \
                            быть изображением, текстом до 25 слов, линиями и формами или совмещением этих элементов по вашему выбору.
                            Выбранное свойство навсегда остается присущим объекту. Действием вы можете коснуться объекта \
                            и лишить его этого свойства.
                            Таким образом можно наделить магическими свойствами несколько предметов, касаясь одного \
                            предмета каждый раз, когда вы используете это умение, но не больше, чем одно свойство на один \
                            предмет. Максимальное количество объектов, которые вы можете наделить магическими свойствами \
                            этим умением одновременно, равно вашему модификатору Интеллекта (минимум один объект). Если \
                            вы пытаетесь превысить свой максимум, самое старое свойство немедленно заканчивается, а затем \
                            начинает действовать новое свойство.""")
                    .isActive(true)
                    .requiresRest(false)
                    .typeOfRest(RestEnum.NONE)
                    .abilitySource(AbilityEnum.CLASS)
                    .cost(ActionCostEnum.ACTION)
                    .build();
            abilityRepo.save(ability);
        } else {
            ability = abilityPresent.get();
        }
        Optional<CharClassAbility> ccaOptional = Optional.ofNullable(charClassAbilityRepo.findByCharacter_IdAndAbility_Name(character.getId(), ability.getName()));
        CharClassAbility charClassAbility;
        if (ccaOptional.isEmpty()) {
            charClassAbility = CharClassAbility.builder()
                    .character(character)
                    .ability(ability)
                    .charClass(charClass)
                    .numberOfUses(numberOfUses)
                    .build();
            charClassAbilityRepo.save(charClassAbility);
        } else {
            charClassAbility = ccaOptional.get();
        }
        charClassAbilitiesList.add(charClassAbility);
        return charClassAbilitiesList;
    }
}
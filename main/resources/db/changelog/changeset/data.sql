--liquibase formatted sql
--changeset proficiencies:1
INSERT INTO proficiencies (name, base_attribute)
VALUES ('ACROBATICS', 'DEXTERITY');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('ATHLETICS', 'STRENGTH');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('ARCANA', 'INTELLIGENCE');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('DECEPTION', 'CHARISMA');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('HISTORY', 'INTELLIGENCE');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('INSIGHT', 'WISDOM');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('INTIMIDATION', 'CHARISMA');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('INVESTIGATION', 'INTELLIGENCE');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('MEDICINE', 'WISDOM');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('NATURE', 'INTELLIGENCE');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('PERCEPTION', 'WISDOM');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('PERFORMANCE', 'CHARISMA');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('PERSUASION', 'CHARISMA');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('RELIGION', 'INTELLIGENCE');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('SLEIGHT_OF_HAND', 'DEXTERITY');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('STEALTH', 'DEXTERITY');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('SURVIVAL', 'WISDOM');
INSERT INTO proficiencies (name, base_attribute)
VALUES ('ANIMAL_HANDLING', 'WISDOM');

--changeset classes:1
INSERT INTO classes (id, name, saving_throw_one, saving_throw_two)
VALUES (10, 'BARD', 'DEXTERITY', 'CHARISMA');
INSERT INTO classes (id, name, saving_throw_one, saving_throw_two)
VALUES (20, 'BARBARIAN', 'STRENGTH', 'CONSTITUTION');
INSERT INTO classes (id, name, saving_throw_one, saving_throw_two)
VALUES (30, 'WARRIOR', 'STRENGTH', 'CONSTITUTION');
INSERT INTO classes (id, name, saving_throw_one, saving_throw_two)
VALUES (40, 'WIZARD', 'INTELLIGENCE', 'WISDOM');
INSERT INTO classes (id, name, saving_throw_one, saving_throw_two)
VALUES (50, 'DRUID', 'INTELLIGENCE', 'WISDOM');
INSERT INTO classes (id, name, saving_throw_one, saving_throw_two)
VALUES (60, 'ARTIFICER', 'CONSTITUTION', 'INTELLIGENCE');
INSERT INTO classes (id, name, saving_throw_one, saving_throw_two)
VALUES (70, 'WARLOCK', 'WISDOM', 'CHARISMA');
INSERT INTO classes (id, name, saving_throw_one, saving_throw_two)
VALUES (80, 'BLOOD_HUNTER', 'DEXTERITY', 'INTELLIGENCE');
INSERT INTO classes (id, name, saving_throw_one, saving_throw_two)
VALUES (90, 'MONK', 'STRENGTH', 'DEXTERITY');
INSERT INTO classes (id, name, saving_throw_one, saving_throw_two)
VALUES (100, 'PALADIN', 'WISDOM', 'CHARISMA');
INSERT INTO classes (id, name, saving_throw_one, saving_throw_two)
VALUES (110, 'ROGUE', 'DEXTERITY', 'INTELLIGENCE');
INSERT INTO classes (id, name, saving_throw_one, saving_throw_two)
VALUES (120, 'RANGER', 'STRENGTH', 'DEXTERITY');
INSERT INTO classes (id, name, saving_throw_one, saving_throw_two)
VALUES (130, 'SORCERER', 'CONSTITUTION', 'CHARISMA');

--дальше расовые абилки
--changeset races:1
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (10, 'AARAKOCRA', 'MEDIUM', 25, FALSE, 0, 2, 0, 0, 1, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (20, 'AASIMAR', 'MEDIUM', 30, TRUE, 0, 0, 0, 0, 0, 2); --тут еще разновидности
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (21, 'PROTECTOR_AASIMAR', 'MEDIUM', 30, TRUE, 0, 0, 0, 0, 1, 2); --тут еще разновидности
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (22, 'SCOURGE_AASIMAR', 'MEDIUM', 30, TRUE, 0, 0, 1, 0, 0, 2); --тут еще разновидности
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (23, 'FALLEN_AASIMAR', 'MEDIUM', 30, TRUE, 1, 0, 0, 0, 0, 2); --тут еще разновидности
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (31, 'GITHYANKI', 'MEDIUM', 30, FALSE, 2, 0, 0, 1, 0, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (32, 'GITHZERAI', 'MEDIUM', 30, FALSE, 0, 0, 0, 1, 2, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (40, 'GNOME', 'SMALL', 25, TRUE, 0, 0, 0, 2, 0, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (41, 'FOREST_GNOME', 'SMALL', 25, TRUE, 0, 1, 0, 2, 0, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (42, 'ROCK_GNOME', 'SMALL', 25, TRUE, 0, 0, 1, 2, 0, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (50, 'GOLIATH', 'MEDIUM', 30, FALSE, 2, 0, 1, 0, 0, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (60, 'DWARF', 'MEDIUM', 25, TRUE, 0, 0, 2, 0, 0, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (61, 'MOUNTAIN_DWARF', 'MEDIUM', 25, TRUE, 2, 0, 2, 0, 0, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (62, 'HILL_DWARF', 'MEDIUM', 25, TRUE, 0, 0, 2, 0, 1, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (301, 'WHITE_DRAGONBORN', 'MEDIUM', 30, FALSE, 2, 0, 0, 0, 0, 1);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (302, 'BRONZE_DRAGONBORN', 'MEDIUM', 30, FALSE, 2, 0, 0, 0, 0, 1);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (303, 'GOLD_DRAGONBORN', 'MEDIUM', 30, FALSE, 2, 0, 0, 0, 0, 1);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (304, 'GREEN_DRAGONBORN', 'MEDIUM', 30, FALSE, 2, 0, 0, 0, 0, 1);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (305, 'RED_DRAGONBORN', 'MEDIUM', 30, FALSE, 2, 0, 0, 0, 0, 1);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (306, 'BRASS_DRAGONBORN', 'MEDIUM', 30, FALSE, 2, 0, 0, 0, 0, 1);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (307, 'COPPER_DRAGONBORN', 'MEDIUM', 30, FALSE, 2, 0, 0, 0, 0, 1);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (308, 'SILVER_DRAGONBORN', 'MEDIUM', 30, FALSE, 2, 0, 0, 0, 0, 1);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (309, 'BLUE_DRAGONBORN', 'MEDIUM', 30, FALSE, 2, 0, 0, 0, 0, 1);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (310, 'BLACK_DRAGONBORN', 'MEDIUM', 30, FALSE, 2, 0, 0, 0, 0, 1);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (80, 'HALFORC', 'MEDIUM', 30, TRUE, 2, 0, 1, 0, 0, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (90, 'HALFLING', 'SMALL', 25, FALSE, 0, 2, 0, 0, 0, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (91, 'STOUT_HALFLING', 'SMALL', 25, FALSE, 0, 2, 1, 0, 0, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (92, 'LIGHTFOOT_HALFLING', 'SMALL', 25, FALSE, 0, 2, 0, 0, 0, 1);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (100, 'HALFELF', 'MEDIUM', 30, TRUE, 0, 0, 0, 0, 0,
        2); --тут нужно подумать, как сделать еще +1 к 2-м хар-кам на выбор
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (110, 'TIEFLING', 'MEDIUM', 30, TRUE, 0, 0, 0, 1, 0, 2);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (120, 'HUMAN', 'MEDIUM', 30, FALSE, 1, 1, 1, 1, 1, 1);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (130, 'ELF', 'MEDIUM', 35, TRUE, 0, 2, 0, 0, 0, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (131, 'HIGH_ELF', 'MEDIUM', 30, TRUE, 0, 2, 0, 1, 0, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (132, 'WOOD_ELF', 'MEDIUM', 35, TRUE, 0, 2, 0, 0, 1, 0);
INSERT INTO races (id, name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus,
                   intelligence_bonus, wisdom_bonus, charisma_bonus)
VALUES (133, 'DROW', 'MEDIUM', 30, TRUE, 0, 2, 0, 0, 0, 1);

--дальше расовые абилки
--changeset abilities:1 (aarakocra)
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ААРАКОКРА: ПОЛЕТ',
        'Вы можете летать со скоростью 50 футов. Для этого вы не должны носить ни средний, ни тяжёлый доспех.', 'RACE',
        false, 'BLANK', false, 'NONE', 1, null, 10);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ААРАКОКРА: КОГТИ', 'Вы владеете своей безоружной атакой, которая причиняет при попадании рубящий урон 1к4.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 10);

--changeset abilities:2 (aasimar)
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НЕБЕСНОЕ СОПРОТИВЛЕНИЕ', 'У вас есть сопротивление урону излучением и некротической энергией.', 'RACE', false,
        'BLANK', false, 'NONE', 1, null, 20);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ИСЦЕЛЯЮЩИЕ РУКИ',
        'Действием вы можете коснуться существа и восстановить ему количество хитов, равное вашему уровню. Вы не сможете вновь воспользоваться этой способностью пока не закончите продолжительный отдых.',
        'RACE', true, 'ACTION', true, 'LONG', 1, null, 20);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НЕСУЩИЙ СВЕТ',
        'Действием вы можете коснуться существа и восстановить ему количество хитов, равное вашему уровню. Вы не сможете вновь воспользоваться этой способностью пока не закончите продолжительный отдых.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 20);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('СИЯЮЩАЯ ДУША', 'Начиная с 3-го уровня, вы можете действием высвободить божественную энергию внутри себя, заставляя ваши глаза мерцать, а два светящихся, бестелесных крыла вырасти у вас за спиной. Ваше превращение длится 1 минуту или пока вы не окончите его бонусным действием.
Во время превращения вы получаете скорость полёта 30 футов и раз в свой ход вы можете нанести дополнительный урон излучением одной цели, когда вы наносите ей урон атакой или заклинанием. Дополнительный урон излучением равен вашему уровню.
После того, как вы используете эту особенность, вы не можете использовать её снова, пока не закончите продолжительный отдых.',
        'RACE', true, 'ACTION', true, 'LONG', 3, null, 21);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ИСПУСКАНИЕ СИЯНИЯ', 'Начиная с 3-го уровня, вы можете действием высвободить божественную энергию внутри себя, заставляя себя излучать испепеляющий свет, льющийся из ваших глаз и рта и угрожающий опалить вас. Ваше превращение длится 1 минуту или пока вы не окончите его бонусным действием.
Во время превращения вы излучаете яркий свет в пределах 10 футов и тусклый свет в пределах ещё 10 футов, и в конце каждого вашего хода, вы и каждое существо в пределах 10 футов от вас получаете урон излучением, равный половине Вашего уровня (округленного в большую сторону).
Кроме того, раз в свой ход вы можете нанести дополнительный урон излучением одной цели, когда вы наносите ей урон атакой или заклинанием. Дополнительный урон излучением равен вашему уровню.
После того, как вы используете эту особенность, вы не можете использовать её снова, пока не закончите продолжительный отдых.',
        'RACE', true, 'ACTION', true, 'LONG', 3, null, 22);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('САВАН СМЕРТИ', 'Начиная с 3-го уровня, вы можете действием высвободить божественную энергию внутри себя, заставляя ваши глаза превратиться в бездну тьмы, а два призрачных, не способных взлететь скелета крыльев вырасти у вас за спиной.
В момент вашего превращения, другие существа в пределах 10 футов, которые могут вас видеть, должны преуспеть в спасброске Харизмы (Сл 8 + ваш бонус мастерства + ваш модификатор Харизмы) или станут испуганными вами до конца вашего следующего хода. Ваше превращение длится 1 минуту или пока вы не закончите его бонусным действием.
Во время превращения раз в свой ход вы можете нанести дополнительный урон некротической энергией одной цели, когда вы наносите ей урон атакой или заклинанием. Дополнительный урон некротической энергией равен вашему уровню.
После того, как вы используете эту особенность, вы не можете использовать её снова, пока не закончите продолжительный отдых.',
        'RACE', true, 'ACTION', true, 'LONG', 3, null, 23);

--changeset abilities:3 (gith)
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ДЕКАДЕНТСКОЕ МАСТЕРСТВО',
        'Вы изучаете один язык по вашему выбору и получаете владение одним навыком или инструментом по вашему выбору.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 31);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ВОИНСКОЕ ВОСПИТАНИЕ', 'Вы владеете легкими и средними доспехами, короткими, длинными и двуручными мечами.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 31);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ПСИОНИКА ГИТЪЯНКИ', 'Начиная с 3-го уровня, вы можете накладывать заклинание прыжок с помощью этой особенности. Начиная с 5-го уровня, вы также можете накладывать заклинание туманный шаг с помощью этой особенности. После накладывания одного из этих заклинаний с помощью особенности вы должны закончить продолжительный отдых, прежде чем сможете вновь наложить это заклинание таким образом.
Кроме того, вы знаете заговор волшебная рука [mage hand]. Волшебная рука, наложенная при помощи этой особенности, невидима.
Базовой характеристикой для этих заклинаний является Интеллект. Заклинания, накладываемые с помощью данной способности, не требуют материальных компонентов.',
        'RACE', true, 'BLANK', true, 'LONG', 3, null, 31);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('МЕНТАЛЬНАЯ ДИСЦИПЛИНА',
        'Под руководством наставников гитцераи учатся управлять собственным разумом. Вы получаете преимущество на спасброски от состояния очарован и испуган.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 32);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ПСИОНИКА ГИТЦЕРАЕВ', 'Начиная с 3-го уровня, вы можете накладывать заклинание щит с помощью этой особенности. Начиная с 5-го уровня, вы также можете накладывать заклинание обнаружение мыслей с помощью этой особенности. После накладывания одного из этих заклинаний с помощью особенности вы должны закончить продолжительный отдых, прежде чем сможете вновь наложить это заклинание таким образом.
Кроме того, вы знаете заговор волшебная рука [mage hand]. Волшебная рука, наложенная при помощи этой особенности, невидима.
Базовой характеристикой для этих заклинаний является Интеллект. Заклинания, накладываемые с помощью данной способности, не требуют материальных компонентов.',
        'RACE', true, 'BLANK', true, 'LONG', 3, null, 32);

--changeset abilities:4 (gnome)
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ГНОМЬЯ ХИТРОСТЬ', 'Вы совершаете с преимуществом спасброски Интеллекта, Мудрости и Харизмы против магии.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 40);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ПРИРОДНАЯ ИЛЛЮЗИЯ',
        'Вы знаете заклинание малая иллюзия. Базовой характеристикой для его использования является Интеллект.', 'RACE',
        false, 'BLANK', false, 'NONE', 1, null, 41);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ОБЩЕНИЕ С МАЛЕНЬКИМИ ЗВЕРЬМИ',
        'С помощью звуков и жестов вы можете передавать простые понятия маленьким или ещё меньшим зверям. Лесные гномы любят животных и часто держат белок, барсуков, кроликов, кротов, дятлов и других животных в качестве питомцев.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 41);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('РЕМЕСЛЕННЫЕ ЗНАНИЯ',
        'При совершении проверки Интеллекта (История) применительно к магическому, алхимическому или технологическому объекту, вы можете добавить к проверке удвоенный бонус мастерства вместо обычного.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 42);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ЖЕСТЯНЩИК', 'Вы владеете ремесленными инструментами (инструменты жестянщика). С их помощью вы можете, потратив 1 час времени и материалы на сумму в 10 зм, создать Крошечное механическое устройство (КД 5, 1 хит). Это устройство перестаёт работать через 24 часа (если вы не потратите 1 час на поддержание его работы). Вы можете действием разобрать его; в этом случае вы можете получить обратно использованные материалы. Одновременно вы можете иметь не более трёх таких устройств.
При создании устройства выберите один из следующих вариантов:
• Заводная игрушка. Эта заводная игрушка изображает животное, чудовище или существо, вроде лягушки, мыши, птицы, дракона или солдатика. Поставленная на землю, она проходит 5 футов в случайном направлении за каждый ваш ход, издавая звуки, соответствующие изображаемому существу.
• Зажигалка. Это устройство производит миниатюрный огонёк, с помощью которого можно зажечь свечу, факел или костёр. Использование этого устройства требует действия.
• Музыкальная шкатулка. При открытии эта шкатулка проигрывает мелодию средней громкости. Шкатулка перестаёт играть если мелодия закончилась или если шкатулку закрыли.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 42);

--changeset abilities:5 (goliath)
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ИСТИННЫЙ АТЛЕТ', 'Вы владеете навыком Атлетика.', 'RACE', false, 'BLANK', false, 'NONE', 1, null, 50);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('СИЛЬНЫЕ РУКИ',
        'При определении вашей грузоподъёмности, а также веса, который вы можете толкать, тянуть и поднимать, вы считаетесь существом на один размер больше.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 50);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('РОЖДЕННЫЙ В ГОРАХ',
        'Вы получаете сопротивление урону холодом. Кроме того, вы акклиматизированы к большой высоте, включая высоту более 20 000 футов (6 километров). Вы также адаптированы к холодным климатическим условиям.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 50);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ВЫНОСЛИВОСТЬ КАМНЯ',
        'Вы можете сосредоточиться, чтобы уменьшить полученные повреждения. При получении урона вы можете реакцией бросить к12 и прибавить к результату модификатор Телосложения, а после уменьшить полученный урон на итоговую сумму. Вы не сможете вновь воспользоваться этой особенностью, пока не закончите короткий или продолжительный отдых.',
        'RACE', true, 'BLANK', true, 'SHORT', 1, null, 50);

--changeset abilities:6 (dwarf)
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ДВАРФСКАЯ УСТОЙЧИВОСТЬ',
        'Вы совершаете с преимуществом спасброски от яда, и вы получаете сопротивление к урону ядом.', 'RACE', false,
        'BLANK', false, 'NONE', 1, null, 60);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ДВАРФ: БОЕВАЯ ТРЕНИРОВКА', 'Вы владеете боевым топором, ручным топором, лёгким и боевым молотами.', 'RACE',
        false, 'BLANK', false, 'NONE', 1, null, 60);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ВЛАДЕНИЕ ИНСТРУМЕНТАМИ',
        'Вы владеете ремесленными инструментами на ваш выбор: инструменты кузнеца, пивовара или каменщика.', 'RACE',
        false, 'BLANK', false, 'NONE', 1, null, 60);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ЗНАНИЕ КАМНЯ',
        'Если вы совершаете проверку Интеллекта (История), связанную с происхождением работы по камню, вы считаетесь владеющим навыком История, и добавляете к проверке удвоенный бонус мастерства вместо обычного.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 60);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ДВАРФ: ВЛАДЕНИЕ ДОСПЕХОМ', 'Вы владеете лёгкими и средними доспехами.', 'RACE', false, 'BLANK', false, 'NONE',
        1, null, 61);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ДВАРФСКАЯ ВЫДЕРЖКА',
        'Максимальное значение ваших хитов увеличивается на 1, и вы получаете 1 дополнительный хит с каждым новым уровнем.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 62);

--changeset abilities:7 (dragonborn)
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НАСЛЕДИЕ ДРАКОНОВ', 'Вы получаете сопротивление к урону холодом.', 'RACE', false, 'BLANK', false, 'NONE', 1,
        null, 301);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ОРУЖИЕ ДЫХАНИЯ', 'Когда вы используете оружие дыхания, все существа в конусе 15 фт. конуса должны совершить спасбросок Телосложения. Сложность этого спасброска равна 8 + ваш модификатор Телосложения + ваш бонус мастерства. Существа получают урон холодом 2к6 в случае проваленного спасброска, или половину этого урона, если спасбросок был успешен. Урон повышается до 3к6 на 6 уровне, до 4к6 на 11, и до 5к6 на 16 уровне.
После использования оружия дыхания вы не можете использовать его повторно, пока не завершите короткий либо продолжительный отдых.',
        'RACE', true, 'ACTION', true, 'SHORT', 1, null, 301);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НАСЛЕДИЕ ДРАКОНОВ', 'Вы получаете сопротивление к урону электричеством.', 'RACE', false, 'BLANK', false,
        'NONE', 1, null, 302);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ОРУЖИЕ ДЫХАНИЯ', 'Когда вы используете оружие дыхания, все существа в линии длиной 30 фт. и шириной 5 фт. должны совершить спасбросок Ловкости. Сложность этого спасброска равна 8 + ваш модификатор Телосложения + ваш бонус мастерства. Существа получают урон электричеством 2к6 в случае проваленного спасброска, или половину этого урона, если спасбросок был успешен. Урон повышается до 3к6 на 6 уровне, до 4к6 на 11, и до 5к6 на 16 уровне.
После использования оружия дыхания вы не можете использовать его повторно, пока не завершите короткий либо продолжительный отдых.',
        'RACE', true, 'ACTION', true, 'SHORT', 1, null, 302);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НАСЛЕДИЕ ДРАКОНОВ', 'Вы получаете сопротивление к урону огнем.', 'RACE', false, 'BLANK', false, 'NONE', 1,
        null, 303);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ОРУЖИЕ ДЫХАНИЯ', 'Когда вы используете оружие дыхания, все существа в конусе 15 фт. должны совершить спасбросок Ловкости. Сложность этого спасброска равна 8 + ваш модификатор Телосложения + ваш бонус мастерства. Существа получают урон огнем 2к6 в случае проваленного спасброска, или половину этого урона, если спасбросок был успешен. Урон повышается до 3к6 на 6 уровне, до 4к6 на 11, и до 5к6 на 16 уровне.
После использования оружия дыхания вы не можете использовать его повторно, пока не завершите короткий либо продолжительный отдых.',
        'RACE', true, 'ACTION', true, 'SHORT', 1, null, 303);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НАСЛЕДИЕ ДРАКОНОВ', 'Вы получаете сопротивление к урону ядом.', 'RACE', false, 'BLANK', false, 'NONE', 1, null,
        304);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ОРУЖИЕ ДЫХАНИЯ', 'Когда вы используете оружие дыхания, все существа в конусе 15 фт. должны совершить спасбросок Телосложения. Сложность этого спасброска равна 8 + ваш модификатор Телосложения + ваш бонус мастерства. Существа получают урон ядом 2к6 в случае проваленного спасброска, или половину этого урона, если спасбросок был успешен. Урон повышается до 3к6 на 6 уровне, до 4к6 на 11, и до 5к6 на 16 уровне.
После использования оружия дыхания вы не можете использовать его повторно, пока не завершите короткий либо продолжительный отдых.',
        'RACE', true, 'ACTION', true, 'SHORT', 1, null, 304);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НАСЛЕДИЕ ДРАКОНОВ', 'Вы получаете сопротивление к урону огнем.', 'RACE', false, 'BLANK', false, 'NONE', 1,
        null, 305);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ОРУЖИЕ ДЫХАНИЯ', 'Когда вы используете оружие дыхания, все существа в конусе 15 фт. должны совершить спасбросок Ловкости. Сложность этого спасброска равна 8 + ваш модификатор Телосложения + ваш бонус мастерства. Существа получают урон огнем 2к6 в случае проваленного спасброска, или половину этого урона, если спасбросок был успешен. Урон повышается до 3к6 на 6 уровне, до 4к6 на 11, и до 5к6 на 16 уровне.
После использования оружия дыхания вы не можете использовать его повторно, пока не завершите короткий либо продолжительный отдых.',
        'RACE', true, 'ACTION', true, 'SHORT', 1, null, 305);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НАСЛЕДИЕ ДРАКОНОВ', 'Вы получаете сопротивление к урону огнем.', 'RACE', false, 'BLANK', false, 'NONE', 1,
        null, 306);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ОРУЖИЕ ДЫХАНИЯ', 'Когда вы используете оружие дыхания, все существа в линии длиной 30 фт. и шириной 5 фт. должны совершить спасбросок Ловкости. Сложность этого спасброска равна 8 + ваш модификатор Телосложения + ваш бонус мастерства. Существа получают урон огнем 2к6 в случае проваленного спасброска, или половину этого урона, если спасбросок был успешен. Урон повышается до 3к6 на 6 уровне, до 4к6 на 11, и до 5к6 на 16 уровне.
После использования оружия дыхания вы не можете использовать его повторно, пока не завершите короткий либо продолжительный отдых.',
        'RACE', true, 'ACTION', true, 'SHORT', 1, null, 306);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НАСЛЕДИЕ ДРАКОНОВ', 'Вы получаете сопротивление к урону кислотой.', 'RACE', false, 'BLANK', false, 'NONE', 1,
        null, 307);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ОРУЖИЕ ДЫХАНИЯ', 'Когда вы используете оружие дыхания, все существа в линии длиной 30 фт. и шириной 5 фт. должны совершить спасбросок Ловкости. Сложность этого спасброска равна 8 + ваш модификатор Телосложения + ваш бонус мастерства. Существа получают урон кислотой 2к6 в случае проваленного спасброска, или половину этого урона, если спасбросок был успешен. Урон повышается до 3к6 на 6 уровне, до 4к6 на 11, и до 5к6 на 16 уровне.
После использования оружия дыхания вы не можете использовать его повторно, пока не завершите короткий либо продолжительный отдых.',
        'RACE', true, 'ACTION', true, 'SHORT', 1, null, 307);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НАСЛЕДИЕ ДРАКОНОВ', 'Вы получаете сопротивление к урону холодом.', 'RACE', false, 'BLANK', false, 'NONE', 1,
        null, 308);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ОРУЖИЕ ДЫХАНИЯ', 'Когда вы используете оружие дыхания, все существа в конусе 15 фт. конуса должны совершить спасбросок Телосложения. Сложность этого спасброска равна 8 + ваш модификатор Телосложения + ваш бонус мастерства. Существа получают урон холодом 2к6 в случае проваленного спасброска, или половину этого урона, если спасбросок был успешен. Урон повышается до 3к6 на 6 уровне, до 4к6 на 11, и до 5к6 на 16 уровне.
После использования оружия дыхания вы не можете использовать его повторно, пока не завершите короткий либо продолжительный отдых.',
        'RACE', true, 'ACTION', true, 'SHORT', 1, null, 308);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НАСЛЕДИЕ ДРАКОНОВ', 'Вы получаете сопротивление к урону электричеством.', 'RACE', false, 'BLANK', false,
        'NONE', 1, null, 309);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ОРУЖИЕ ДЫХАНИЯ', 'Когда вы используете оружие дыхания, все существа в линии длиной 30 фт. и шириной 5 фт. должны совершить спасбросок Ловкости. Сложность этого спасброска равна 8 + ваш модификатор Телосложения + ваш бонус мастерства. Существа получают урон электричеством 2к6 в случае проваленного спасброска, или половину этого урона, если спасбросок был успешен. Урон повышается до 3к6 на 6 уровне, до 4к6 на 11, и до 5к6 на 16 уровне.
После использования оружия дыхания вы не можете использовать его повторно, пока не завершите короткий либо продолжительный отдых.',
        'RACE', true, 'ACTION', true, 'SHORT', 1, null, 309);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НАСЛЕДИЕ ДРАКОНОВ', 'Вы получаете сопротивление к урону кислотой.', 'RACE', false, 'BLANK', false, 'NONE', 1,
        null, 310);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ОРУЖИЕ ДЫХАНИЯ', 'Когда вы используете оружие дыхания, все существа в линии длиной 30 фт. и шириной 5 фт. должны совершить спасбросок Ловкости. Сложность этого спасброска равна 8 + ваш модификатор Телосложения + ваш бонус мастерства. Существа получают урон кислотой 2к6 в случае проваленного спасброска, или половину этого урона, если спасбросок был успешен. Урон повышается до 3к6 на 6 уровне, до 4к6 на 11, и до 5к6 на 16 уровне.
После использования оружия дыхания вы не можете использовать его повторно, пока не завершите короткий либо продолжительный отдых.',
        'RACE', true, 'ACTION', true, 'SHORT', 1, null, 310);

--changeset abilities:8 (halforc)
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('УГРОЖАЮЩИЙ ВИД', 'Вы владеете навыком Запугивание.', 'RACE', false, 'BLANK', false, 'NONE', 1, null, 80);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НЕПОКОЛЕБИМАЯ СТОЙКОСТЬ',
        'Если ваши хиты опустились до нуля, но вы при этом не убиты, ваши хиты вместо этого опускаются до 1. Вы не можете использовать эту способность снова, пока не завершите длительный отдых.',
        'RACE', true, 'BLANK', true, 'LONG', 1, null, 80);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('СВИРЕПЫЕ АТАКИ',
        'Если вы совершили критическое попадание рукопашной атакой оружием, вы можете добавить к урону ещё одну кость урона оружия.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 80);

--changeset abilities:9 (halfling)
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ВЕЗУЧИЙ',
        'Если при броске атаки, проверке характеристики или спасброске у вас выпало «1», вы можете перебросить кость, и должны использовать новый результат.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 90);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ХРАБРЫЙ', 'Вы совершаете с преимуществом спасброски от испуга.', 'RACE', false, 'BLANK', false, 'NONE', 1,
        null, 90);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ПРОВОРС  ТВО ПОЛУРОСЛИКОВ',
        'Вы можете проходить сквозь пространство, занятое существами, чей размер больше вашего.', 'RACE', false,
        'BLANK', false, 'NONE', 1, null, 90);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('УСТОЙЧИВОСТЬ КОРЕНАСТЫХ',
        'Вы совершаете с преимуществом спасброски от яда, и вы получаете сопротивление к урону ядом.', 'RACE', false,
        'BLANK', false, 'NONE', 1, null, 91);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ЕСТЕСТВЕННАЯ СКРЫТНОСТЬ',
        'Вы можете предпринять попытку скрыться даже если заслонены только существом, превосходящими вас в размере как минимум на одну категорию.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 92);

--changeset abilities:10 (halfelf)
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НАСЛЕДИЕ ФЕЙ', 'Вы совершаете с преимуществом спасброски от очарования, и вас невозможно магически усыпить.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 100);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('УНИВЕРСАЛЬНОСТЬ НАВЫКОВ', 'Вы получаете владение двумя навыками на ваш выбор.', 'RACE', false, 'BLANK', false,
        'NONE', 1, null, 100);

--changeset abilities:11 (tiefling)
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('АДСКОЕ СОПРОТИВЛЕНИЕ', 'Вы получаете сопротивление к урону огнём.', 'RACE', false, 'BLANK', false, 'NONE', 1,
        null, 110);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ДЬЯВОЛЬСКОЕ НАСЛЕДИЕ УР.1', 'Вы знаете заклинание чудотворство.', 'RACE', false, 'BLANK', false, 'NONE', 1,
        null, 110);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ДЬЯВОЛЬСКОЕ НАСЛЕДИЕ УР.2',
        'Вы можете один раз в день активировать адское возмездие как заклинание 2 уровня. Базовой характеристикой для этих заклинаний является Харизма.',
        'RACE', true, 'REACTION', true, 'LONG', 3, null, 110);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ДЬЯВОЛЬСКОЕ НАСЛЕДИЕ УР.3',
        'Вы можете один раз в день активировать заклинание тьма. Базовой характеристикой для этих заклинаний является Харизма.',
        'RACE', true, 'ACTION', true, 'LONG', 5, null, 110);

--changeset abilities:12 (elf)
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ОБОСТРЕННЫЕ ЧУВСТВА', 'Вы владеете навыком Внимательность.', 'RACE', false, 'BLANK', false, 'NONE', 1, null,
        130);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('НАСЛЕДИЕ ФЕЙ', 'Вы совершаете с преимуществом спасброски от очарования, и вас невозможно магически усыпить.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 130);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ТРАНС',
        'Эльфы не спят. Вместо этого они погружаются в глубокую медитацию, находясь в полубессознательном состоянии до 4 часов в сутки. Во время этой медитации вы можете грезить о разных вещах. Некоторые из этих грёз являются ментальными упражнениями, выработанными за годы тренировок. После такого отдыха вы получаете все преимущества, которые получает человек после 8 часов сна.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 130);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ЭЛЬФИЙСКОЕ ОРУЖИЕ', 'Вы владеете длинным мечом, коротким мечом, коротким и длинным луками.', 'RACE', false,
        'BLANK', false, 'NONE', 1, null, 131);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ЗАГОВОР',
        'Вы знаете один заговор из списка заклинаний волшебника. Базовой характеристикой для его использования является Интеллект.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 131);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ЯЗЫКИ', 'Вы можете говорить, читать и писать на ещё одном языке, на ваш выбор.', 'RACE', false, 'BLANK', false,
        'NONE', 1, null, 131);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ЭЛЬФИЙСКОЕ ОРУЖИЕ', 'Вы владеете длинным мечом, коротким мечом, коротким и длинным луками.', 'RACE', false,
        'BLANK', false, 'NONE', 1, null, 132);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('БЫСТРЫЕ НОГИ', 'Ваша базовая скорость перемещения увеличивается до 35 футов.', 'RACE', false, 'BLANK', false,
        'NONE', 1, null, 132);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('МАСКИРОВКА',
        'Вы можете предпринять попытку спрятаться, даже если вы слабо заслонены листвой, сильным дождём, снегопадом, туманом или другими природными явлениями.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 132);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ПРЕВОСХОДНОЕ ТЕМНОЕ ЗРЕНИЕ', 'Ваше тёмное зрение имеет радиус 120 футов.', 'RACE', false, 'BLANK', false,
        'NONE', 1, null, 133);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ЧУВСТВИТЕЛЬНОСТЬ К СОЛНЦУ',
        'Вы совершаете с помехой броски атаки и проверки Мудрости (Внимательность), основанные на зрении, если вы, цель вашей атаки или изучаемый предмет расположены на прямом солнечном свете.',
        'RACE', false, 'BLANK', false, 'NONE', 1, null, 133);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ОРУЖИЕ ДРОУ', 'Вы владеете рапирой, коротким мечом и ручным арбалетом.', 'RACE', false, 'BLANK', false, 'NONE',
        1, null, 133);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('МАГИЯ ДРОУ УР.1',
        'Вы знаете заклинание пляшущие огоньки. Базовой характеристикой для их использования является Харизма.', 'RACE',
        false, 'BLANK', false, 'NONE', 1, null, 133);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('МАГИЯ ДРОУ УР.2',
        'Вы можете один раз в день использовать заклинание огонь фей. Базовой характеристикой для их использования является Харизма.',
        'RACE', true, 'ACTION', true, 'LONG', 3, null, 133);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('МАГИЯ ДРОУ УР.3',
        'Вы можете раз в день использовать заклинание тьма. Базовой характеристикой для их использования является Харизма.',
        'RACE', true, 'ACTION', true, 'LONG', 5, null, 133);

--дальше классовые абилки
--changeset abilities:13 (bard)
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ВДОХНОВЕНИЕ БАРДА (к6)',
        'Своими словами или музыкой вы можете вдохновлять других. Для этого вы должны бонусным действием выбрать одно существо, отличное от вас, в пределах 60 футов, которое может вас слышать. Это существо получает кость бардовского вдохновения — к6.' ||
        'В течение следующих 10 минут это существо может один раз бросить эту кость и добавить результат к проверке характеристики, броску атаки или спасброску, который оно совершает. Существо может принять решение о броске кости вдохновения уже после броска к20, но должно сделать это прежде, чем Мастер объявит результат броска. ' ||
        'Как только кость бардовского вдохновения брошена, она исчезает. Существо может иметь только одну такую кость одновременно.' ||
        'Вы можете использовать это умение количество раз, равное модификатору вашей Харизмы, но как минимум один раз. Потраченные использования этого умения восстанавливаются после продолжительного отдыха.' ||
        'Ваша кость бардовского вдохновения изменяется с ростом вашего уровня в этом классе. Она становится к8 на 5 уровне, к10 на 10 уровне и к12 на 15 уровне.',
        'CLASS', true, 'BONUS_ACTION', true, 'LONG', 1, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('МАСТЕР НА ВСЕ РУКИ',
        'Вы можете добавлять половину бонуса мастерства, округлённую в меньшую сторону, ко всем проверкам характеристик, куда этот бонус ещё не включён.',
        'CLASS', false, 'BLANK', false, 'NONE', 2, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ПЕСНЬ ОТДЫХА (к6)',
        'Вы с помощью успокаивающей музыки или речей можете помочь своим раненым союзникам восстановить их силы во время короткого отдыха. ' ||
        'Если вы, или любые союзные существа, способные слышать ваше исполнение, восстанавливаете хиты в конце короткого отдыха, каждый из вас восстанавливает дополнительно 1к6 хитов. ' ||
        'Для того, чтобы восстановить дополнительные хиты, существо должно потратить в конце короткого отдыха как минимум одну Кость Хитов.',
        'CLASS', true, 'FREE_ACTION', false, 'NONE', 2, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('КОЛЛЕГИЯ БАРДОВ',
        'Вы углубляетесь в традиции выбранной вами коллегии бардов: коллегии знаний или коллегии доблести.', 'CLASS',
        false, 'BLANK', false, 'NONE', 3, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('КОМПЕТЕНТНОСТЬ',
        'Выберите 2 навыка из тех, которыми вы владеете. Ваш бонус мастерства для этих навыков удваивается.', 'CLASS',
        false, 'BLANK', false, 'NONE', 3, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('УВЕЛИЧЕНИЕ ХАРАКТЕРИСТИК',
        'Вы можете повысить значение одной из ваших характеристик на 2 или двух характеристик на 1. Как обычно, значение характеристики при этом не должно превысить 20.',
        'CLASS', false, 'BLANK', false, 'NONE', 4, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ВДОХНОВЕНИЕ БАРДА (к8)',
        'Вы можете вдохновлять других словами или музыкой. Бонусным действием выберите существо в пределах 60 футов, которое может услышать вас. Это существо получает кость бардовского вдохновения (к8). В течение следующих 10 минут оно может один раз бросить эту кость и добавить результат к проверке характеристики, броску атаки или спасброску. Кость исчезает после броска. Вы можете использовать это умение число раз, равное модификатору вашей Харизмы, но как минимум один раз. Потраченные использования восстанавливаются после продолжительного отдыха.',
        'CLASS', true, 'BONUS_ACTION', true, 'SHORT', 5, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ИСТОЧНИК ВДОХНОВЕНИЯ',
        'Вы восстанавливаете истраченные вдохновения барда и после короткого и после продолжительного отдыха.', 'CLASS',
        false, 'BLANK', false, 'NONE', 5, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('КОНТРОЧАРОВАНИЕ',
        'Вы получаете возможность использовать звуки или слова силы для разрушения воздействующих на разум эффектов. Вы можете действием начать исполнение, которое продлится до конца вашего следующего хода. В течение этого времени вы и все дружественные существа в пределах 30 футов от вас совершают спасброски от запугивания и очарования с преимуществом. Чтобы получить это преимущество, существа должны слышать вас. Исполнение заканчивается преждевременно, если вы оказываетесь недееспособны, теряете способность говорить, или прекращаете исполнение добровольно (на это не требуется действие).',
        'CLASS', true, 'ACTION', false, 'NONE', 6, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('УВЕЛИЧЕНИЕ ХАРАКТЕРИСТИК',
        'Вы можете повысить значение одной из ваших характеристик на 2 или двух характеристик на 1. Как обычно, значение характеристики при этом не должно превысить 20.',
        'CLASS', false, 'BLANK', false, 'NONE', 8, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ПЕСНЬ ОТДЫХА (к8)',
        'Вы с помощью успокаивающей музыки или речей можете помочь своим раненым союзникам восстановить их силы во время короткого отдыха. Если вы, или любые союзные существа, способные слышать ваше исполнение, восстанавливаете хиты в конце короткого отдыха, каждый из вас восстанавливает дополнительно 1к8 хитов. Для того, чтобы восстановить дополнительные хиты, существо должно потратить в конце короткого отдыха как минимум одну Кость Хитов.',
        'CLASS', true, 'FREE_ACTION', false, 'NONE', 9, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('КОМПЕТЕНТНОСТЬ',
        'Выберите 2 навыка из тех, которыми вы владеете. Ваш бонус мастерства для этих навыков удваивается.', 'CLASS',
        false, 'BLANK', false, 'NONE', 10, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ВДОХНОВЕНИЕ БАРДА (к10)',
        'Вы можете вдохновлять других словами или музыкой. Бонусным действием выберите существо в пределах 60 футов, которое может услышать вас. Это существо получает кость бардовского вдохновения (к10). В течение следующих 10 минут оно может один раз бросить эту кость и добавить результат к проверке характеристики, броску атаки или спасброску. Кость исчезает после броска. Вы можете использовать это умение число раз, равное модификатору вашей Харизмы, но как минимум один раз. Потраченные использования восстанавливаются после продолжительного отдыха.',
        'CLASS', true, 'BONUS_ACTION', true, 'SHORT', 10, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ТАЙНЫ МАГИИ',
        'Вы успели набрать знаний из самого широкого спектра магических дисциплин. Выберите два заклинания любого класса, включая ваш собственный. Эти заклинания должны быть того уровня, который вы можете использовать, или являться заговорами. Теперь эти заклинания считаются для вас заклинаниями барда, и они уже включены в общее количество известных вам заклинаний согласно таблице «Бард».',
        'CLASS', false, 'BLANK', false, 'NONE', 10, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('УВЕЛИЧЕНИЕ ХАРАКТЕРИСТИК',
        'Вы можете повысить значение одной из ваших характеристик на 2 или двух характеристик на 1. Как обычно, значение характеристики при этом не должно превысить 20.',
        'CLASS', false, 'BLANK', false, 'NONE', 12, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ПЕСНЬ ОТДЫХА (к10)',
        'Вы с помощью успокаивающей музыки или речей можете помочь своим раненым союзникам восстановить их силы во время короткого отдыха. Если вы, или любые союзные существа, способные слышать ваше исполнение, восстанавливаете хиты в конце короткого отдыха, каждый из вас восстанавливает дополнительно 1к10)хитов. Для того, чтобы восстановить дополнительные хиты, существо должно потратить в конце короткого отдыха как минимум одну Кость Хитов.',
        'CLASS', true, 'FREE_ACTION', false, 'NONE', 13, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ТАЙНЫ МАГИИ',
        'Вы успели набрать знаний из самого широкого спектра магических дисциплин. Выберите два заклинания любого класса, включая ваш собственный. Эти заклинания должны быть того уровня, который вы можете использовать, или являться заговорами. Теперь эти заклинания считаются для вас заклинаниями барда, и они уже включены в общее количество известных вам заклинаний согласно таблице Бард.',
        'CLASS', false, 'BLANK', false, 'NONE', 14, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ВДОХНОВЕНИЕ БАРДА (к12)',
        'Вы можете вдохновлять других словами или музыкой. Бонусным действием выберите существо в пределах 60 футов, которое может услышать вас. Это существо получает кость бардовского вдохновения (к12). В течение следующих 10 минут оно может один раз бросить эту кость и добавить результат к проверке характеристики, броску атаки или спасброску. Кость исчезает после броска. Вы можете использовать это умение число раз, равное модификатору вашей Харизмы, но как минимум один раз. Потраченные использования восстанавливаются после продолжительного отдыха.',
        'CLASS', true, 'BONUS_ACTION', true, 'SHORT', 15, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('УВЕЛИЧЕНИЕ ХАРАКТЕРИСТИК',
        'Вы можете повысить значение одной из ваших характеристик на 2 или двух характеристик на 1. Как обычно, значение характеристики при этом не должно превысить 20.',
        'CLASS', true, 'ACTION', true, 'NONE', 16, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ПЕСНЬ ОТДЫХА (к12)',
        'Вы с помощью успокаивающей музыки или речей можете помочь своим раненым союзникам восстановить их силы во время короткого отдыха. Если вы, или любые союзные существа, способные слышать ваше исполнение, восстанавливаете хиты в конце короткого отдыха, каждый из вас восстанавливает дополнительно 1к12 хитов. Для того, чтобы восстановить дополнительные хиты, существо должно потратить в конце короткого отдыха как минимум одну Кость Хитов.',
        'CLASS', true, 'FREE_ACTION', false, 'NONE', 17, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ТАЙНЫ МАГИИ',
        'Вы успели набрать знаний из самого широкого спектра магических дисциплин. Выберите два заклинания любого класса, включая ваш собственный. Эти заклинания должны быть того уровня, который вы можете использовать, или являться заговорами. Теперь эти заклинания считаются для вас заклинаниями барда, и они уже включены в общее количество известных вам заклинаний согласно таблице «Бард».',
        'CLASS', false, 'BLANK', false, 'NONE', 18, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('УВЕЛИЧЕНИЕ ХАРАКТЕРИСТИК',
        'Вы можете повысить значение одной из ваших характеристик на 2 или двух характеристик на 1. Как обычно, значение характеристики при этом не должно превысить 20.',
        'CLASS', false, 'BLANK', false, 'NONE', 19, 10, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ПРЕВОСХОДНОЕ ВДОХНОВЕНИЕ',
        'Если на момент броска инициативы у вас не осталось неиспользованных вдохновений, вы получаете одно.', 'CLASS',
        false, 'BLANK', false, 'NONE', 20, 10, null);

--changeset abilities:14 (barbarian)
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ЗАЩИТА БЕЗ ДОСПЕХОВ',
        'Если вы не носите доспехов, ваш Класс Доспеха равен 10 + модификатор Ловкости + модификатор Телосложения. Вы можете использовать щит, не теряя этого преимущества.',
        'CLASS', false, 'BLANK', false, 'NONE', 1, 20, null);
INSERT INTO abilities (name, description, ability_source, is_active, cost, requires_rest, type_of_rest, level_available,
                       class_source_id, race_source_id)
VALUES ('ЯРОСТЬ', 'В бою вы сражаетесь с первобытной свирепостью. В свой ход вы можете бонусным действием войти в состояние ярости.
В состоянии ярости вы получаете следующие преимущества, если не носите тяжёлый доспех:
Вы совершаете с преимуществом проверки и спасброски Силы.
Если вы совершаете рукопашную атаку оружием, используя Силу, вы получаете бонус к броску урона, соответствующий вашему уровню варвара, как показано в колонке «урон ярости» таблицы «Варвар».
Вы получаете сопротивление дробящему, колющему и рубящему урону.
Если вы способны накладывать заклинания, то вы не можете накладывать или концентрироваться на заклинаниях, пока находитесь в состоянии ярости.
Ваша ярость длится 1 минуту. Она прекращается раньше, если вы потеряли сознание или если вы закончили свой ход, не получив урон или не атаковав враждебное по отношению к вам существо с момента окончания вашего прошлого хода. Также вы можете прекратить свою ярость бонусным действием.
Если вы впадали в состояние ярости максимальное для вашего уровня количество раз (смотрите колонку «ярость»), то вы должны совершить продолжительный отдых, прежде чем сможете использовать ярость ещё раз.', 'CLASS', true, 'BONUS_ACTION', true, 'LONG', 1, 20, null);


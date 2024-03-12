INSERT INTO proficiencies (name, base_attribute) VALUES ('ACROBATICS', 'DEXTERITY');
INSERT INTO proficiencies (name, base_attribute) VALUES ('ATHLETICS', 'STRENGTH');
INSERT INTO proficiencies (name, base_attribute) VALUES ('ARCANA', 'INTELLIGENCE');
INSERT INTO proficiencies (name, base_attribute) VALUES ('DECEPTION', 'CHARISMA');
INSERT INTO proficiencies (name, base_attribute) VALUES ('HISTORY', 'INTELLIGENCE');
INSERT INTO proficiencies (name, base_attribute) VALUES ('INSIGHT', 'WISDOM');
INSERT INTO proficiencies (name, base_attribute) VALUES ('INTIMIDATION', 'CHARISMA');
INSERT INTO proficiencies (name, base_attribute) VALUES ('INVESTIGATION', 'INTELLIGENCE');
INSERT INTO proficiencies (name, base_attribute) VALUES ('MEDICINE', 'WISDOM');
INSERT INTO proficiencies (name, base_attribute) VALUES ('NATURE', 'INTELLIGENCE');
INSERT INTO proficiencies (name, base_attribute) VALUES ('PERCEPTION', 'WISDOM');
INSERT INTO proficiencies (name, base_attribute) VALUES ('PERFORMANCE', 'CHARISMA');
INSERT INTO proficiencies (name, base_attribute) VALUES ('PERSUASION', 'CHARISMA');
INSERT INTO proficiencies (name, base_attribute) VALUES ('RELIGION', 'INTELLIGENCE');
INSERT INTO proficiencies (name, base_attribute) VALUES ('SLEIGHT_OF_HAND', 'DEXTERITY');
INSERT INTO proficiencies (name, base_attribute) VALUES ('STEALTH', 'DEXTERITY');
INSERT INTO proficiencies (name, base_attribute) VALUES ('SURVIVAL', 'WISDOM');
INSERT INTO proficiencies (name, base_attribute) VALUES ('ANIMAL_HANDLING', 'WISDOM');

INSERT INTO classes (name, saving_throw_one, saving_throw_two) VALUES ('BARD', 'DEXTERITY', 'CHARISMA');
INSERT INTO classes (name, saving_throw_one, saving_throw_two) VALUES ('BARBARIAN', 'STRENGTH', 'CONSTITUTION');
INSERT INTO classes (name, saving_throw_one, saving_throw_two) VALUES ('WARRIOR', 'STRENGTH', 'CONSTITUTION');
INSERT INTO classes (name, saving_throw_one, saving_throw_two) VALUES ('WIZARD', 'INTELLIGENCE', 'WISDOM');
INSERT INTO classes (name, saving_throw_one, saving_throw_two) VALUES ('DRUID', 'INTELLIGENCE', 'WISDOM');
INSERT INTO classes (name, saving_throw_one, saving_throw_two) VALUES ('ARTIFICER', 'CONSTITUTION', 'INTELLIGENCE');
INSERT INTO classes (name, saving_throw_one, saving_throw_two) VALUES ('WARLOCK', 'WISDOM', 'CHARISMA');
INSERT INTO classes (name, saving_throw_one, saving_throw_two) VALUES ('BLOOD_HUNTER', 'DEXTERITY', 'INTELLIGENCE');
INSERT INTO classes (name, saving_throw_one, saving_throw_two) VALUES ('MONK', 'STRENGTH', 'DEXTERITY');
INSERT INTO classes (name, saving_throw_one, saving_throw_two) VALUES ('PALADIN', 'WISDOM', 'CHARISMA');
INSERT INTO classes (name, saving_throw_one, saving_throw_two) VALUES ('ROGUE', 'DEXTERITY', 'INTELLIGENCE');
INSERT INTO classes (name, saving_throw_one, saving_throw_two) VALUES ('RANGER', 'STRENGTH', 'DEXTERITY');
INSERT INTO classes (name, saving_throw_one, saving_throw_two) VALUES ('SORCERER', 'CONSTITUTION', 'CHARISMA');

INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('AARAKOCRA', 'MEDIUM', 25, FALSE, 0, 2, 0, 0, 1, 0);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('PROTECTOR_AASIMAR', 'MEDIUM', 30, TRUE, 0, 0, 0 ,0 ,1, 2); --тут еще разновидности
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('SCOURGE_AASIMAR', 'MEDIUM', 30, TRUE, 0, 0, 1 ,0 ,0, 2); --тут еще разновидности
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('FALLEN_AASIMAR', 'MEDIUM', 30, TRUE, 1, 0, 0 ,0 ,0, 2); --тут еще разновидности
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('GITHYANKI', 'MEDIUM', 30, FALSE, 2, 0, 0, 1, 0, 0);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('GITHZERAI', 'MEDIUM', 30, FALSE, 0, 0, 0, 1, 2, 0);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('FOREST_GNOME', 'SMALL', 25, TRUE, 0, 1, 0, 2, 0, 0);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('ROCK_GNOME', 'SMALL', 25, TRUE, 0, 0, 1, 2, 0, 0);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('GOLIATH', 'MEDIUM', 30, FALSE, 2, 0, 1, 0, 0, 0);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('MOUNTAIN_DWARF', 'MEDIUM', 25, TRUE, 2, 0, 2, 0, 0, 0);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('HILL_DWARF', 'MEDIUM', 25, TRUE, 0, 0, 2, 0, 1, 0);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('DRAGONBORN', 'MEDIUM', 30, FALSE, 2, 0, 0, 0, 0, 1);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('HALFORC', 'MEDIUM', 30, TRUE, 2, 0, 1, 0, 0, 0);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('STOUT_HALFLING', 'SMALL', 25, FALSE, 0, 2, 1, 0, 0, 0);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('LIGHTFOOT_HALFLING', 'SMALL', 25, FALSE, 0, 2, 0, 0, 0, 1);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('HALFELF', 'MEDIUM', 30, TRUE, 0, 0, 0, 0, 0, 2); --тут нужно подумать, как сделать еще +1 к 2-м хар-кам на выбор
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('TIEFLING', 'MEDIUM', 30, TRUE, 0, 0, 0, 1, 0, 2);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('HUMAN', 'MEDIUM', 30, FALSE, 1 ,1, 1, 1, 1, 1);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('WOOD_ELF', 'MEDIUM', 35, TRUE, 0, 2, 0, 0, 1, 0);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('HIGH_ELF', 'MEDIUM', 30, TRUE, 0, 2, 0, 1, 0, 0);
INSERT INTO races (name, size, speed, has_darkvision, strength_bonus, dexterity_bonus, constitution_bonus, intelligence_bonus, wisdom_bonus, charisma_bonus) VALUES ('DROW', 'MEDIUM', 30, TRUE, 0, 2, 0, 0, 0, 1);
package ru.maleth.mythra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.maleth.mythra.service.character.enums.CharClassEnum;
import ru.maleth.mythra.service.character.enums.CharRaceEnum;

import java.util.Set;

@Entity
@Table(name = "characters")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String charName;
    @ManyToOne
    @JoinColumn (name = "race_id")
    private Race charRace;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private int experience;
    @Column(name = "armor_class")
    private int armorClass;
    private int initiative;

    @ManyToMany
    @JoinTable(name = "characters_classes",
            joinColumns = {@JoinColumn(name = "fk_character")},
            inverseJoinColumns = {@JoinColumn(name = "fk_class")})
    private Set<CharClass> characterClasses;

    @ManyToMany
    @JoinTable(name = "characters_proficiencies",
            joinColumns = {@JoinColumn(name = "fk_character")},
            inverseJoinColumns = {@JoinColumn(name = "fk_proficiency")})
    private Set<Proficiency> proficiencies;

    @Column(name = "max_hp")
    private int maxHP;
    @Column(name = "current_hp")
    private int currentHP;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    public String getClassName() {
        return CharClassEnum.valueOf(this.getCharacterClasses().stream().findFirst().get().getName()).getName();
    }

}

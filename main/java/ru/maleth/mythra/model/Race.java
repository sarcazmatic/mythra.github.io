package ru.maleth.mythra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.maleth.mythra.service.character.enums.CharClassEnum;
import ru.maleth.mythra.service.character.enums.CharRaceEnum;
import ru.maleth.mythra.service.character.enums.CharSizeEnum;

@Entity
@Table(name = "races")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    private CharSizeEnum size;
    private int speed;
    @Column(name = "has_darkvision")
    private boolean hasDarkvision;

    @Column(name = "strength_bonus")
    private int strengthBonus;
    @Column(name = "dexterity_bonus")
    private int dexterityBonus;
    @Column(name = "constitution_bonus")
    private int constitutionBonus;
    @Column(name = "intelligence_bonus")
    private int intelligenceBonus;
    @Column(name = "wisdom_bonus")
    private int wisdomBonus;
    @Column(name = "charisma_bonus")
    private int charismaBonus;

    public String getRaceName() {
        return CharRaceEnum.valueOf(this.name).getName();
    }

}

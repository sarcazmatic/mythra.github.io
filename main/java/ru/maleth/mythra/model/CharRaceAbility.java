package ru.maleth.mythra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table (name = "character_race_abilities")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CharRaceAbility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "charater_id")
    private Character character;
    @ManyToOne
    @JoinColumn(name = "char_race_id")
    private Race race;
    @ManyToOne
    @JoinColumn(name = "ability_id")
    private Ability ability;
    private Integer numberOfUses;
}

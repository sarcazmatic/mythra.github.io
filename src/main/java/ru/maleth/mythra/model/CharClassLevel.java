package ru.maleth.mythra.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "character_classes_level")
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CharClassLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;
    @ManyToOne
    @JoinColumn(name = "character_class_id")
    private CharClass charClass;
    @Column(name = "char_level")
    private Integer classLevel;

}

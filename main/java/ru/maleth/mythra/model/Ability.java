package ru.maleth.mythra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.maleth.mythra.enums.ActionCostEnum;
import ru.maleth.mythra.enums.RestEnum;

@Entity
@Table(name = "abilities")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "char_class_id")
    private CharClass charClass;
    @Column(name = "class_level")
    private int classLevel;
    @Column(name = "is_active")
    private boolean isActive;
    @Enumerated(EnumType.STRING)
    private ActionCostEnum cost;
    @Column(name = "requires_rest")
    private boolean requiresRest;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_rest")
    private RestEnum typeOfRest;
}

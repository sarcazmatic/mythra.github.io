package ru.maleth.mythra.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.maleth.mythra.enums.AttribEnum;

@Entity
@Table(name = "proficiencies")
@Data
@RequiredArgsConstructor
public class Proficiency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "base_attribute")
    private AttribEnum baseAttribute;

}

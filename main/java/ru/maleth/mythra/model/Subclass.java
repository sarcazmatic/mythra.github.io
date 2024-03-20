package ru.maleth.mythra.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Table(name = "subclasses")
@Entity
@Data
@RequiredArgsConstructor
public class Subclass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "char_class_id")
    private CharClass charClass;

}

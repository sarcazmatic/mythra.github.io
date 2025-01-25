package ru.maleth.mythra.model;

import jakarta.persistence.*;

@Table(name = "conditions")
@Entity
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}

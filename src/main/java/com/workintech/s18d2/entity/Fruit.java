package com.workintech.s18d2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fruits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    // TESTLER setFruitType / getFruitType bekliyor
    @Enumerated(EnumType.STRING)
    private FruitType fruitType;
}



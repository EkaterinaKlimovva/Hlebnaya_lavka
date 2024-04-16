package com.example.HlebnayaLavka.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "hlebnaya_lavka_menu", schema = "hlebnaya_lavka")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_seq")
    @SequenceGenerator(name = "menu_seq", sequenceName = "hlebnaya_lavka_menu_seq", schema = "hlebnaya_lavka", allocationSize = 1)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "cost")
    Double cost;

    @OneToMany(mappedBy = "menu")
    List<Purchase> purchases;
}

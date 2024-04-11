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
@Table(name = "hlebnaya_lavka_customer", schema = "hlebnaya_lavka")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "hlebnaya_lavka_customer_seq", schema = "hlebnaya_lavka", allocationSize = 1)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "sum_cost")
    Double sumCost;

    @OneToMany(mappedBy = "customer")
    List<Purchase> purchases;
}

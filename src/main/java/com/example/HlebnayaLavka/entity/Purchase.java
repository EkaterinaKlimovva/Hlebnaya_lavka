package com.example.HlebnayaLavka.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "hlebnaya_lavka_purchase", schema = "hlebnaya_lavka")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_seq")
    @SequenceGenerator(name = "purchase_seq", sequenceName = "hlebnaya_lavka_purchase_seq", schema = "hlebnaya_lavka", allocationSize = 1)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    Menu menu;

    @Column(name = "count")
    Long count;

    @Column(name = "sum_cost")
    Double sumCost;

    @Column(name = "date_purchase")
    LocalDate datePurchase;
}

package com.example.HlebnayaLavka.repository;

import com.example.HlebnayaLavka.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query
            (
                    "select p " +
                            "from Purchase as p " +
                            "where p.datePurchase between :dateStart and :dateEnd " +
                            "order by p.datePurchase desc"
            )
    List<Purchase> findAllByFilter
            (
                    @Param("dateStart")LocalDate dateStart,
                    @Param("dateEnd")LocalDate dateEnd
            );
}

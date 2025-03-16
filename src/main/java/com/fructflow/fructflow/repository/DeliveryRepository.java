package com.fructflow.fructflow.repository;

import com.fructflow.fructflow.dbase.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByDeliveryDateBetween(LocalDate startDate, LocalDate endDate);
}
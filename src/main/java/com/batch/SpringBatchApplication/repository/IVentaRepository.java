package com.batch.SpringBatchApplication.repository;

import com.batch.SpringBatchApplication.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IVentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByFecha(LocalDate fecha);
}

package com.batch.SpringBatchApplication.repository;

import com.batch.SpringBatchApplication.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}

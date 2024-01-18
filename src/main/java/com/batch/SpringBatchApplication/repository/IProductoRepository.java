package com.batch.SpringBatchApplication.repository;

import com.batch.SpringBatchApplication.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
}

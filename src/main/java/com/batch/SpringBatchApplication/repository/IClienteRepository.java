package com.batch.SpringBatchApplication.repository;

import com.batch.SpringBatchApplication.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}

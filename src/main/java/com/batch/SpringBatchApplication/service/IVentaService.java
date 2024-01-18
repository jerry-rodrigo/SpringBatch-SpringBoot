package com.batch.SpringBatchApplication.service;

import com.batch.SpringBatchApplication.dto.VentaDTO;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    VentaDTO registrarVenta(VentaDTO ventaDTO);
    List<VentaDTO> consultarVentasPorFecha(LocalDate fecha);
    VentaDTO consultarVentaPorId(Long id);
}

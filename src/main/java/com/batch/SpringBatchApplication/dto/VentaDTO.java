package com.batch.SpringBatchApplication.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VentaDTO {

    private Long id;
    private Long idCliente;
    private LocalDate fecha;
}

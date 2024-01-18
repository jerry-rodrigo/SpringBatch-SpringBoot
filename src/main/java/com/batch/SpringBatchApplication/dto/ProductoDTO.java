package com.batch.SpringBatchApplication.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDTO {

    private Long id;
    private String nombre;
    private BigDecimal precio;
}

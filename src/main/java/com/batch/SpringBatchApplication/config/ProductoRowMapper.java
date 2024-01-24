package com.batch.SpringBatchApplication.config;

import com.batch.SpringBatchApplication.dto.ProductoDTO;
import org.springframework.batch.extensions.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;


public class ProductoRowMapper extends BeanWrapperRowMapper<ProductoDTO> {
    public ProductoRowMapper() {
        setTargetType(ProductoDTO.class);
    }

    @Override
    public ProductoDTO mapRow(RowSet rowSet) {
        ProductoDTO productoDTO = new ProductoDTO();
        //productoDTO.setId(Long.parseLong(rowSet.getCurrentRow()[0]));
        productoDTO.setNombre(rowSet.getCurrentRow()[0]);

        String precioString = rowSet.getCurrentRow()[1];
        if (precioString != null && !precioString.isEmpty()) {
            productoDTO.setPrecio(new Float(precioString));
        }

        return productoDTO;
    }
}
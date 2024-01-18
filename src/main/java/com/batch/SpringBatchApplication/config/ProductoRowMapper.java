package com.batch.SpringBatchApplication.config;

import com.batch.SpringBatchApplication.dto.ProductoDTO;
import org.springframework.batch.extensions.excel.mapping.BeanWrapperRowMapper;

public class ProductoRowMapper extends BeanWrapperRowMapper<ProductoDTO> {
    public ProductoRowMapper() {
        setTargetType(ProductoDTO.class);
    }
}

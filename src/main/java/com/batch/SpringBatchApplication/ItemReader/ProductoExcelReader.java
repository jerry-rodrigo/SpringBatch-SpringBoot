package com.batch.SpringBatchApplication.ItemReader;

import com.batch.SpringBatchApplication.dto.ProductoDTO;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.core.io.Resource;

public class ProductoExcelReader implements ItemReader<ProductoDTO> {

    private final Resource resource;

    public ProductoExcelReader(Resource resource) {
        this.resource = resource;
    }

    @Override
    public ProductoDTO read() throws Exception {
        // Implementa la lógica para leer un producto desde el archivo Excel
        return null;
    }

    /*public PoiItemReader<ProductoDTO> excelReader() {
        PoiItemReader<ProductoDTO> reader = new PoiItemReader<>();
        reader.setResource(resource);
        reader.setRowMapper(productoRowMapper());
        return reader;
    }

    private RowMapper<ProductoDTO> productoRowMapper() {
        DefaultRowMapper<ProductoDTO> rowMapper = new DefaultRowMapper<>();
        rowMapper.setTargetType(ProductoDTO.class);
        return rowMapper;
    }*/
}
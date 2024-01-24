package com.batch.SpringBatchApplication.ItemReader;

import com.batch.SpringBatchApplication.config.ProductoRowMapper;
import com.batch.SpringBatchApplication.dto.ProductoDTO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

public class ProductoExcelReader implements ItemReader<ProductoDTO> {

    private final PoiItemReader<ProductoDTO> excelReader;

    public ProductoExcelReader(PoiItemReader<ProductoDTO> excelReader) {
        this.excelReader = excelReader;
    }

    @Override
    public ProductoDTO read() throws Exception {
        try {
            return excelReader.read();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    @StepScope
    public PoiItemReader<ProductoDTO> excelReader() {
        PoiItemReader<ProductoDTO> reader = new PoiItemReader<>();
        reader.setLinesToSkip(1);
        reader.setRowMapper(new ProductoRowMapper());
        return reader;
    }

    @Bean
    @StepScope
    public ItemReader<ProductoDTO> productoExcelReader() {
        PoiItemReader<ProductoDTO> reader = new PoiItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("productos.xlsx"));
        reader.setRowMapper(new ProductoRowMapper());
        return reader;
    }

    @Bean
    public RowMapper<ProductoDTO> productoRowMapper() {
        return new ProductoRowMapper();
    }
}
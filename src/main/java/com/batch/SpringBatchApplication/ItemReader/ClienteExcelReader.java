package com.batch.SpringBatchApplication.ItemReader;

import com.batch.SpringBatchApplication.config.ClienteRowMapper;
import com.batch.SpringBatchApplication.dto.ClienteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

@Slf4j
public class ClienteExcelReader implements ItemReader<ClienteDTO> {

    private final PoiItemReader<ClienteDTO> excelReader;

    public ClienteExcelReader(PoiItemReader<ClienteDTO> excelReader) {
        this.excelReader = excelReader;
    }

    @Override
    public ClienteDTO read() throws Exception {
        try {
            return excelReader.read();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    @StepScope
    public PoiItemReader<ClienteDTO> excelReader() {
        PoiItemReader<ClienteDTO> reader = new PoiItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("clientes.xlsx"));

        try {
            // Configura el RowMapper y otras propiedades según sea necesario
            reader.setRowMapper(new ClienteRowMapper());
            reader.afterPropertiesSet(); // Asegura que las propiedades estén configuradas correctamente
        } catch (Exception e) {
            log.error("Error al configurar el PoiItemReader: {}", e.getMessage(), e);
            throw new RuntimeException("Error al configurar el PoiItemReader", e);
        }

        return reader;
    }

    @Bean
    @StepScope
    public ItemReader<ClienteDTO> clienteExcelReader() {
        PoiItemReader<ClienteDTO> reader = new PoiItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("clientes.xlsx"));
        reader.setRowMapper(new ClienteRowMapper());
        return reader;
    }

    @Bean
    public RowMapper<ClienteDTO> clienteRowMapper() {
        return new ClienteRowMapper();
    }
}


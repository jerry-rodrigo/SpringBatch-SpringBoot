package com.batch.SpringBatchApplication.ItemReader;

import com.batch.SpringBatchApplication.dto.ClienteDTO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ClienteExcelReader implements ItemReader<ClienteDTO> {

    private final Resource resource;

    public ClienteExcelReader(Resource resource) {
        this.resource = resource;
    }

    @Override
    public ClienteDTO read() throws Exception {
        // Implementa la lógica para leer un cliente desde el archivo Excel
        return null;
    }

    /*public PoiItemReader<ClienteDTO> excelReader() {
        PoiItemReader<ClienteDTO> reader = new PoiItemReader<>();
        reader.setResource(resource);
        reader.setRowMapper(clienteRowMapper());
        return reader;
    }

    private RowMapper<ClienteDTO> clienteRowMapper() {
        DefaultRowMapper<ClienteDTO> rowMapper = new DefaultRowMapper<>();
        rowMapper.setTargetType(ClienteDTO.class);
        return rowMapper;
    }

    @Bean
    @StepScope
    public ItemReader<ClienteDTO> clienteExcelReader() {
        PoiItemReader<ClienteDTO> reader = new PoiItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("clientes.xlsx"));
        reader.setRowMapper(clienteRowMapper());
        return reader;
    }

    @Bean
    public RowMapper<ClienteDTO> clienteRowMapper() {
        return new ClienteRowMapper();
    }*/
}


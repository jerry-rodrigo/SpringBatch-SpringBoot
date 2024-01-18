package com.batch.SpringBatchApplication.config;

import com.batch.SpringBatchApplication.dto.ClienteDTO;
import org.springframework.batch.extensions.excel.mapping.BeanWrapperRowMapper;

public class ClienteRowMapper extends BeanWrapperRowMapper<ClienteDTO> {
    public ClienteRowMapper() {
        setTargetType(ClienteDTO.class);
    }
}

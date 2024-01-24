package com.batch.SpringBatchApplication.config;

import com.batch.SpringBatchApplication.dto.ClienteDTO;
import org.springframework.batch.extensions.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;

public class ClienteRowMapper extends BeanWrapperRowMapper<ClienteDTO> {

    public ClienteRowMapper() {
        setTargetType(ClienteDTO.class);
    }

    @Override
    public ClienteDTO mapRow(RowSet rowSet) {
        ClienteDTO clienteDTO = new ClienteDTO();
        //clienteDTO.setId(Long.parseLong(rowSet.getCurrentRow()[0]));
        clienteDTO.setNombres(rowSet.getCurrentRow()[0]);
        clienteDTO.setApellidos(rowSet.getCurrentRow()[1]);
        clienteDTO.setDni(rowSet.getCurrentRow()[2]);
        clienteDTO.setTelefono(rowSet.getCurrentRow()[3]);
        clienteDTO.setEmail(rowSet.getCurrentRow()[4]);

        return clienteDTO;
    }
}
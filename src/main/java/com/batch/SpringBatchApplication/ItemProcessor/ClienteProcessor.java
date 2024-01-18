package com.batch.SpringBatchApplication.ItemProcessor;

import com.batch.SpringBatchApplication.dto.ClienteDTO;
import com.batch.SpringBatchApplication.model.Cliente;
import org.springframework.batch.item.ItemProcessor;

public class ClienteProcessor implements ItemProcessor<ClienteDTO, Cliente> {

    @Override
    public Cliente process(ClienteDTO clienteDTO) throws Exception {
        // Implementa la lógica para transformar ClienteDTO a Cliente
        return null;
    }
}

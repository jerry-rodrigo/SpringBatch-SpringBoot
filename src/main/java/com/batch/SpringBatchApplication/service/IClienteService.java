package com.batch.SpringBatchApplication.service;

import com.batch.SpringBatchApplication.dto.ClienteDTO;
import com.batch.SpringBatchApplication.model.Cliente;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IClienteService {

    ClienteDTO registrarCliente(ClienteDTO clienteDTO);
    List<ClienteDTO> obtenerTodosClientes();
    void cargarClientesDesdeExcel(MultipartFile file) throws IOException;

}

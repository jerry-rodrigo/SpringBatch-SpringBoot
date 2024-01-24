package com.batch.SpringBatchApplication.ItemProcessor;

import com.batch.SpringBatchApplication.dto.ClienteDTO;
import com.batch.SpringBatchApplication.model.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class ClienteProcessor implements ItemProcessor<ClienteDTO, Cliente> {

    @Override
    public Cliente process(ClienteDTO clienteDTO) {
        try {
            // Validar que el clienteDTO no sea nulo u otros requisitos de validación
            if (clienteDTO == null) {
                log.error("ClienteDTO es nulo. No se puede procesar.");
                throw new IllegalArgumentException("ClienteDTO es nulo. No se puede procesar.");
            }

            Cliente cliente = new Cliente();

            // Asignar los valores del ClienteDTO al modelo Cliente
            cliente.setId(clienteDTO.getId());
            cliente.setNombres(clienteDTO.getNombres());
            cliente.setApellidos(clienteDTO.getApellidos());
            cliente.setDni(clienteDTO.getDni());
            cliente.setTelefono(clienteDTO.getTelefono());
            cliente.setEmail(clienteDTO.getEmail());

            // Puedes realizar más transformaciones o validaciones según tus necesidades

            return cliente;
        } catch (Exception e) {
            log.error("Error al procesar ClienteDTO: {}", e.getMessage(), e);
            // Puedes lanzar una excepción personalizada si es necesario
            throw new RuntimeException("Error al procesar ClienteDTO", e);
        }
    }
}

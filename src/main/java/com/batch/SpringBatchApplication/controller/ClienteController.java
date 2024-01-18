package com.batch.SpringBatchApplication.controller;

import com.batch.SpringBatchApplication.dto.ClienteDTO;
import com.batch.SpringBatchApplication.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService iClienteService;

    @Autowired
    public ClienteController(IClienteService iClienteService) {
        this.iClienteService = iClienteService;
    }

    @PostMapping("/cargar")
    public ResponseEntity<String> cargarClientesDesdeExcel(@RequestParam("file") MultipartFile file) throws IOException {
        iClienteService.cargarClientesDesdeExcel(file);
        return new ResponseEntity<>("Carga de clientes desde Excel exitosa.", HttpStatus.OK);
    }

    @PostMapping("/registrarCliente")
    public ResponseEntity<ClienteDTO> registrarCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteRegistrado = iClienteService.registrarCliente(clienteDTO);
        return new ResponseEntity<>(clienteRegistrado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodosClientes() {
        List<ClienteDTO> clientes = iClienteService.obtenerTodosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

}


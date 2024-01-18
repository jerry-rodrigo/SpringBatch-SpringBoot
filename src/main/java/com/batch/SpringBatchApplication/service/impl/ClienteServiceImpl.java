package com.batch.SpringBatchApplication.service.impl;

import com.batch.SpringBatchApplication.dto.ClienteDTO;
import com.batch.SpringBatchApplication.model.Cliente;
import com.batch.SpringBatchApplication.repository.IClienteRepository;
import com.batch.SpringBatchApplication.service.IClienteService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements IClienteService {

    private final IClienteRepository iClienteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClienteServiceImpl(IClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.iClienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClienteDTO registrarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        Cliente clienteGuardado = iClienteRepository.save(cliente);
        return modelMapper.map(clienteGuardado, ClienteDTO.class);
    }

    @Override
    public List<ClienteDTO> obtenerTodosClientes() {
        List<Cliente> clientes = iClienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void cargarClientesDesdeExcel(MultipartFile file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        List<Cliente> clientes = new ArrayList<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Omitir la primera fila (encabezados)
            if (row.getRowNum() == 0) {
                continue;
            }

            Cliente cliente = new Cliente();
            cliente.setNombres(row.getCell(0).getStringCellValue());
            cliente.setApellidos(row.getCell(1).getStringCellValue());
            cliente.setDni(row.getCell(2).getStringCellValue());
            cliente.setTelefono(row.getCell(3).getStringCellValue());
            cliente.setEmail(row.getCell(4).getStringCellValue());

            clientes.add(cliente);
        }

        iClienteRepository.saveAll(clientes);
    }
}

package com.batch.SpringBatchApplication.service.impl;

import com.batch.SpringBatchApplication.dto.ProductoDTO;
import com.batch.SpringBatchApplication.dto.VentaDTO;
import com.batch.SpringBatchApplication.model.Venta;
import com.batch.SpringBatchApplication.repository.IVentaRepository;
import com.batch.SpringBatchApplication.service.IVentaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements IVentaService {

    private final IVentaRepository iVentaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VentaServiceImpl(IVentaRepository iVentaRepository, ModelMapper modelMapper) {
        this.iVentaRepository = iVentaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VentaDTO registrarVenta(VentaDTO ventaDTO) {
        Venta venta = modelMapper.map(ventaDTO, Venta.class);
        Venta ventaGuardada = iVentaRepository.save(venta);
        return modelMapper.map(ventaGuardada, VentaDTO.class);
    }

    @Override
    public List<VentaDTO> consultarVentasPorFecha(LocalDate fecha) {
        List<Venta> ventas = iVentaRepository.findByFecha(fecha);
        return ventas.stream()
                .map(venta -> modelMapper.map(venta, VentaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public VentaDTO consultarVentaPorId(Long id) {
        Venta venta = iVentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
        return modelMapper.map(venta, VentaDTO.class);
    }
}

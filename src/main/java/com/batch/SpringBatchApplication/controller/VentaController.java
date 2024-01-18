package com.batch.SpringBatchApplication.controller;

import com.batch.SpringBatchApplication.dto.VentaDTO;
import com.batch.SpringBatchApplication.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final IVentaService iVentaService;

    @Autowired
    public VentaController(IVentaService iVentaService) {
        this.iVentaService = iVentaService;
    }

    @PostMapping("/restrarVenta")
    public ResponseEntity<VentaDTO> registrarVenta(@RequestBody VentaDTO ventaDTO) {
        VentaDTO ventaRegistrada = iVentaService.registrarVenta(ventaDTO);
        return new ResponseEntity<>(ventaRegistrada, HttpStatus.CREATED);
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<VentaDTO>> consultarVentasPorFecha(@PathVariable String fecha) {
        LocalDate fechaFormateada = LocalDate.parse(fecha);
        List<VentaDTO> ventas = iVentaService.consultarVentasPorFecha(fechaFormateada);
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> consultarVentaPorId(@PathVariable Long id) {
        VentaDTO venta = iVentaService.consultarVentaPorId(id);
        return new ResponseEntity<>(venta, HttpStatus.OK);
    }

}


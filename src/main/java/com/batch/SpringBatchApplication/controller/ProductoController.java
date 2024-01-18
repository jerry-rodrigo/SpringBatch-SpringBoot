package com.batch.SpringBatchApplication.controller;

import com.batch.SpringBatchApplication.dto.ProductoDTO;
import com.batch.SpringBatchApplication.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService iProductoService;

    @Autowired
    public ProductoController(IProductoService iProductoService) {
        this.iProductoService = iProductoService;
    }

    @PostMapping("/cargar")
    public ResponseEntity<String> cargarProductosDesdeExcel(@RequestParam("file") MultipartFile file) throws IOException {
        iProductoService.cargarProductosDesdeExcel(file);
        return new ResponseEntity<>("Carga de productos desde Excel exitosa.", HttpStatus.OK);
    }

    @PostMapping("/registrarProducto")
    public ResponseEntity<ProductoDTO> registrarProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoRegistrado = iProductoService.registrarProducto(productoDTO);
        return new ResponseEntity<>(productoRegistrado, HttpStatus.CREATED);
    }

    @PutMapping("/actualizarProducto/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoActualizado = iProductoService.actualizarProducto(id, productoDTO);
        return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodosProductos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanoPagina) {
        List<ProductoDTO> productos = iProductoService.obtenerTodosProductosPaginados(pagina, tamanoPagina);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        iProductoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

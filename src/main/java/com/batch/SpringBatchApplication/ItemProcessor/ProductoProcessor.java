package com.batch.SpringBatchApplication.ItemProcessor;

import com.batch.SpringBatchApplication.dto.ProductoDTO;
import com.batch.SpringBatchApplication.model.Producto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class ProductoProcessor implements ItemProcessor<ProductoDTO, Producto> {

    @Override
    public Producto process(ProductoDTO productoDTO) {
        try {
            // Validar que el productoDTO no sea nulo u otros requisitos de validación
            if (productoDTO == null) {
                log.error("ProductoDTO es nulo. No se puede procesar.");
                throw new IllegalArgumentException("ProductoDTO es nulo. No se puede procesar.");
            }

            Producto producto = new Producto();

            // Asignar los valores del ProductoDTO al modelo Producto
            producto.setId(productoDTO.getId());
            producto.setNombre(productoDTO.getNombre());

            // Validar que el precio no sea nulo
            if (productoDTO.getPrecio() == null) {
                log.error("Precio en ProductoDTO es nulo. No se puede procesar.");
                throw new IllegalArgumentException("Precio en ProductoDTO es nulo. No se puede procesar.");
            }

            producto.setPrecio(productoDTO.getPrecio());

            return producto;
        } catch (Exception e) {
            log.error("Error al procesar ProductoDTO: {}", e.getMessage(), e);
            throw new RuntimeException("Error al procesar ProductoDTO", e);
        }
    }
}

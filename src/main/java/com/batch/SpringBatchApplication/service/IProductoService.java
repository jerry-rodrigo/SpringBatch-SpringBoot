package com.batch.SpringBatchApplication.service;

import com.batch.SpringBatchApplication.dto.ProductoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductoService {

    ProductoDTO registrarProducto(ProductoDTO productoDTO);
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO);
    List<ProductoDTO> obtenerTodosProductosPaginados(int pagina, int tamanoPagina);
    void eliminarProducto(Long id);
    void cargarProductosDesdeExcel(MultipartFile file) throws IOException;

}

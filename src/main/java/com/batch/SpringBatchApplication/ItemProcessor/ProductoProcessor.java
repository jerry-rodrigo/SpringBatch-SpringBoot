package com.batch.SpringBatchApplication.ItemProcessor;

import com.batch.SpringBatchApplication.dto.ProductoDTO;
import com.batch.SpringBatchApplication.model.Producto;
import org.springframework.batch.item.ItemProcessor;

public class ProductoProcessor implements ItemProcessor<ProductoDTO, Producto> {

    @Override
    public Producto process(ProductoDTO productoDTO) throws Exception {
        // Implementa la lógica para transformar ProductoDTO a Producto
        return null;
    }
}

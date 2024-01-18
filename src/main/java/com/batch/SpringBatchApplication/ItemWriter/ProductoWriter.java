package com.batch.SpringBatchApplication.ItemWriter;

import com.batch.SpringBatchApplication.model.Producto;
import com.batch.SpringBatchApplication.service.IProductoService;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ProductoWriter implements ItemWriter<Producto> {

    public ProductoWriter(IProductoService iProductoService) {
    }

    @Override
    public void write(List<? extends Producto> list) throws Exception {

    }
}

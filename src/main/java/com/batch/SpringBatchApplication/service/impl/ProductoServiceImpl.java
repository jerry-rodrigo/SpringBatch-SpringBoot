package com.batch.SpringBatchApplication.service.impl;

import com.batch.SpringBatchApplication.dto.ProductoDTO;
import com.batch.SpringBatchApplication.model.Producto;
import com.batch.SpringBatchApplication.repository.IProductoRepository;
import com.batch.SpringBatchApplication.service.IProductoService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements IProductoService {

    private final IProductoRepository iProductoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductoServiceImpl(IProductoRepository iProductoRepository, ModelMapper modelMapper) {
        this.iProductoRepository = iProductoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductoDTO registrarProducto(ProductoDTO productoDTO) {
        Producto producto = modelMapper.map(productoDTO, Producto.class);
        Producto productoGuardado = iProductoRepository.save(producto);
        return modelMapper.map(productoGuardado, ProductoDTO.class);
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
        Producto productoExistente = iProductoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado"));

        // Actualizar campos del productoExistente con los valores de productoDTO
        modelMapper.map(productoDTO, productoExistente);

        Producto productoActualizado = iProductoRepository.save(productoExistente);
        return modelMapper.map(productoActualizado, ProductoDTO.class);
    }

    @Override
    public List<ProductoDTO> obtenerTodosProductosPaginados(int pagina, int tamanoPagina) {
        Page<Producto> productosPaginados = iProductoRepository.findAll(PageRequest.of(pagina, tamanoPagina));
        return productosPaginados.getContent().stream()
                .map(producto -> modelMapper.map(producto, ProductoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarProducto(Long id) {
        iProductoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void cargarProductosDesdeExcel(MultipartFile file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        List<Producto> productos = new ArrayList<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Omitir la primera fila (encabezados)
            if (row.getRowNum() == 0) {
                continue;
            }

            Producto producto = new Producto();
            producto.setNombre(row.getCell(0).getStringCellValue());
            producto.setPrecio((float) row.getCell(1).getNumericCellValue());

            productos.add(producto);
        }

        iProductoRepository.saveAll(productos);
    }
}

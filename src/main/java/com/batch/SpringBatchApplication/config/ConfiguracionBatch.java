package com.batch.SpringBatchApplication.config;

import com.batch.SpringBatchApplication.ItemProcessor.ClienteProcessor;
import com.batch.SpringBatchApplication.ItemProcessor.ProductoProcessor;
import com.batch.SpringBatchApplication.ItemWriter.ClienteWriter;
import com.batch.SpringBatchApplication.ItemWriter.ProductoWriter;
import com.batch.SpringBatchApplication.dto.ClienteDTO;
import com.batch.SpringBatchApplication.dto.ProductoDTO;
import com.batch.SpringBatchApplication.model.Cliente;
import com.batch.SpringBatchApplication.model.Producto;
import com.batch.SpringBatchApplication.service.IClienteService;
import com.batch.SpringBatchApplication.service.IProductoService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class ConfiguracionBatch {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private IClienteService iClienteService;

    @Autowired
    private IProductoService iProductoService;

    @Bean
    public Job cargarClientesJob(Step cargarClientesStep) {
        return jobBuilderFactory.get("cargarClientesJob")
                .incrementer(new RunIdIncrementer())
                .start(cargarClientesStep)
                .build();
    }

    @Bean
    public Step cargarClientesStep(ItemReader<ClienteDTO> clienteExcelReader,
                                   ItemProcessor<ClienteDTO, Cliente> clienteProcessor,
                                   ItemWriter<Cliente> clienteWriter) {
        return stepBuilderFactory.get("cargarClientesStep")
                .<ClienteDTO, Cliente>chunk(10)
                .reader(clienteExcelReader)
                .processor(clienteProcessor)
                .writer(clienteWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<ClienteDTO> clienteExcelReader() {
        PoiItemReader<ClienteDTO> reader = new PoiItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("clientes.xlsx"));
        reader.setRowMapper(clienteRowMapper());
        return reader;
    }

    @Bean
    public RowMapper<ClienteDTO> clienteRowMapper() {
        return new ClienteRowMapper();
    }

    @Bean
    public ItemProcessor<ClienteDTO, Cliente> clienteProcessor() {
        return new ClienteProcessor();
    }

    @Bean
    public ItemWriter<Cliente> clienteWriter() {
        return new ClienteWriter(iClienteService);
    }

    @Bean
    public Job cargarProductosJob(Step cargarProductosStep) {
        return jobBuilderFactory.get("cargarProductosJob")
                .incrementer(new RunIdIncrementer())
                .start(cargarProductosStep)
                .build();
    }

    @Bean
    public Step cargarProductosStep(ItemReader<ProductoDTO> productoExcelReader,
                                    ItemProcessor<ProductoDTO, Producto> productoProcessor,
                                    ItemWriter<Producto> productoWriter) {
        return stepBuilderFactory.get("cargarProductosStep")
                .<ProductoDTO, Producto>chunk(10)
                .reader(productoExcelReader)
                .processor(productoProcessor)
                .writer(productoWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<ProductoDTO> productoExcelReader() {
        PoiItemReader<ProductoDTO> reader = new PoiItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("productos.xlsx"));
        reader.setRowMapper(productoRowMapper());
        return reader;
    }

    @Bean
    public RowMapper<ProductoDTO> productoRowMapper() {
        return new ProductoRowMapper();
    }

    @Bean
    public ItemProcessor<ProductoDTO, Producto> productoProcessor() {
        return new ProductoProcessor();
    }

    @Bean
    public ItemWriter<Producto> productoWriter() {
        return new ProductoWriter(iProductoService);
    }

}

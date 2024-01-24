package com.batch.SpringBatchApplication.config;

import com.batch.SpringBatchApplication.ItemProcessor.ClienteProcessor;
import com.batch.SpringBatchApplication.ItemProcessor.ProductoProcessor;
import com.batch.SpringBatchApplication.dto.ClienteDTO;
import com.batch.SpringBatchApplication.dto.ProductoDTO;
import com.batch.SpringBatchApplication.model.Cliente;
import com.batch.SpringBatchApplication.model.Producto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
public class ConfigurationBatch {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public ItemProcessor<ClienteDTO, Cliente> clienteProcessor() {
        return new ClienteProcessor();
    }

    @Bean
    public ItemProcessor<ProductoDTO, Producto> productoProcessor() {
        return new ProductoProcessor();
    }

    @Bean
    public ItemReader<ClienteDTO> clienteExcelReader() {
        PoiItemReader<ClienteDTO> reader = new PoiItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("clientes.xlsx"));
        reader.setRowMapper(new ClienteRowMapper());
        return reader;
    }

    @Bean
    public ItemReader<ProductoDTO> productoExcelReader() {
        PoiItemReader<ProductoDTO> reader = new PoiItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("productos.xlsx"));
        reader.setRowMapper(new ProductoRowMapper());
        return reader;
    }

    @Bean
    public JpaItemWriter<Cliente> clienteJpaItemWriter() {
        JpaItemWriter<Cliente> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public JpaItemWriter<Producto> productoJpaItemWriter() {
        JpaItemWriter<Producto> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public Step cargarClientesStep(ItemReader<ClienteDTO> clienteExcelReader,
                                   ItemProcessor<ClienteDTO, Cliente> clienteProcessor,
                                   ItemWriter<Cliente> clienteJpaItemWriter) {
        return stepBuilderFactory.get("cargarClientesStep")
                .<ClienteDTO, Cliente>chunk(100)
                .reader(clienteExcelReader)
                .processor(clienteProcessor)
                .writer(clienteJpaItemWriter)
                .build();
    }

    @Bean
    public Step cargarProductosStep(ItemReader<ProductoDTO> productoExcelReader,
                                    ItemProcessor<ProductoDTO, Producto> productoProcessor,
                                    ItemWriter<Producto> productoJpaItemWriter) {
        return stepBuilderFactory.get("cargarProductosStep")
                .<ProductoDTO, Producto>chunk(100)
                .reader(productoExcelReader)
                .processor(productoProcessor)
                .writer(productoJpaItemWriter)
                .build();
    }

    @Bean
    public Job cargarClientesYProductosJob(Step cargarClientesStep, Step cargarProductosStep) {
        return jobBuilderFactory.get("cargarClientesYProductosJob")
                .incrementer(new RunIdIncrementer())
                .start(cargarClientesStep)
                .next(cargarProductosStep)
                .build();
    }
}

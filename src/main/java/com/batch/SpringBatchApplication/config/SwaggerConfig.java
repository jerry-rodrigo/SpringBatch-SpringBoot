package com.batch.SpringBatchApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
//@EnableSwagger2
public class SwaggerConfig {

    /*@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.batch.SpringBatchApplication.controller"))
                //.apis(RequestHandlerSelectors.basePackage("Pcom.batch.SpringBatchApplication.controller.ProductoController"))
                //.apis(RequestHandlerSelectors.basePackage("com.batch.SpringBatchApplication.controller.VentaController"))
                .paths(PathSelectors.any())
                .build();
    }*/
}

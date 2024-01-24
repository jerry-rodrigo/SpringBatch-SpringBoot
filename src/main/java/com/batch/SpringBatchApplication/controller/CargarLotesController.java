package com.batch.SpringBatchApplication.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cargar-lotes")
public class CargarLotesController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job cargarClientesYProductosJob;

    @GetMapping("/clientes-y-productos")
    public ResponseEntity<String> cargarClientesYProductos() throws JobExecutionException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        JobExecution jobExecution = jobLauncher.run(cargarClientesYProductosJob, jobParameters);
        return ResponseEntity.ok("Job ejecutado con ID: " + jobExecution.getId());
    }
}

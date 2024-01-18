package com.batch.SpringBatchApplication.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {

    private final Job cargarClientesJob;
    private final Job cargarProductosJob;
    private final JobLauncher jobLauncher;

    public JobScheduler(Job cargarClientesJob, Job cargarProductosJob, JobLauncher jobLauncher) {
        this.cargarClientesJob = cargarClientesJob;
        this.cargarProductosJob = cargarProductosJob;
        this.jobLauncher = jobLauncher;
    }

    @Scheduled(cron = "0 58 23 * * ?") // Ejecuta a la 1 am todos los días
    public void ejecutarCargarClientesJob() throws JobExecutionException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        jobLauncher.run(cargarClientesJob, jobParameters);
    }

    @Scheduled(cron = "0 58 23 * * ?") // Ejecuta a la 1 am todos los días
    public void ejecutarCargarProductosJob() throws JobExecutionException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        jobLauncher.run(cargarProductosJob, jobParameters);
    }
}


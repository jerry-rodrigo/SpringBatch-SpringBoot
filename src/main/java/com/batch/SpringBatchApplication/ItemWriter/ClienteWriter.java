package com.batch.SpringBatchApplication.ItemWriter;

import com.batch.SpringBatchApplication.model.Cliente;
import com.batch.SpringBatchApplication.service.IClienteService;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ClienteWriter implements ItemWriter<Cliente> {


    public ClienteWriter(IClienteService iClienteService) {
    }

    @Override
    public void write(List<? extends Cliente> list) throws Exception {

    }
}


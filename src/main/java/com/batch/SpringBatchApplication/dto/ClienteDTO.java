package com.batch.SpringBatchApplication.dto;

import lombok.Data;

@Data
public class ClienteDTO {

    private Long id;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String email;
}

package com.ibermatica.saga.model;


import lombok.Data;

@Data
public class PacienteDTO {

    private Integer id;
    private String nombre;
    private String cic;
    private boolean isEscasamenteIdentificado;
}

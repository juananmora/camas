package com.ibermatica.saga.camas;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cama {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    private EstadoOcupacion ocupacion;

    public enum EstadoOcupacion {
        DISPONIBLE, OCUPADO, RESERVADO
    }
}

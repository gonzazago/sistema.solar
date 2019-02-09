package com.examen.ingreso.meli.sistema.solar.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "WEATHER")
public class Weather implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long day;
    @Column(name ="WEATHER_TYPE")
    private String weatherType;
    @Column
    private Double amountRainy;
}

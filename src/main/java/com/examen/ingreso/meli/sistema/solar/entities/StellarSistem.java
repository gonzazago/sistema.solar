package com.examen.ingreso.meli.sistema.solar.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;


@Data
@AllArgsConstructor
public class StellarSistem {

    @NonNull
    List<Planet> planets;


}

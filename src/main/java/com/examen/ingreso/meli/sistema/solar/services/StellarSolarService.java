package com.examen.ingreso.meli.sistema.solar.services;

import com.examen.ingreso.meli.sistema.solar.entities.StellarSistem;
import com.examen.ingreso.meli.sistema.solar.resources.dto.WeatherInfoDTO;

public interface StellarSolarService {

    WeatherInfoDTO simulateAfterDays(StellarSistem stellarSistem, Integer days);
}

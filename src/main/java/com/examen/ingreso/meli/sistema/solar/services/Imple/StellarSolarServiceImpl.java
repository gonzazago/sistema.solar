package com.examen.ingreso.meli.sistema.solar.services.Imple;

import com.examen.ingreso.meli.sistema.solar.entities.StellarSistem;
import com.examen.ingreso.meli.sistema.solar.resources.dto.WeatherInfoDTO;
import com.examen.ingreso.meli.sistema.solar.services.StellarSolarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StellarSolarServiceImpl implements StellarSolarService {
    @Autowired
    WeatherServiceImple weatherServiceImple;

     @Override
    public WeatherInfoDTO simulateAfterDays(StellarSistem stellarSistem, Integer days) {

        return weatherServiceImple.simulateDays(stellarSistem.getPlanets(),days);
    }
}

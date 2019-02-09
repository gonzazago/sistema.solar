package com.examen.ingreso.meli.sistema.solar.services;

import com.examen.ingreso.meli.sistema.solar.entities.Planet;
import com.examen.ingreso.meli.sistema.solar.entities.Weather;
import com.examen.ingreso.meli.sistema.solar.resources.dto.WeatherInfoDTO;

import java.util.List;
import java.util.Optional;

public interface WeatherService {

    WeatherInfoDTO simulateDays(List<Planet> planetList, Integer days);
    Optional<Weather> getWeatherForDay(Long day);
}

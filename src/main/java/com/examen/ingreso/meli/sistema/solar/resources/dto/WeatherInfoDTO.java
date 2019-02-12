package com.examen.ingreso.meli.sistema.solar.resources.dto;

import lombok.Data;

@Data
public class WeatherInfoDTO {

    private Long countPeriodsRainy;
    private Long countDroughtPeriods;
    private Long countSunnPeriods;
    private Integer dayMaxOfRainy;
}

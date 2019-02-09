package com.examen.ingreso.meli.sistema.solar.resources;


import com.examen.ingreso.meli.sistema.solar.entities.Planet;
import com.examen.ingreso.meli.sistema.solar.factory.PlanetFactory;
import com.examen.ingreso.meli.sistema.solar.repository.WeatherRepository;
import com.examen.ingreso.meli.sistema.solar.resources.ExceptionRest.ApiRestException;
import com.examen.ingreso.meli.sistema.solar.resources.ExceptionRest.DayNotFoundException;
import com.examen.ingreso.meli.sistema.solar.resources.dto.WeatherDayDTO;
import com.examen.ingreso.meli.sistema.solar.resources.dto.WeatherInfoDTO;
import com.examen.ingreso.meli.sistema.solar.services.WeatherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class WeatherRest {

 @Autowired
    WeatherService weatherService;

    Planet ferengui = PlanetFactory.createPlanet("Ferengui",2.00,true,1000.00);
    Planet  vulcano = PlanetFactory.createPlanet("Vulcano",2.00,true,1000.00);
    Planet betasoide = PlanetFactory.createPlanet("Betasoide",-2.00,false,1000.00);

    List<Planet> planets = new ArrayList<>(Arrays.asList(ferengui,vulcano,betasoide));


    @ApiOperation(notes ="Return the Weather Info for day specific ",
            value = "Consult the Weather info for day specific",
            nickname = "getWeatherInfoForDay")
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "Succes", response = WeatherDayDTO.class),
            @ApiResponse(code = 400 ,message = "Invalid days for simulate", response = DayNotFoundException.class)
    })
 @GetMapping("/weathers/{day}")
 public ResponseEntity<WeatherDayDTO> getWeatherInfoForDay(@ApiParam(value = "Number of days to be calculated", allowableValues = "range[1,infinity]", required = true)
         @PathVariable("day")Long day) throws DayNotFoundException {

     final WeatherDayDTO weatherDayDTO = null;

     if(day < 1){
         throw new IllegalArgumentException("the value entered must be greater than 1 ");
     }

     return weatherService.getWeatherForDay(day).map(
             weather -> {
                 weatherDayDTO.setDay(weather.getDay());
                 weatherDayDTO.setDesc(weather.getWeatherType());
                 return ResponseEntity.ok(weatherDayDTO);
             }
     ).orElseThrow(() -> new DayNotFoundException());
 }


}

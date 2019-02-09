package com.examen.ingreso.meli.sistema.solar.resources;

import com.examen.ingreso.meli.sistema.solar.entities.Planet;
import com.examen.ingreso.meli.sistema.solar.entities.StellarSistem;
import com.examen.ingreso.meli.sistema.solar.factory.PlanetFactory;
import com.examen.ingreso.meli.sistema.solar.resources.ExceptionRest.ApiRestException;
import com.examen.ingreso.meli.sistema.solar.resources.dto.WeatherInfoDTO;
import com.examen.ingreso.meli.sistema.solar.services.StellarSolarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StellarSistemRest {


    @Autowired
    StellarSolarService stellarSolarService;

    Planet ferengui = PlanetFactory.createPlanet("Ferengui",1.00,true,500.00);
    Planet  vulcano = PlanetFactory.createPlanet("Vulcano",5.00,true,1000.00);
    Planet betasoide = PlanetFactory.createPlanet("Betasoide",3.00,false,2000.00);

    List<Planet> planets = new ArrayList<>(Arrays.asList(ferengui,vulcano,betasoide));
    @ApiOperation(notes ="Return the simulation WeatherInfo for days > 0 ",
            value = "Simulate Weather dayas",
            nickname = "simulateForeCast")
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "Succes", response = WeatherInfoDTO.class),
            @ApiResponse(code = 400 ,message = "Invalid days for simulate", response = ApiRestException.class)
    })
    @GetMapping("/stellar-sollar/{days}")
    public ResponseEntity<WeatherInfoDTO> simulateDays(@ApiParam(value = "Number of days to be calculated", allowableValues = "range[1,infinity]", required = true)
                                                           @PathVariable("days") Integer day) throws Exception{
        if(day < 1  || day > 3600){
            throw new IllegalArgumentException("La cantidad de dias debe ser mayor a 1 y menor a 10 años");
        }
        StellarSistem stellarSistem = new StellarSistem(planets);
        WeatherInfoDTO infoDTO=stellarSolarService.simulateAfterDays(stellarSistem,day);
        return  ResponseEntity.ok(infoDTO);
    }



    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    @ResponseBody
    public ResponseEntity<?> handleBadRequests(final Exception ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiRestException error = ApiRestException.create(status, ex.getMessage());
        return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
    }
}

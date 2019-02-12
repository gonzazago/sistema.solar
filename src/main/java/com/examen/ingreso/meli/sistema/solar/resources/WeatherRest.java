package com.examen.ingreso.meli.sistema.solar.resources;

import com.examen.ingreso.meli.sistema.solar.resources.ExceptionRest.DayNotFoundException;
import com.examen.ingreso.meli.sistema.solar.resources.dto.WeatherDayDTO;
import com.examen.ingreso.meli.sistema.solar.services.WeatherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class WeatherRest {
    private final Logger log = LoggerFactory.getLogger(WeatherRest.class);
 @Autowired
    WeatherService weatherService;

    @ApiOperation(notes ="Return the Weather Info for day specific ",
            value = "Consult the Weather info for day specific",
            nickname = "getWeatherInfoForDay")
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "Succes", response = WeatherDayDTO.class),
            @ApiResponse(code = 400 ,message = "Invalid days for simulate", response = DayNotFoundException.class)
    })
 @GetMapping("/weathers/{day}")
 public ResponseEntity<WeatherDayDTO> getWeatherInfoForDay(@ApiParam(value = "Number of days to be calculated", allowableValues = "range[1,infinity]", required = true)
         @PathVariable("day")Long day) throws Exception {
        log.info("Init method getWeathrtInfoForDay");
     if(day < 1){
         throw new IllegalArgumentException("the value entered must be greater than 1 ");
     }
     try {
         return  weatherService.getWeatherForDay(day).map(
                 weather -> {
                     WeatherDayDTO w = new WeatherDayDTO();
                     w.setDay(weather.getDay());
                     w.setDesc(weather.getWeatherType());
                     return ResponseEntity.ok(w);
                 }
         ).orElseThrow(() -> new DayNotFoundException());
     }catch (Exception e){
         log.error("Error ocurred in method getWeatherInfoForDay",e);
         throw  new Exception();
     }


 }


}

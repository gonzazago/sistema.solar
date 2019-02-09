package com.examen.ingreso.meli.sistema.solar.services.Imple;

import com.examen.ingreso.meli.sistema.solar.entities.*;
import com.examen.ingreso.meli.sistema.solar.repository.WeatherRepository;
import com.examen.ingreso.meli.sistema.solar.resources.dto.WeatherInfoDTO;
import com.examen.ingreso.meli.sistema.solar.services.PlanetService;
import com.examen.ingreso.meli.sistema.solar.services.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImple implements WeatherService {

    private final static String DROUGHT = "DROUGHT";
    private final static String SUN = "SUN";
    private final static String RAIN = "RAIN";

    private final static String UNKNOWN ="UNKNOWN";

    @Autowired
    PlanetService planetService;

    @Autowired
    WeatherRepository weatherRepository;



    @Override
    public WeatherInfoDTO simulateDays(List<Planet> planets, Integer days) {
        Integer dayMax = null;
         if(planets.size() == 3){

             for (Integer i = 0; i < days; i++) {

                 String weatherType = getWeatherType(planets,i);
                 Weather weather = new Weather();

                 switch (weatherType){

                     case SUN:
                         weather.setAmountRainy(0.00);
                         weather.setWeatherType(WeatherTypes.SUNNY.getWeatherType());
                         weatherRepository.save(weather);
                         break;
                     case DROUGHT:
                         weather.setAmountRainy(0.00);
                         weather.setWeatherType(WeatherTypes.DROUGHT.getWeatherType());
                         weatherRepository.save(weather);
                         break;
                     case RAIN:
                         weather.setAmountRainy(planetService.calculatePeriodRainy(planets.get(0),planets.get(1),planets.get(2)));
                         weather.setWeatherType(WeatherTypes.RAIN.getWeatherType());
                         weatherRepository.save(weather);
                         break;
                     default:
                         weather.setAmountRainy(0.00);
                         weather.setWeatherType(WeatherTypes.UNKNOWN.getWeatherType());
                         weatherRepository.save(weather);
                 }
             }
        }else {
             throw  new IllegalArgumentException("La cantidad de planetas enviados es incorrecta");
         }
         List<Weather> weathers = getWeatherInfo();

         weathers.sort((w1,w2) -> w1.getWeatherType().compareTo(w2.getWeatherType()));

        List< Weather> maxPeriodRain = weathers.stream()
                 .filter(x -> x.getWeatherType().equals(WeatherTypes.RAIN.getWeatherType())).collect(Collectors.toList());
        
        if(!maxPeriodRain.isEmpty() && maxPeriodRain !=null ){
             dayMax = maxPeriodRain.stream()
                    .max(Comparator.comparing(Weather::getAmountRainy))
                    .get()
                    .getDay().intValue();
        }


        Map<String,Long> groupByWeather = weathers
                .stream()
                .collect(
                        Collectors.groupingBy(Weather::getWeatherType, Collectors.counting())
                );
        System.out.println(groupByWeather);
        WeatherInfoDTO weatherInfoDTO = new WeatherInfoDTO();
        Long periodsSun = Long.valueOf(0) ;
        Long periodsDrought = Long.valueOf(0);
        Long periodRainy = Long.valueOf(0) ;

        if(groupByWeather.containsKey(WeatherTypes.RAIN.getWeatherType())){
            periodRainy = groupByWeather.get(WeatherTypes.RAIN.getWeatherType());
        }

        if(groupByWeather.containsKey(WeatherTypes.DROUGHT.getWeatherType())){
            periodsDrought = groupByWeather.get(WeatherTypes.DROUGHT.getWeatherType());
        }

        if(groupByWeather.containsKey(WeatherTypes.SUNNY.getWeatherType())){
            periodsSun = groupByWeather.get(WeatherTypes.SUNNY.getWeatherType());
        }
        weatherInfoDTO.setCountDroughtPeriods(periodsDrought);
        weatherInfoDTO.setCountSunnPeriods(periodsSun);
        weatherInfoDTO.setMaxPeriodRain(periodRainy);
        weatherInfoDTO.setDayMaxOfRainy(dayMax != null ? dayMax : 0);

        return weatherInfoDTO;
    }

    @Override
    public Optional<Weather> getWeatherForDay(Long day) {
        return weatherRepository.findById(day);
    }

    private String getWeatherType(List<Planet> planets,Integer day){



        for(Planet p : planets){
            p.simulateAfterDay(day);
        }
        Integer i = 0;
        if(i + 3 <= planets.size() &&
                planetService.planetsIsAling(planets.get(i),planets.get(i+1),planets.get(i+2))){
            if(!planetService.plantesAlingWithSun(planets.get(i),planets.get(i+1),planets.get(i+2))){
                return SUN;
            }else{
                return DROUGHT;
            }
        }else{
            if(i + 2 < planets.size() &&
                    planetService.isSunIntoTriangle(planets.get(i),planets.get(i+1),planets.get(i+2))){
                return  RAIN;
            }else{
                return UNKNOWN;
            }
        }
    }

    public  List<Weather> getWeatherInfo(){
        return weatherRepository.findAll();
    }
}

package com.examen.ingreso.meli.sistema.solar;

import com.examen.ingreso.meli.sistema.solar.entities.Planet;
import com.examen.ingreso.meli.sistema.solar.factory.PlanetFactory;
import com.examen.ingreso.meli.sistema.solar.services.Imple.WeatherServiceImple;
import com.examen.ingreso.meli.sistema.solar.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);}


}


package com.examen.ingreso.meli.sistema.solar.repository;

import com.examen.ingreso.meli.sistema.solar.entities.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface WeatherRepository extends JpaRepository<Weather,Long> {


}

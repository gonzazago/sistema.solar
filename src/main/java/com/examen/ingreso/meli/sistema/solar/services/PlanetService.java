package com.examen.ingreso.meli.sistema.solar.services;

import com.examen.ingreso.meli.sistema.solar.entities.Planet;

public interface PlanetService {

    boolean  planetsIsAling(Planet p1,Planet p2, Planet p3);
    boolean  isSunIntoTriangle(Planet p1,Planet p2, Planet p3);
    boolean plantesAlingWithSun(Planet p1,Planet p2, Planet p3);
    Double calculatePeriodRainy(Planet p1, Planet p2,Planet p3);
}

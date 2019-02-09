package com.examen.ingreso.meli.sistema.solar.factory;

import com.examen.ingreso.meli.sistema.solar.entities.Planet;

import java.awt.geom.Point2D;

public class PlanetFactory {


    public static Planet createPlanet(String name,Double velocity,boolean clockwise,Double distance){
        return new Planet(name,velocity,clockwise,distance);
    }
}

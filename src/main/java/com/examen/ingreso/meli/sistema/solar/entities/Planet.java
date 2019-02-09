package com.examen.ingreso.meli.sistema.solar.entities;

import com.examen.ingreso.meli.sistema.solar.utils.CalcUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.geom.Point2D;

@Data
@AllArgsConstructor
public  class Planet {

    private String name;
    private Double velocity;
    private boolean clockWise;
    private Double distance;
    private Point2D position;//Calculado
    private Double angule;//Calculado

    public Planet(String name, Double velocity, boolean clockwise, Double distance) {
        this.name = name;
        this.velocity = velocity;
        this.clockWise = clockwise;
        this.distance =distance;
        this.angule = 90.00;
    }

/*
    public void setPosition(){
        this.position = CalcUtils.transformFromPolarToCartesian(
                new PolarCordenate(this.distance,Math.toRadians(this.getAngule())));
    }

    public void  setAngule(Integer days){
        this.angule = CalcUtils.getAngule(this,days);
    }*/

    public void simulateAfterDay(Integer days){
        this.angule = CalcUtils.getAngule(this,days);
        this.position = CalcUtils.transformFromPolarToCartesian(new PolarCordenate(
                this.distance,Math.toRadians(this.getAngule())));
    }




}

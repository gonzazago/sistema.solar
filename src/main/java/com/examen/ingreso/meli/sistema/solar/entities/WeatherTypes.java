package com.examen.ingreso.meli.sistema.solar.entities;



public enum WeatherTypes {

    RAIN("The Weather is Rain"),
    SUNNY("The Wheater is Sunny"),
    DROUGHT("The Weather is drought"),
    UNKNOWN("Unknown");

    private final String weatherType;

    WeatherTypes(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getWeatherType() {
        return weatherType;
    }
}

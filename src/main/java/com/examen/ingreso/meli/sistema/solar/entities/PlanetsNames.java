package com.examen.ingreso.meli.sistema.solar.entities;

public enum PlanetsNames {
    VULCANO("Vulcano"),
    FERENGUI("Ferengi"),
    BETASOIDE("Betasoide") ;

    private final String name;

    PlanetsNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

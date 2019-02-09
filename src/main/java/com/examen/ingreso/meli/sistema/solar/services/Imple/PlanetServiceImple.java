package com.examen.ingreso.meli.sistema.solar.services.Imple;

import com.examen.ingreso.meli.sistema.solar.entities.Planet;
import com.examen.ingreso.meli.sistema.solar.entities.PolarCordenate;
import com.examen.ingreso.meli.sistema.solar.services.PlanetService;
import com.examen.ingreso.meli.sistema.solar.utils.CalcUtils;
import org.springframework.stereotype.Service;


@Service
public class PlanetServiceImple implements PlanetService {

    @Override
    public boolean planetsIsAling(Planet p1, Planet p2, Planet p3) {
        return CalcUtils.isAlingToPlanets(p1.getPosition(),p2.getPosition(),p3.getPosition());
    }

    @Override
    public boolean isSunIntoTriangle(Planet p1, Planet p2, Planet p3) {
        if(!CalcUtils.isAlingToPlanets(p1.getPosition(),p2.getPosition(),p3.getPosition())){

            return CalcUtils.isSunIntoTriangule(p1.getPosition(),p2.getPosition(),p3.getPosition());
        }
        return  false;
    }

    @Override
    public boolean plantesAlingWithSun(Planet p1, Planet p2, Planet p3) {
        if(CalcUtils.isAlingToPlanets(p1.getPosition(),p2.getPosition(),p3.getPosition())){

            PolarCordenate pc1 = new PolarCordenate(p1.getDistance(),Math.toRadians(p1.getAngule()));
            PolarCordenate pc2 = new PolarCordenate(p2.getDistance(),Math.toRadians(p2.getAngule()));
            PolarCordenate pc3 = new PolarCordenate(p3.getDistance(),Math.toRadians(p3.getAngule()));
            PolarCordenate sunP = new PolarCordenate(0.00,0.00);

            return  CalcUtils.isAlingPlanetsAndSun(pc2,pc1)
            &&  CalcUtils.isAlingPlanetsAndSun(pc3,pc1)
            && CalcUtils.isAlingPlanetsAndSun(pc1,sunP);
        }
        return false;
    }

    @Override
    public Double calculatePeriodRainy(Planet p1, Planet p2, Planet p3) {
        return CalcUtils.calculatePerimeter(p1.getPosition(),p2.getPosition(),p3.getPosition());
    }

}

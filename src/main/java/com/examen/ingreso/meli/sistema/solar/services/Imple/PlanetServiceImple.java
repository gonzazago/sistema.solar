package com.examen.ingreso.meli.sistema.solar.services.Imple;

import com.examen.ingreso.meli.sistema.solar.entities.Planet;
import com.examen.ingreso.meli.sistema.solar.services.PlanetService;
import com.examen.ingreso.meli.sistema.solar.utils.CalcUtils;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;


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

            Point2D pc1 = p1.getPosition();
            Point2D pc2 = p2.getPosition();
            Point2D pc3 = p3.getPosition();

            return  CalcUtils.isAlingPlanetsAndSun(pc1,pc2,pc3);

        }
        return false;
    }

    @Override
    public Double calculatePeriodRainy(Planet p1, Planet p2, Planet p3) {
        return CalcUtils.calculatePerimeter(p1.getPosition(),p2.getPosition(),p3.getPosition());
    }

}

package com.examen.ingreso.meli.sistema.solar.services.Imple;

import com.examen.ingreso.meli.sistema.solar.entities.Planet;
import com.examen.ingreso.meli.sistema.solar.services.PlanetService;
import com.examen.ingreso.meli.sistema.solar.utils.CalcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;


@Service
public class PlanetServiceImple implements PlanetService {
    private final Logger log = LoggerFactory.getLogger(PlanetServiceImple.class);
    @Override
    public boolean planetsIsAling(Planet p1, Planet p2, Planet p3) {
        try {
            log.info("Init method planetIsAling");
            return CalcUtils.isAlingToPlanets(p1.getPosition(),p2.getPosition(),p3.getPosition());
        }catch (Exception e){
            log.error("Error ocurrer in method planetsIsAling" ,e);
            return false;
        }

    }

    @Override
    public boolean isSunIntoTriangle(Planet p1, Planet p2, Planet p3) {
        log.info("init method isSunIntoToTriangule");
        try {
            if(!CalcUtils.isAlingToPlanets(p1.getPosition(),p2.getPosition(),p3.getPosition())){

                return CalcUtils.isSunIntoTriangule(p1.getPosition(),p2.getPosition(),p3.getPosition());
            }

        }catch (Exception e){
            log.error("Error ocurred in method", e);
        }
        return  false;
    }

    @Override
    public boolean plantesAlingWithSun(Planet p1, Planet p2, Planet p3) {
        try {
            if(CalcUtils.isAlingToPlanets(p1.getPosition(),p2.getPosition(),p3.getPosition())){

                Point2D pc1 = p1.getPosition();
                Point2D pc2 = p2.getPosition();
                Point2D pc3 = p3.getPosition();

                return  CalcUtils.isAlingPlanetsAndSun(pc1,pc2,pc3);

            }
        }catch (Exception e){
            log.error("Error ocurred in isAlintPlanetAndSun", e);
        }

        return false;
    }

    @Override
    public Double calculatePeriodRainy(Planet p1, Planet p2, Planet p3) {
        return CalcUtils.calculatePerimeter(p1.getPosition(),p2.getPosition(),p3.getPosition());
    }

}

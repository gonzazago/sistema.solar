package com.examen.ingreso.meli.sistema.solar.utils;

import com.examen.ingreso.meli.sistema.solar.entities.Planet;
import com.examen.ingreso.meli.sistema.solar.entities.PolarCordenate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.geom.Point2D;
import java.util.function.Function;


public class CalcUtils {

    private final static Logger log = LoggerFactory.getLogger(CalcUtils.class);

    public static Point2D transformFromPolarToCartesian(PolarCordenate p1){
        Double angularRadians = p1.getAnguleInRadian();
        Double x = Math.cos(angularRadians) * p1.getRadio();
        Double y = Math.sin(angularRadians) * p1.getRadio();
        return new Point2D.Double(x,y);
    }

    /**Devuelve el angulo en grados*/
    public static Double getAngule(Planet p, Integer day){

        return Math.abs(p.getVelocity())*day;
    }


    /**
     * Metodo que recibe 3 puntos cartesianos y verifica que esten
     * alineados entre si*/
    public static boolean isAlingToPlanets(Point2D p1, Point2D p2, Point2D p3){

        return (p1.getX() == p2.getX() && p2.getX() == p3.getX()
                    || p1.getY() == p2.getY() && p2.getY() == p3.getY());
    }

    /**
     * Verifica que dados 3 puntos estos pertenezcan a la misma recta
     * @Input PolarCordenate p1 ,PolarCorednate p2
     * @return boolean*/
    public  static  boolean isAlingPlanetsAndSun(Point2D p1, Point2D p2, Point2D p3){

        if( (p1.getX() == p2.getX() && p2.getX() == p3.getX() && p1.getX() != 0.0)
                || (p1.getY() == p2.getY() && p2.getY() == p3.getY() && p1.getY() != 0.0)){
            return true;
        }
        try{
            Double delX = p2.getX() - p1.getX();
            Double delY = p2.getY() - p1.getY();

            Double slope = delY / delX;

            Double independent = p2.getY() - (slope * p1.getY());
            Function<Double,Double> rec = x -> (slope * x) + independent;

            return  rec.apply(p3.getX()) == p3.getY() && rec.apply(0.0) != 0.0;
        }catch (Exception e){
            log.error("Error ocurred in method isAlingPlanetsAndSun" , e);
            return false;
        }

    }


    /**Metodo que devuelve si el sol se encuentra por dentro o
     * por fuera del triangulo formado por los 3 planetas
     * se toma como referencia el sol en el P(0,0)
     * Se utiliza la siguiente formula
     * (A1.x - A3.x) * (A2.y - A3.y) - (A1.y - A3.y) * (A2.x - A3.x)*/
    public static boolean isSunIntoTriangule(Point2D p1,Point2D p2,Point2D p3){
        Point2D sunPosition = new Point2D.Double(0,0);
        Double triangleOrientation =  calculateOrientation(p1,p2,p3);

        if(triangleOrientation > 0){
            return calculateOrientation(p1,p2,sunPosition) > 0 &&
                    calculateOrientation(p2,p3,sunPosition) > 0 &&
                    calculateOrientation(p3,p1,sunPosition) > 0;
        }else {
            return calculateOrientation(p1,p2,sunPosition) < 0 &&
                    calculateOrientation(p2,p3,sunPosition) < 0 &&
                    calculateOrientation(p3,p1,sunPosition) < 0;
        }
    }

    private static Double calculateOrientation(Point2D p1, Point2D p2 ,Point2D p3){

        return (p1.getX() -p3.getX()) * (p2.getY() - p3.getY())  -
                (p1.getY() - p3.getY()) * (p2.getX() - p3.getX());
    }

   public static double calculatePerimeter(Point2D p1, Point2D p2, Point2D p3){

       return   calculateDistance(p1,p2)+ calculateDistance(p1,p3)
                + calculateDistance(p2,p3);
   }

   private static Double calculateDistance(Point2D p1, Point2D p2){
       return Math.sqrt( getAbsValue( Math.pow(getAbsValue(p2.getX())- getAbsValue(p1.getX()),2.0) -
               Math.pow(getAbsValue(p2.getY()) -getAbsValue( p1.getY()),2)));
   }

   private static Double getAbsValue(Double n1){
        return Math.abs(n1);
   }
}

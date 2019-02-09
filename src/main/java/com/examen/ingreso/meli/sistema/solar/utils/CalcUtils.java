package com.examen.ingreso.meli.sistema.solar.utils;

import com.examen.ingreso.meli.sistema.solar.entities.Planet;
import com.examen.ingreso.meli.sistema.solar.entities.PolarCordenate;
import java.awt.geom.Point2D;


public class CalcUtils {

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
     * Verifica que dos puntos pasado como parametros en Coordenada polar esten alineados entre si
     * @Input PolarCordenate p1 ,PolarCorednate p2
     * @return boolean*/
    public  static  boolean isAlingPlanetsAndSun(PolarCordenate p1, PolarCordenate p2){
        if(p2.getAnguleInRadian().equals(0.00) && p1.getAnguleInRadian().equals(0.00)){
            return true;
        }

        if( p2.getAnguleInRadian().equals(0D)){
            return p2.getAnguleInRadian()%p1.getAnguleInRadian() == 0;
        }else {
            return p1.getAnguleInRadian()%p2.getAnguleInRadian() == 0;
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

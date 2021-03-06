package com.examen.ingreso.meli.sistema.solar;

import com.examen.ingreso.meli.sistema.solar.utils.CalcUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.geom.Point2D;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalcUtilsTest {

    @Test
    public  void checkPlanetsIsAlingToX(){
        Point2D p1 = new Point2D.Double(2.0,2.1);
        Point2D p2 = new Point2D.Double(2.0,3.3);
        Point2D p3 = new Point2D.Double(2.0,4.1);
        Assert.assertTrue(CalcUtils.isAlingToPlanets(p1,p2,p3));
    }

    @Test
    public  void checkPlanetIsAlingtoY(){
        Point2D p1 = new Point2D.Double(1.0,2);
        Point2D p2 = new Point2D.Double(3.0,2);
        Point2D p3 = new Point2D.Double(5.0,2);
        Assert.assertTrue(CalcUtils.isAlingToPlanets(p1,p2,p3));

    }


    @Test
    public  void checkPlanetIsNotAling(){
        Point2D p1 = new Point2D.Double(1.0,4);
        Point2D p2 = new Point2D.Double(3.0,5);
        Point2D p3 = new Point2D.Double(5.0,1);
        Assert.assertFalse(CalcUtils.isAlingToPlanets(p1,p2,p3));
    }


    @Test
    public  void sunIntoTriangule(){
        Point2D p1 = new Point2D.Double(1,-2);
        Point2D p2 = new Point2D.Double(-2,-3);
        Point2D p3 = new Point2D.Double(-1,3);
        Assert.assertTrue(CalcUtils.isSunIntoTriangule(p1,p2,p3));
    }

    @Test
    public void sunNotInToTriangule(){
        Point2D p1 = new Point2D.Double(1,2);
        Point2D p2 = new Point2D.Double(2,3);
        Point2D p3 = new Point2D.Double(1,3);
        Assert.assertFalse(CalcUtils.isSunIntoTriangule(p1,p2,p3));
    }

    @Test
    public void planetsAlingWithSun(){
        Point2D p1 = new Point2D.Double(500.00,0.00);
        Point2D p2 = new Point2D.Double(2000.00,0.0);
        Point2D p3 = new Point2D.Double(1000.00,0.00);



        boolean p1p2 = CalcUtils.isAlingPlanetsAndSun(p1,p2,p3);

        Assert.assertFalse(p1p2);
    }





}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.edu.gymspmkr.bike;

/**
 *
 * @author bracek
 */
public class RunBike {

    public static void main(String[] args) {
        
        HorskyBike horsky = new HorskyBike();
        


        HorskyBike a = new HorskyBike(10, 20, 30, 40);
        System.out.println("a.getVyskaSedadla() = " + a.getVyskaSedadla());

        if (a instanceof HorskyBike) {
            System.out.println("a je typom HorskyBike");
            HorskyBike b = (HorskyBike) a;
        }

        if (a instanceof Bicykel) {
            System.out.println("a je typom Bicykel");
            Bicykel b = (Bicykel) a;
        }

        if (a instanceof Object) {
            System.out.println("a je typom Object");
            Object b = (Object) a;
        }

        System.out.println("*****************************");



        //implicitny casting 
        Object obj = new HorskyBike(); //obj je objetk typu objek, HorskyBike, Bicykel

        if (obj instanceof Object) {
            System.out.println("obj is instanceof Object");
            Object x = (Object) obj;
        }

        if (obj instanceof Bicykel) {
            System.out.println("obj is instanceof Bicykel");
            Bicykel x = (Bicykel) obj;
        }

        if (obj instanceof HorskyBike) {
            System.out.println("obj is instanceof HorskyBike");
            HorskyBike x = (HorskyBike) obj;
        }


        System.out.println("*****************************");
        
        Bicykel bicy = new Bicykel();
        if (bicy instanceof HorskyBike) {
            HorskyBike horskyBike = (HorskyBike) bicy;
            System.out.println("bike je typu HorskyBike");
        }else
            System.out.println("!!! bike nie je typu HorkyBike");
        
        
        
           //implicitny casting 
        Object obj1 = new Bicykel(); //obj je objetk typu objek, HorskyBike, Bicykel

        if (obj1 instanceof Object) {
            System.out.println("obj1 is instanceof Object");
            Object x = (Object) obj;
        }

        if (obj1 instanceof Bicykel) {
            System.out.println("obj1 is instanceof Bicykel");
            Bicykel x = (Bicykel) obj;
        }

        if (obj1 instanceof HorskyBike) {
            System.out.println("obj is instanceof HorskyBike");
            HorskyBike x = (HorskyBike) obj;
        }else
        {
             System.out.println("!!! obj1 nie je  instanceof HorskyBike");
        }


        
    }

    
}

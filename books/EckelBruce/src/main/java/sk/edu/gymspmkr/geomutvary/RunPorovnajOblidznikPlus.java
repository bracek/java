/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.edu.gymspmkr.geomutvary;

/**
 *
 * @author bracek
 */
public class RunPorovnajOblidznikPlus {

    public static void main(String[] args) {
        ObdlznikPlus a = new ObdlznikPlus();
        a.sirka = 10;
        a.vyska = 10;

        ObdlznikPlus b = new ObdlznikPlus();
        b.sirka = 5;
        b.vyska = 4;

        System.out.println("b.jeVacsiNez(a) = " + b.jeVacsiNez(a));



        System.out.println("b.NajdiVacsi(a,b) = " + b.NajdiVacsi(a, b));




    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.pavel.s14.ss01;

/**
 *
 * @author bracek
 */
public class TestAnimal {

    public static void main(String[] args) {
        Animal[] animals = new Animal[6];
        for (int i = 0; i < animals.length; i++) {
            switch ((int) (1.0 + Math.random() * 3.0)) {
                
                case 1:
                    animals[i] = new Bird("bird", i);
                    break;
                case 2:
                    animals[i] = new Elephant("elephant", i);
                    break;
                case 3:
                    animals[i] = new Snake("snake", i);
                    break;
            }
            
        }
        Animal t;
        for (int i = 0; i < animals.length; i++) {
            t = animals[i];
            t.listingInfo();            
        }
    }
    
}

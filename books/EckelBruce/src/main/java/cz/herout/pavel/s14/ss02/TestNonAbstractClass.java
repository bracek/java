/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s14.ss02;


/**
 *
 * @author bracek
 */
public class TestNonAbstractClass {
    public static void main(String[] args) {
        Animal[] animals    = new Animal[6];
        for (int i = 0; i < animals.length; i++) {
//            Animal animal = animals[i];
            switch((int) (1.0 + Math.random() * 2.0)){
                      case 1:
                    animals[i] = new Bird("bird", i);
                    break;
                case 2:
                    animals[i] = new Elephant("elephant", i);
                    break;
            
            }
        }
        for (int i = 0; i < animals.length; i++) {
//            Animal animal = animals[i];
            animals[i].listingInfo();
        }
    }
}
                
            
            


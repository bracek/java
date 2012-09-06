/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s13.ss08;

import cz.herout.pavel.s13.ss03.Info;
import cz.herout.pavel.s13.ss04.InfoDalsi;

/**
 *
 * @author bracek
 */
public class Test {
    public static void main(String[] args) {
        
        Info iRect = new Rectangle(10, 10);
        InfoDalsi idalsiRect = new Rectangle(3, 5);
        InfoOba iObaRect = new Rectangle(2, 4);
        
        
       iRect.kdoJsem();
       ((Rectangle)iRect).vlastnosti();
       
       idalsiRect.vlastnosti();
       ((Rectangle)idalsiRect).kdoJsem();
       
       iObaRect.kdoJsem();
       iObaRect.vlastnosti();
        System.out.println("Pocet rozhrani = " + InfoOba.POCET);            
        
        
    }

}

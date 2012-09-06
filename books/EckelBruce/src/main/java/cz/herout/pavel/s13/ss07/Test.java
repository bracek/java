/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.pavel.s13.ss07;

import cz.herout.pavel.s13.ss03.Info;
import cz.herout.pavel.s13.ss04.InfoDalsi;

/**
 *
 * @author bracek
 */
public class Test {

    public static void main(String[] args) {
        
        Rectangle rect = new Rectangle(2, 4);
        Info ir = new Rectangle(3, 6);
        InfoDalsi idrect = new Rectangle(5, 7);
        
        rect.kdoJsem();
         rect.vlastnosti();
         
        ir.kdoJsem();        
        ((Rectangle)ir).vlastnosti();
        
        idrect.vlastnosti();
        ((Rectangle)idrect).kdoJsem();
        
        
         ((Rectangle)ir).writeWidth();
        
                
        




    }
}

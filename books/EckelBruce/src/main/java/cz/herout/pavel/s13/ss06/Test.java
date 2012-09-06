/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s13.ss06;

import cz.herout.pavel.s13.ss03.Info;

/**
 *
 * @author bracek
 */
public class Test {
    public static void main(String[] args) {
        Usecka u = new Usecka(5);
        Obdlznik o = new Obdlznik(2,4);
        Info iu = new Usecka(4);    
        Info io = new Obdlznik(3,6);
        u.kdoJsem();
        o.kdoJsem();
        iu.kdoJsem();
        io.kdoJsem();                
    }
}

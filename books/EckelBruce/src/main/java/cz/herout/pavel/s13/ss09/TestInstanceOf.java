/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s13.ss09;

import cz.herout.pavel.s13.ss03.Info;
import cz.herout.pavel.s13.ss08.InfoOba;
import cz.herout.pavel.s13.ss08.Rectangle;
import cz.herout.pavel.s13.ss08.Vector;

/**
 *
 * @author bracek
 */
public class TestInstanceOf {
    public static void main(String[] args) {
        
        Vector vector = new Vector(10);
        Rectangle rec = new Rectangle(3,4);
        
       
        if (vector instanceof Info) {
            Info info = (Info) vector;
            System.out.println("vector implementuje Info");
        }
        
        if (rec instanceof Info) {
            
            Info info = (Info) rec;
            System.out.println("rec implementuje Info");
        }
        
        if (vector instanceof InfoOba) {
            InfoOba infoOba = (InfoOba) vector;
            System.out.println("vector impleemntuje InfoOba");
        }
        
        if (rec instanceof InfoOba) {
            InfoOba infoOba = (InfoOba) rec;
            System.out.println("rec implementuje InfoOba");
        }
        
        if (vector instanceof Vector) {
            Vector vector1 = (Vector) vector;
            System.out.println("vector je instanciou Vector");
        }
       
        
    }

}

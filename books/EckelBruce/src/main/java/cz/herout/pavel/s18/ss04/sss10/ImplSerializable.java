/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s18.ss04.sss10;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author bracek
 */
public class ImplSerializable implements Serializable {
    Date d;

    
    ImplSerializable() {
       d = new Date(); 
    }

    
}

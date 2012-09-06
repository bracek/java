/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package foreign.eckel.bruce.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author bracek
 */
public class ThawAlien {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        ObjectInputStream vstup =
                new ObjectInputStream(new FileInputStream("X.file"));
        Object zahada = vstup.readObject();
        System.out.println("zahada.getClass() = " + zahada.getClass());
        
    }

}

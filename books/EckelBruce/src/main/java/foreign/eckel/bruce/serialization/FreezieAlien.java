/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package foreign.eckel.bruce.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 *
 * @author bracek
 */
public class FreezieAlien {
    public static void main(String[] args) throws IOException{
        ObjectOutput vystup = new ObjectOutputStream(new FileOutputStream("X.file"));
        Alien zorcon = new Alien();
        vystup.writeObject(zorcon);
        
    }

    
}

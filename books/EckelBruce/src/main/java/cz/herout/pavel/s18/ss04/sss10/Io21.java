/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.pavel.s18.ss04.sss10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 *
 * @author bracek
 */
public class Io21 {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

        String aktDir = System.getProperty("user.dir");
        System.out.println("aktDir = " + aktDir);
        String finalPath = aktDir + "/src/cz/herout/pavel/s18/ss04/sss10/";

        File fout = new File(finalPath, "datum.bin");
        FileOutputStream fwJm = new FileOutputStream(fout);
        ObjectOutputStream fw = new ObjectOutputStream(fwJm);
        
        Date d = new Date();
        System.out.println("Vznik = " + d);
        ImplSerializable impl = new ImplSerializable();
        System.out.println("impl.d.toString() = " + impl.d.toString());
        fw.writeObject(d);
        fw.writeObject(impl);
        fwJm.close();
        
        d  = null;
        impl = null;
        
        FileInputStream frJm = new FileInputStream("datum.bin");
        ObjectInputStream fr = new ObjectInputStream(frJm);
        d = (Date) fr.readObject();
        impl = (ImplSerializable) fr.readObject();
        fwJm.close();
        
        System.out.println("Cteni = " + d);
        System.out.println(impl.d.toString());
        
        
    }
}

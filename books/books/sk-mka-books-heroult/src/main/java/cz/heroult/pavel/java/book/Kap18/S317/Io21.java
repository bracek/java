package cz.heroult.pavel.java.book.Kap18.S317;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                  Uèebnice jazyka Java                       //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       CTI_ME.TXT                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2000                      // 
//                                                             //
/////////////////////////////////////////////////////////////////

import java.io.*;
import java.util.*;

class ImplSerializable implements Serializable {
  Date d;
  ImplSerializable() { 
    d = new Date(); 
  }
}

public class Io21 {
  public static void main(String[] argv)
                throws IOException, ClassNotFoundException {
    FileOutputStream fwJm = new FileOutputStream("datum.bin");
    ObjectOutputStream fw = new ObjectOutputStream(fwJm);

    Date d = new Date();
    System.out.println("Vznik: " + d);
    ImplSerializable impl = new ImplSerializable();
    System.out.println(impl.d.toString());
    fw.writeObject(d);
    fw.writeObject(impl);
    fwJm.close();
    d = null;      // zruseni instance
    impl = null;

    FileInputStream frJm = new FileInputStream("datum.bin");
    ObjectInputStream fr = new ObjectInputStream(frJm);
    d = (Date)fr.readObject();
    impl = (ImplSerializable)fr.readObject();
    fwJm.close();
    System.out.println("Cteni: " + d);
    System.out.println(impl.d.toString());
  }
}



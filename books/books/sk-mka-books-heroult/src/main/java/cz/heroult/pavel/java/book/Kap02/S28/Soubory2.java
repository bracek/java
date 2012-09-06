package cz.heroult.pavel.java.book.Kap02.S28;

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

public class Soubory2 {
  public static void main(String[] argv) throws IOException{
    File soub = new File("b.txt");
    File adr = new File("TMP");

    System.out.println(new Date(soub.lastModified()));
    System.out.println(new Date(adr.lastModified()));

    System.out.println(soub.length());
    System.out.println(adr.length());

    File jiny = new File("c.txt");
    soub.renameTo(jiny);
    adr.renameTo(new File("TMP-OLD"));

    soub.delete();   // nevymaže c.txt
    adr.delete();    // nevymaže TMP-OLD
    jiny.delete();   // skuteèné vymazání c.txt
  }
}

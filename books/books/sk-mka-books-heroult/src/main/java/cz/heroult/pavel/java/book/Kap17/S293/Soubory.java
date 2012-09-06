package cz.heroult.pavel.java.book.Kap17.S293;

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

public class Soubory {
  public static void main(String[] argv) {
   String aktDir = System.getProperty("user.dir");
   File soubAbs = new File(aktDir, "a.txt");
   File soubRel = new File("TMP" + File.separator + "a.txt");
   File soub = new File("a.txt");

   System.out.println(soubRel.getAbsolutePath());
   System.out.println(soubRel.getName());
   System.out.println(soubRel.getParent());

   System.out.println(soubAbs.getAbsolutePath());
   System.out.println(soubAbs.getName());
   System.out.println(soubAbs.getParent());

   System.out.println(soub.getAbsolutePath());
   System.out.println(soub.getName());
   System.out.println(soub.getParent());
  }
}

package cz.heroult.pavel.java.book.Kap17.S295;

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

public class VypisAdresare {
  public static void main(String[] argv) {
    String jmenoAktDir = System.getProperty("user.dir");
    File aktDir = new File(jmenoAktDir);
    String[] jmena;
    jmena = aktDir.list();
    for (int i = 0;  i < jmena.length;  i++)
      System.out.println(jmena[i]);

    File[] soubory;
    soubory = aktDir.listFiles();
    for (int i = 0;  i < soubory.length;  i++)
      System.out.println(soubory[i].getName() + "\t"
                         + soubory[i].length());
  }
}

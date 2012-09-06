package cz.heroult.pavel.java.book.Kap17.S295;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojov� k�d je sou��st� distribuce bal�ku program�,  //
//     poskytovan�ch jako dopl�uj�c� informace ke knize        //
//                                                             //
//                  U�ebnice jazyka Java                       //
//                                                             //
//     P�e�t�te si, pros�m, d�kladn� upozorn�n� v souboru      // 
//                       CTI_ME.TXT                            //
//        kter� je ned�lnou sou��st� t�to distribuce           //
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

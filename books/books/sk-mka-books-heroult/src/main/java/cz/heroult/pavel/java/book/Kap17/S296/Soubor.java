package cz.heroult.pavel.java.book.Kap17.S296;

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

class FiltrPripony implements FilenameFilter {
  String maska;

  FiltrPripony(String maska) {
    this.maska = maska;
  }

  public boolean accept(File dir, String name) {
    if (name.lastIndexOf(maska) > 0)
      return true;
    else
      return false;
  }
}

class FiltrVelikosti implements FilenameFilter {
  int velikost;

  FiltrVelikosti(int velikost) {
    this.velikost = velikost;
  }

  public boolean accept(File dir, String name) {
    File f = new File(dir, name);
    if (f.length() > velikost)
      return true;
    else
      return false;
  }
}

public class Soubor {
  public static void main(String[] args) {
    String jmenoAktDir = System.getProperty("user.dir");
    File aktDir = new File(jmenoAktDir);

    String[] jmena;
    FiltrPripony filtrPr = new FiltrPripony(".java");
    jmena = aktDir.list(filtrPr);
    for (int i = 0;  i < jmena.length;  i++)
      System.out.println(jmena[i]);


    File[] soubory;
    FiltrVelikosti filtrVel = new FiltrVelikosti(1000);
    soubory = aktDir.listFiles(filtrVel);
    for (int i = 0;  i < soubory.length;  i++)
      System.out.println(soubory[i].getName() + "\t"
                         + soubory[i].length());
  }
}

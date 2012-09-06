package cz.heroult.pavel.java.book.Kap18.S307;

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

public class Presuny {
  public static void main(String[] args) throws IOException {
    File frJm = new File("a.txt");
    FileReader fr = new FileReader(frJm);

    FileWriter fw = new FileWriter("b.txt");

    long delka = frJm.length();
    int c;

    // prvni kopirovani od zacatku
    for (long i = 0;  i < delka;  i++) {
      c = fr.read();
      fw.write(c);
    }

    fr.close();  // ponìkud nepøehledný pøesun na zaè. souboru
    fr = new FileReader(frJm);
//    fr.reset();   // není tímto proudem podporováno

    fw.close();
    fw = new FileWriter("b.txt", true);

    // druhe kopirovani z poloviny
    fr.skip(delka / 2);
    while((c = fr.read()) != -1)
      fw.write(c);

    fr.close();
    fw.close();
  }
}

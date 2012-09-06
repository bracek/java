package cz.heroult.pavel.java.book.Kap18.S313;

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

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FileReaderSplit {
  public static void main(String[] args) throws IOException {
    BufferedReader bfr = new BufferedReader(
                         new FileReader("buf.txt"));
    String radka;
    int suma = 0;
    while((radka = bfr.readLine()) != null) {
      String[] cisla = radka.split(" ");
      for (String cislo : cisla) {
        suma += Integer.parseInt(cislo);
      }
    }
    System.out.println("Soucet je: " + suma);
    bfr.close();
  }
}

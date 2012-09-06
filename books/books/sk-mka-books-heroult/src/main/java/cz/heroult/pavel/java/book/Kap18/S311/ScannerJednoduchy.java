package cz.heroult.pavel.java.book.Kap18.S311;

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

import java.util.Scanner;
import java.util.Locale;
import java.io.File;
import java.io.IOException;

public class ScannerJednoduchy {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(new File("data-scanner.txt"));
    sc.useLocale(Locale.US); 
    int i = sc.nextInt();
    double d = sc.nextDouble();
    String s = sc.next();
    System.out.println("i = " + i 
              + ", d = " + d + ", s = " + s);
  }
}

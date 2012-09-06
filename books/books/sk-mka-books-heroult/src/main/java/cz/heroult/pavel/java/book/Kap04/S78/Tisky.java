package cz.heroult.pavel.java.book.Kap04.S78;

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

public class Tisky {
  public static void main(String[] args) {
    int i, j;

    i = 4; j = 7;
    System.out.print("Toto je hodnota promenne i: " + i + "\n");

    System.out.println("Toto je hodnota promenne i: " + i);

    System.out.println("Toto je promenna i: "+i+"\na toto j: "+j);

    System.out.println("Soucet je " + i + j);

    System.out.println("Soucet je " + (i + j));

    System.out.println("Pracovali na 100%");

    System.out.println("Soucet je "+(i+j)+"\tSoucin je "+(i*j));

    System.out.println("\007Chyba, pokus o deleni nulou.");

    System.out.println("Toto je \"backslash\" : '\\'");

    System.out.println("Toto je 'backslash' : '\\'");
  }
}

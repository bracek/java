package cz.heroult.pavel.java.book.Kap09.S171;

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

public class Retez12 {
  public static void main(String[] args) {
    String s = "mala a VELKA";
    int i;

    i = s.indexOf('a');
    System.out.println("Prvni a je na " + i + ". pozici");
    i = s.indexOf('a', i + 1);
    System.out.println("Dalsi a je na " + i + ". pozici");
    i = s.lastIndexOf('a');
    System.out.println("Posledni a je na " + i + ". pozici");
    i = s.lastIndexOf('a', i - 1);
    System.out.println("Predposledni a je na "+ i +". pozici");
  }
}

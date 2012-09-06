package cz.heroult.pavel.java.book.Kap09.S172;

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

public class Retez13 {
  public static void main(String[] args) {
    String s1 = "mala a VELKA";
    int i;

    i = s1.lastIndexOf("VEL");
    System.out.println("Posledni VEL je na "+ i +". pozici");
  }
}

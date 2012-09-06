package cz.heroult.pavel.java.book.Kap09.S170;

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

public class Retez9 {
  public static void main(String[] args) {
    String s = "mala a VELKA";

    if (s.startsWith("mala") == true)
      System.out.println("Zacina na \"mala\"");
    if (s.endsWith("mala") == false)
      System.out.println("Nekonci na \"mala\"");
  }
}

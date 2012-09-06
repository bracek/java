package cz.heroult.pavel.java.book.Kap03.S53;

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

public class ViceEnum {
  enum Karty {CERVENA, ZELENA, KULE, ZALUDY};
  enum Barvy {MODRA, ZLUTA, CERVENA, ZELENA};
                 
  public static void main(String[] args) {
    Karty k = Karty.CERVENA;
//    Barvy b = Karty.CERVENA;     // nelze
    Barvy b = Barvy.CERVENA;

    System.out.println("karta: " + k);
    System.out.println("barva: " + b);
  }
}

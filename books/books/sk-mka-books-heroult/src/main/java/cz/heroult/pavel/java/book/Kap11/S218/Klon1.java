package cz.heroult.pavel.java.book.Kap11.S218;

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

public class Klon1 {
  int i;

  Klon1(int i) { this.i = i; }

  public static void main(String[] args) throws
                                 CloneNotSupportedException {
    Klon1 kopie, orig = new Klon1(5);
    kopie = (Klon1) orig.clone();
    System.out.println("orig: " + orig.i);
    System.out.println("kopie: " + kopie.i);
  }
}

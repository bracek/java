package cz.heroult.pavel.java.book.Kap11.S212;

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

class Rodic {
  public int i;
  public Rodic() { i = 1; }
  final int getI() { return i; }
}

public class Potomek extends Rodic {
  //  int getI() { return i * 2; }  // chyba

  public static void main(String[] args) {
    Potomek pot = new Potomek();
    System.out.println("Hodnota je: " + pot.getI());
  }
}

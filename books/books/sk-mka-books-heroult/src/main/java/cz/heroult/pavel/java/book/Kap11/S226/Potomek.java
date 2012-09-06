package cz.heroult.pavel.java.book.Kap11.S226;

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
}

public class Potomek extends Rodic {
  public static void main(String[] args) {
    Potomek dite = new Potomek();
    System.out.println("Jmeno tridy je: " +
                  dite.getClass().getName());
    System.out.println("Jmeno rodicovske tridy je: " +
                  dite.getClass().getSuperclass().getName());
  }
}

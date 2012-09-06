package cz.heroult.pavel.java.book.Kap19.S340;

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

public class Exit {
  public static void konec() {
    System.out.println("Pred exit()");
    System.exit(-1);
    System.out.println("PO exit()");
  }

  public static void main(String[] args) {
    System.out.println("Pred konec()");
    konec();
    System.out.println("PO konec()");
  }
}

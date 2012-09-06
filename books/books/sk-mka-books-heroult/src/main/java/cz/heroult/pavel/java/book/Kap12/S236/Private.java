package cz.heroult.pavel.java.book.Kap12.S236;

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

class APriv {
  private int i;
  private int getI() { return i; }
}

class BPriv {
  int j;
  BPriv() {
    APriv a = new APriv();
//    a.i = 1;        // chyba
//    j = a.getI();   // chyba
  }
}

class ABPriv extends APriv {
  int j;
  ABPriv() {
//    i = 1;        // chyba
//    j = getI();   // chyba
  }
}

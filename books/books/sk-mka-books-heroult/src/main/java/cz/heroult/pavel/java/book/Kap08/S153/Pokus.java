package cz.heroult.pavel.java.book.Kap08.S153;

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

public class Pokus {
  public int promInstance;
  public static int promTridy;

  public Pokus(int promInstance, int promTridy) {
    this.promInstance = promInstance;
    this.promTridy = promTridy;    // možný zpùsob
    Pokus.promTridy = promTridy;   // èitelnìjší zpùsob
  }


  public static void main(String[] args) {
    Pokus p = new Pokus(1, 2);

    System.out.println(p.promInstance + " " + promTridy);
  }
}

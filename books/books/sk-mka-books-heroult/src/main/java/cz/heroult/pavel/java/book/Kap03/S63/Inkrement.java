package cz.heroult.pavel.java.book.Kap03.S63;

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

public class Inkrement {
  public static void main(String[] args) {
    int citac = 0;

    citac = citac + 1;
    System.out.println("citac = " + citac);

    citac += 1;
    System.out.println("citac = " + citac);

    ++citac;
    System.out.println("citac = " + citac);

    ++citac;
    System.out.println("citac = " + citac);
  }
}

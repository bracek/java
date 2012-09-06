package cz.heroult.pavel.java.book.Kap03.S47;

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

public class Nekonecno {
  public static void main(String[] args) {
    double nula = 0.0;
    double vysledek = +5.0 / nula;

    System.out.println(vysledek);
    if (Double.POSITIVE_INFINITY == vysledek)
      System.out.println("kladne nekonecno");

    vysledek = -5.0 / nula;
    System.out.println(vysledek);

    if (Double.isInfinite(vysledek) == true)
      System.out.println("nekonecno");

    System.out.println("MAX = " + Float.MAX_VALUE +
                 ", 2 * MAX = " + (2 * Float.MAX_VALUE));

    vysledek = nula / nula;
    System.out.println(vysledek);

    if (Double.isNaN(vysledek) == true)
      System.out.println("neni cislo");
  }
}

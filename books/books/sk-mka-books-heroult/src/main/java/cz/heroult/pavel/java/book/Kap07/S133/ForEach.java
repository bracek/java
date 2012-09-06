package cz.heroult.pavel.java.book.Kap07.S133;

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

public class ForEach {
  public static void main(String[] args) {
    int[] pole = { 1, 3, 5, 7 };
    int suma = 0;
    
    for (int hodnota : pole) {
      suma += hodnota;
    }

    System.out.println("Suma = " + suma);
  }
}

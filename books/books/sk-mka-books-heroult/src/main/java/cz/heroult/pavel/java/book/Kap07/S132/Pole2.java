package cz.heroult.pavel.java.book.Kap07.S132;

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

public class Pole2 {
  public static final int MAX = 20;

  public static void main(String[] args) {
    int[] poleInt = new int[MAX];        // MAX vhodné
    for (int i = 0;  i < MAX;  i++) {    // MAX nevhodné
      poleInt[i] = i + 1;
      System.out.print(poleInt[i] + "  ");
    }
  }
}

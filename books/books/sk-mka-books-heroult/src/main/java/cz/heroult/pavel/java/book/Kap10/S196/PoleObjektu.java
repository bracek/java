package cz.heroult.pavel.java.book.Kap10.S196;

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

class IntADouble {
  private int i;
  private double d;
  IntADouble(int i, double d) { this.i = i; this.d = d; }

  public void vypis() {
    System.out.println("i = " + i + ", d = " + d);
  }
}

public class PoleObjektu {
  public static void main(String[] args) {
    IntADouble[] louka;
    louka = new IntADouble[3];
    for (int j = 0;  j < louka.length;  j++) {
      louka[j] = new IntADouble(j, (double) (j * 2));
    }
    for (int j = 0;  j < louka.length;  j++) {
      louka[j].vypis();
    }
  }
}

package cz.heroult.pavel.java.book.Kap11.S219;

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

public class Klon2 implements Cloneable {
  int i;

  Klon2(int i) { this.i = i; }

  protected Object clone() {
    Klon2 k = null;
    try {
      k = (Klon2) super.clone();
      k.i = this.i;
    }
    catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return k;
  }

  public static void main(String[] args) {
    Klon2 kopie, orig = new Klon2(5);
    kopie = (Klon2) orig.clone();
    System.out.println("orig: " + orig.i);
    System.out.println("kopie: " + kopie.i);
  }
}

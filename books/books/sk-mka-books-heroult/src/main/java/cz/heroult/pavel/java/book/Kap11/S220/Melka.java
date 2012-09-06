package cz.heroult.pavel.java.book.Kap11.S220;

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

class Pomocna {
  double d;
  Pomocna(double d) { this.d = d; }
}

public class Melka implements Cloneable {
  int i;
  Pomocna dTrida;

  Melka(int i, Pomocna dt) { this.i = i; dTrida = dt; }

  protected Object clone() {
    Melka k = null;
    try {
      k = (Melka)super.clone();
      k.i = this.i;
      k.dTrida = this.dTrida;
    }
    catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return k;
  }

  public static void main(String[] args) {
    Pomocna p = new Pomocna(3.14);
    Melka kopie, orig = new Melka(5, p);
    kopie = (Melka) orig.clone();
    System.out.println("orig: "+orig.i+"; "+orig.dTrida.d);
    System.out.println("kopie: "+kopie.i+"; "+kopie.dTrida.d);
    orig.i = 10;
    orig.dTrida.d = 6.28;
    System.out.println("orig: "+orig.i+"; "+orig.dTrida.d);
    System.out.println("kopie: "+kopie.i+"; "+kopie.dTrida.d);
  }
}

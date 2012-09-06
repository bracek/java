package cz.heroult.pavel.bohatstvoKnihoven.kap13;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                   Java -- bohatství knihoven                //
//                II. opravené a rozšíøené vydání              //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       Cti_me.txt                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2006                      // 
//                                                             //
/////////////////////////////////////////////////////////////////

import java.util.*;

class Klic {
  private String kl;
  public Klic(String kl) { this.kl = "k" + kl; }
  public String toString() { return kl; }
  public int hashCode() { return kl.hashCode(); }
  public boolean equals(Object o) {
    return (o instanceof Klic)
      && kl.equals(((Klic)o).kl);
  }
  public void finalize() {
    System.out.println("Uvolneni klice: "+ kl);
  }
}

class Hodnota {
  private String ho;
  public Hodnota(String ho) { this.ho = "h" + ho; }
  public String toString() { return ho; }
  public void finalize() {
    System.out.println("Uvolneni hodnoty: " + ho);
  }
}

public class PouzitiWeakHashMap {
  public static void main(String[] args) {
    int n = 10;
    // mozne nastaveni pomoci prikazove radky
    if (args.length > 0) {
      n = Integer.parseInt(args[0]);
    }
    Klic[] poleKlicu = new Klic[n];
    Hodnota[] poleHodnot = new Hodnota[n];
    WeakHashMap<Klic, Hodnota> whm = new WeakHashMap<Klic, Hodnota>();
    for(int i = 0; i < n; i++) {
      Klic k = new Klic(Integer.toString(i));
      Hodnota h = new Hodnota(Integer.toString(i));
      if (i % 4 == 0) {
        poleKlicu[i] = k;     // reference mimo WeakHashMap 
      }
      if ((i + 1) % 4 == 0) {
        poleHodnot[i] = h;    // reference mimo WeakHashMap 
      }
      whm.put(k, h);
    }
    System.out.println("Pred uvolnenim pameti: ");
    System.out.println(whm);

    System.gc();
    System.runFinalization();

    System.out.println("Po uvolneni pameti: ");
    for (Map.Entry<Klic, Hodnota> e : whm.entrySet()) {
      System.out.print(e.getKey() + "=" + e.getValue() + ", ");
    }
  }
}

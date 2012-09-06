package cz.heroult.pavel.bohatstvoKnihoven.kap11;

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


class Vaha {
  double vaha;
  Vaha(double vaha) { this.vaha = vaha; }

  public String toString() {
    return "" + vaha;
  }

  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (o instanceof Vaha == false)
      return false;
    boolean stejnaVaha = (vaha == ((Vaha) o).vaha);
    return stejnaVaha;
  }

  public int hashCode() {
    return (int) vaha;
  }
}
public class IteratorVHashMape {
  public static void main(String[] args) {
    HashMap<String, Vaha> hm = new HashMap<String, Vaha>();
    hm.put("Pavel", new Vaha(85));
    hm.put("Venca", new Vaha(105));
    hm.put("Karel", new Vaha(70));
    System.out.println("Mapa: " + hm);

    // iterator pres klice
    System.out.print("Klice: ");
    for (Iterator<String> it = hm.keySet().iterator();  it.hasNext(); ) {
      System.out.print(it.next() + ", ");
    }
    System.out.println();
    for (String jmeno: hm.keySet()) {
      System.out.print(jmeno + ", ");
    }
    System.out.println();
 
    // iterator pres hodnoty
    System.out.print("Hodnoty: ");
    for (Iterator<Vaha> it = hm.values().iterator();  it.hasNext(); ) {
      System.out.print(it.next() + ", ");
    }
    System.out.println();
    for (Vaha va: hm.values()) {
      System.out.print(va.vaha + ", ");
    }
    System.out.println();

    // iterator pres dvojice
    System.out.print("Dvojice: ");
    for (Iterator<Map.Entry<String, Vaha>> it = hm.entrySet().iterator();  it.hasNext(); ) {
      Map.Entry<String, Vaha> e = it.next();
      System.out.print(e.getKey() + "=" + e.getValue() + ", ");
    }
    System.out.println();

    for (Map.Entry<String, Vaha> e: hm.entrySet()) {
      System.out.print(e.getKey() + "=" + e.getValue() + ", ");
    }
    System.out.println();

    System.out.print("Vsichni ztloustli: ");
    for (Map.Entry<String, Vaha> e: hm.entrySet()) {
      double d = e.getValue().vaha;
      Vaha v = new Vaha(d + 10);
      System.out.print(e.getKey() + "=" + e.setValue(v) + "->" + e.getValue() + ", ");
    }
    System.out.println();
    System.out.println("Mapa: " + hm);
  }
}

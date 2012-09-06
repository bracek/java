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

class VahaKlic implements Comparable<VahaKlic> {
  double vaha;
  VahaKlic(double vaha) { this.vaha = vaha; }

  public String toString() {
    return Double.toString(vaha);
  }

  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (o instanceof VahaKlic == false)
      return false;
    boolean stejnaVaha = (vaha == ((VahaKlic) o).vaha);
    return stejnaVaha;
  }

  public int hashCode() {
    return (int) vaha;
  }

  public int compareTo(VahaKlic vk) {
    return ((int) (this.vaha - vk.vaha));
  }
}

class VahovyKomparator implements Comparator<VahaKlic> {
  public int compare(VahaKlic vk1, VahaKlic vk2) {
    return (int) (vk1.vaha - vk2.vaha);
  }
}

public class MujTreeMap {
  static void praceSMapou(TreeMap<VahaKlic, String> tm) {
    String s;
    Comparator<? super VahaKlic> c = tm.comparator();
    if (c == null) {
      s = "prirozene razeni";
    }
    else {
      s = c.getClass().getName();
    }
    System.out.println("Komparator: " + s);

    tm.put(new VahaKlic(85), "Pavel");
    tm.put(new VahaKlic(105), "Venca");
    tm.put(new VahaKlic(74), "Karel");
    System.out.println("Mapa: " + tm);
    VahaKlic v = tm.lastKey();
    System.out.println("Nejvice vazi: "
                       + v + "=" + tm.get(v));
    SortedMap<VahaKlic, String> pom = tm.tailMap(new VahaKlic(85));
    System.out.println("Dva nejtezsi: " + pom + "\n");
  }

  public static void main(String[] args) {
    praceSMapou(new TreeMap<VahaKlic, String>());
    praceSMapou(new TreeMap<VahaKlic, String>(new VahovyKomparator()));
  }
}

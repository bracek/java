package cz.heroult.pavel.bohatstvoKnihoven.kap10;

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

class Hrusticka implements Comparable<Hrusticka> {
  int cena;
  Hrusticka(int cena) { this.cena = cena; }
  public String toString() { return "" + cena; }

   public boolean equals(Object o) {
     if (o == this)
       return true;
     if (o instanceof Hrusticka == false)
       return false;
     boolean stejnaCena = (cena == ((Hrusticka) o).cena);
     return stejnaCena;
   }

   public int hashCode() {
    return cena;
  }

  public int compareTo(Hrusticka h) {
    if (cena > h.cena)
      return (+1);
    else if (cena == h.cena)
      return (0);
    else
      return (-1);
  }
}

public class HruskyVMnozine {
  static void praceSHrustickami(String typ, Set<Hrusticka> st) {
    for (int i = 20;  i < 30;  i++) {
      st.add(new Hrusticka(i));
    }
    st.add(new Hrusticka(25));

    // tisk mnoziny pomoci for-each
    System.out.print(typ + "-pocet: " + st.size() + " [");
    for (Hrusticka h : st) {
      System.out.print(h + ", ");
    }
    System.out.println("]");
  }

  public static void main(String[] args) {
    praceSHrustickami("HashSet", new HashSet<Hrusticka>());
    praceSHrustickami("TreeSet", new TreeSet<Hrusticka>());
  }
}

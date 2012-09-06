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

class MyHruska implements Comparable<MyHruska> {
  int cena;
  MyHruska(int cena) { this.cena = cena; }
  public String toString() { return "" + cena; }

   public boolean equals(Object o) {
     if (o == this)
       return true;
     if (o instanceof MyHruska == false)
       return false;
     boolean stejnaCena = (cena == ((MyHruska) o).cena);
     return stejnaCena;
   }

   public int hashCode() {
    return cena;
  }

  public int compareTo(MyHruska h) {
    if (cena > h.cena)
      return (+1);
    else if (cena == h.cena)
      return (0);
    else
      return (-1);
  }
}

public class HruskyVSerazeneMnozine {
  public static void main(String[] args) {
    SortedSet<MyHruska> st = new TreeSet<MyHruska>();

    for (int i = 29;  i >= 20 ;  i--) {
      st.add(new MyHruska(i));
    }
    System.out.println("Cely kosik: " + st);
    System.out.println("Nejlevnejsi MyHruska: " +
                       st.first());
    System.out.println("Nejdrazsi MyHruska: " +
                       st.last());

//    TreeSet<MyHruska> pom = st.subSet(new MyHruska(23),
    SortedSet<MyHruska> pom = st.subSet(new MyHruska(23),
                            new MyHruska(26));
    System.out.println("Od 23 do 26: " + pom.size() + " " + pom);
    pom = st.headSet(new MyHruska(25));
    System.out.println("Pod 25: " + pom.size() + " " + pom);
    pom = st.tailSet(new MyHruska(25));
    System.out.println("Nad 25: " + pom.size() + " " + pom);
  }
}

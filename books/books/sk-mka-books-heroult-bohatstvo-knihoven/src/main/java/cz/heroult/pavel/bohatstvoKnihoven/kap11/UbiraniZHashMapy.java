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

class HashoVaha {
  double HashoVaha;
  HashoVaha(double HashoVaha) { this.HashoVaha = HashoVaha; }

  public String toString() {
    return "" + HashoVaha;
  }

  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (o instanceof HashoVaha == false)
      return false;
    boolean stejnaHashoVaha = (HashoVaha == ((HashoVaha) o).HashoVaha);
    return stejnaHashoVaha;
  }

  public int hashCode() {
    return (int) HashoVaha;
  }
}

public class UbiraniZHashMapy {
  static void naplneniMapyATisk(HashMap<String, HashoVaha> hm) {
    hm.clear();
    for (int i = 1;  i <= 4;  i++) {
      String jmeno = "Robot" + i;
      hm.put(jmeno, new HashoVaha(50 + i));
    }
    System.out.println("Mapa: " + hm);
  }
 
  public static void main(String[] args) {
    HashMap<String, HashoVaha> hm = new HashMap<String, HashoVaha>();

    naplneniMapyATisk(hm);
    Set<String> kl = hm.keySet();
    kl.remove("Robot1");
    kl.remove("Robot2");
    System.out.println("Mapa bez klicu: " + hm);
 
    naplneniMapyATisk(hm);
    Collection<HashoVaha> hod = hm.values();
    hod.remove(new HashoVaha(52));
    System.out.println("Mapa bez hodnoty: " + hm);

    naplneniMapyATisk(hm);
    for (Iterator<Map.Entry<String, HashoVaha>> it = hm.entrySet().iterator();  it.hasNext(); ) {
      String s = (String) it.next().getKey();
      char c = s.charAt(s.length() - 1);
      int i = Character.digit(c, 10);
      if (i % 2 == 0) {
        it.remove(); 
      }         
    }
    System.out.println("Mapa bez sudych: " + hm);
  }
}

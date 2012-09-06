package cz.heroult.pavel.bohatstvoKnihoven.kap04;

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

class CeleCislo {
  private int cislo;
  CeleCislo(int i) { this.cislo = i; }
  int getCislo() { return cislo; }
  void setCislo(int i) { this.cislo = i; }
  public String toString() { return "" + cislo; }
}

public class ArrayListVlastniTrida {
  public static void tiskni(String jmeno, List<CeleCislo> li) {
    int vel = li.size();
    System.out.print(jmeno + " (" + vel + ") : ");
    for (int i = 0;  i < vel;  i++) {
      System.out.print("[" + i + "]="
                       + li.get(i).getCislo() + ", ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    System.out.println("Vytvoreni seznamu");
    ArrayList<CeleCislo> ar = new ArrayList<CeleCislo>();
    for (int i = 0;  i < 5;  i++) {
      ar.add(new CeleCislo(i + 10));
    }
    tiskni("ar", ar);
    System.out.println("Tisk celeho seznamu: " + ar);

    System.out.println("Pridavani prvku");
    ar.add(2, new CeleCislo(77));
    tiskni("ar", ar);

    System.out.println("Vytvoreni podseznamu");
    List<CeleCislo> sl = ar.subList(2, 5);
    tiskni("sl", sl);

    ar.get(3).setCislo(33);
    tiskni("ar", ar);
    tiskni("sl", sl);
  }
}

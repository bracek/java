package cz.heroult.pavel.bohatstvoKnihoven.kap02;


import java.util.*;

public class TypovanaANetypovana {
  public static void main(String[] args) {
    ArrayList arNetypovany = new ArrayList();
    arNetypovany.add(new Integer(1));
    arNetypovany.add(new Integer(2));
//    arNetypovany.add("tri");
    for (int i = 0; i < arNetypovany.size(); i++) {
      Integer intObjekt = (Integer) arNetypovany.get(i);  // chyba p�i b�hu programu
      System.out.print(intObjekt.intValue() + ", ");
    }

    ArrayList<Integer> arTypovany = new ArrayList<Integer>();
    arTypovany.add(new Integer(1));
    arTypovany.add(new Integer(2));
    // arTypovany.add("tri");            // chyba p�i kompilaci
    for (int i = 0; i < arTypovany.size(); i++) {
      Integer intObjekt = arTypovany.get(i);  
      System.out.print(intObjekt.intValue() + ", ");
    }
  }
}


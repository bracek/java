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

class TypyTestu {
  public static final int POCET_RADU = 3;
  public static final boolean DEBUG = false;   // pro ladeni
  public static int pocetPrvku = 10000;

  private static Integer[] pole;

  private Collection<Integer> col;
  private String typKont;

  static void pripravaInicializacnihoPole() {
    pole = new Integer[pocetPrvku];
    for (int i = 0;  i < pocetPrvku;  i++) {
      pole[i] = new Integer(i);
    }
    if (DEBUG == true) {
      System.out.println("Pole pred: " + Arrays.asList(pole)); 
    }
    List<Integer> l = Arrays.asList(pole);
    Collections.shuffle(l);
    if (DEBUG == true) {
      System.out.println("List:      " + l);
    }
    pole = l.toArray(new Integer[0]);
    if (DEBUG == true) {
      System.out.println("Pole po:   " + Arrays.asList(pole)); 
    }
    l = null;
    System.gc();
  }

  TypyTestu(Collection<Integer> col) {
    this.col = col;
    this.typKont = col.getClass().getName();
    typKont = typKont.substring(typKont.lastIndexOf(".") + 1);
  }

  private void tisk() {
    if (DEBUG == true) {
      System.out.print(col + " ");
    }
  }

  private abstract class Akce {
    String typ;

    Akce(String typ) {
      this.typ = typ;
    }

    abstract void akce();
  }

  private Akce[] poleAkci = {
    new Akce("naplneni") {
      void akce() {
        Collections.addAll(col, pole);
        tisk();
      }
    },

    new Akce("pruchodIteratorem") {
      void akce() {
        Iterator it = col.iterator();
        while (it.hasNext()) {
          it.next();
        }
        tisk();
      }
    },

    new Akce("clearANaplneni") {
      void akce() {
        col.clear();
        Collections.addAll(col, pole);
        tisk();
      }
    },

    new Akce("vypusteniPolovinyIteratorem") {
      void akce() {
        Iterator it = col.iterator();
        while (it.hasNext() == true) {
          it.next();
          it.remove();
          it.next();
        }
        tisk();
      }
    },

    new Akce("hledani") {
      void akce() {
        for (int i = 0;  i < pocetPrvku;  i++) {
          col.contains(pole[i]);
        }
        tisk();
      }
    }
  };

  void testy() {
    System.out.println(typKont);
    for (int i = 0;  i < poleAkci.length;  i++) {
      long zac = System.currentTimeMillis();
      poleAkci[i].akce();
      long kon = System.currentTimeMillis();
      System.out.println(poleAkci[i].typ + ": " + (kon - zac));
    }
    System.out.println();
    col.clear();
    col = null;
    System.gc();
  }
}

public class RychlostMnozinaASeznam {
  public static void main(String[] args) {
    for (int i = 1;  i <= TypyTestu.POCET_RADU;  i++) {
      TypyTestu.pripravaInicializacnihoPole();
      System.out.println("Velikost kontejneru: " + TypyTestu.pocetPrvku);
      new TypyTestu(new ArrayList<Integer>()).testy();
      new TypyTestu(new LinkedList<Integer>()).testy();
      new TypyTestu(new HashSet<Integer>()).testy();
      new TypyTestu(new TreeSet<Integer>()).testy();

      // pokusy s kapacitou a load faktorem
//      new TypyTestu(new HashSet(TypyTestu.pocetPrvku / 3, (float)0.99)).testy();
//      new TypyTestu(new HashSet(TypyTestu.pocetPrvku, (float)0.99)).testy();
      TypyTestu.pocetPrvku *= 10;
    }
  }
}

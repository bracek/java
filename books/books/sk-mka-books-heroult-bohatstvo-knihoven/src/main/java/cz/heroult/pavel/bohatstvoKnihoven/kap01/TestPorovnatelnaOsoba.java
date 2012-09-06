package cz.heroult.pavel.bohatstvoKnihoven.kap01;

import java.util.*;

class PorOsoba implements Comparable<PorOsoba> {
  int vyska;
  double vaha;
  String popis;

  PorOsoba(int vyska, double vaha, String popis) {
    this.vyska = vyska;
    this.vaha = vaha;
    this.popis = popis;
  }

  public int compareTo(PorOsoba os) {
    return this.vyska - os.vyska;
  }

  public String toString() {
    return "vy = " + vyska + ", va = " + vaha + ", " + popis + "\n";
  }

  public static final Comparator<PorOsoba> PODLE_VYSKY = 
    new Comparator<PorOsoba>() {
    public int compare(PorOsoba os1, PorOsoba os2) {
      return os1.vyska - os2.vyska;
    }
  };

  public static final Comparator<PorOsoba> PODLE_VAHY = 
    new Comparator<PorOsoba>() {
    public int compare(PorOsoba os1, PorOsoba os2) {
      return (int) (os1.vaha - os2.vaha);
    }
  };

  public static final Comparator<PorOsoba> PODLE_POPISU = 
    new Comparator<PorOsoba>() {
    public int compare(PorOsoba os1, PorOsoba os2) {
      return os1.popis.compareTo(os2.popis);
    }
  };
}


public class TestPorovnatelnaOsoba {
  static PorOsoba[] poleOsob;

  public static void main(String[] args) {
    poleOsob = new PorOsoba[4];
    poleOsob[0] = new PorOsoba(186, 82.5, "muz");
    poleOsob[1] = new PorOsoba(172, 63.0, "zena");
    poleOsob[2] = new PorOsoba(105, 26.1, "dite");
    poleOsob[3] = new PorOsoba(116, 80.5, "obezni trpaslik");

    Arrays.sort(poleOsob, PorOsoba.PODLE_VAHY);
    System.out.println("Absolutni razeni podle vahy:\n"
                        + Arrays.toString(poleOsob));

    Arrays.sort(poleOsob, PorOsoba.PODLE_POPISU);
    System.out.println("Absolutni razeni podle popisu:\n"
                        + Arrays.toString(poleOsob));

    Arrays.sort(poleOsob, PorOsoba.PODLE_VYSKY);
    System.out.println("Absolutni razeni podle vysky:\n"
                        + Arrays.toString(poleOsob));

    Arrays.sort(poleOsob, Collections.reverseOrder(
                       PorOsoba.PODLE_VYSKY));
    System.out.println("Absolutni razeni podle vysky sestupne:\n"
                        + Arrays.toString(poleOsob));
  }
}

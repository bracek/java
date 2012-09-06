package cz.heroult.pavel.bohatstvoKnihoven.kap01;



import java.util.*;

class MalaOsoba implements Comparable<MalaOsoba> {
  private int vyska;
  private double vaha;
  private String popis;

  MalaOsoba(int vyska, double vaha, String popis) {
    this.vyska = vyska;
    this.vaha = vaha;
    this.popis = popis;
  }

/*  // jednodussi a lepsi pro "mala" cisla
  public int compareTo(MalaOsoba os) {
    return this.vyska - os.vyska;
  }
*/
  public int compareTo(MalaOsoba os) {
    if (this.vyska > os.vyska)
      return +1;
    else if (this.vyska == os.vyska)
      return 0;
    else
      return -1;
  }

  public String toString() {
    return "vy = " + vyska + ", va = " + vaha + ", " + popis ;
  }
}

public class ArraysPrirozeneRazeniObecnychObjektu {
  static MalaOsoba[] poleOsob;

  public static void main(String[] args) {
    poleOsob = new MalaOsoba[4];
    poleOsob[0] = new MalaOsoba(186, 82.5, "muz");
    poleOsob[1] = new MalaOsoba(172, 63.0, "zena");
    poleOsob[2] = new MalaOsoba(105, 26.1, "dite");
    poleOsob[3] = new MalaOsoba(116, 80.5, "obezni trpaslik");

    Arrays.sort(poleOsob);

    for (int i = 0;  i < poleOsob.length;  i++) {
      System.out.println("[" + i + "] " + poleOsob[i]);
//      System.out.println("[" + i + "] " + poleOsob[i].toString());
    }
  }
}

package cz.heroult.pavel.bohatstvoKnihoven.kap01;



import java.util.*;

class Osoba implements Comparable<Osoba> {
  int vyska;
  double vaha;
  String popis;

  Osoba(int vyska, double vaha, String popis) {
    this.vyska = vyska;
    this.vaha = vaha;
    this.popis = popis;
  }

  public int compareTo(Osoba os) {
    return this.vyska - os.vyska;
  }

  public String toString() {
    return "vy = " + vyska + ", va = " + vaha + ", " + popis ;
  }
}

class KomparatorOsobyPodleVysky implements Comparator<Osoba> {
  public int compare(Osoba os1, Osoba os2) {
    return os1.vyska - os2.vyska;
  }
}

class KomparatorOsobyPodleVahy implements Comparator<Osoba> {
  public int compare(Osoba os1, Osoba os2) {
    return (int) (os1.vaha - os2.vaha);
  }
}

class KomparatorOsobyPodlePopisu implements Comparator<Osoba> {
  public int compare(Osoba os1, Osoba os2) {
    String s1 = os1.popis;
    String s2 = os2.popis;
    return s1.compareTo(s2);
  }
}

public class ArraysAbsolutniRazeni {
  static Osoba[] poleOsob;

  static void vypisOsoby() {
    for (int i = 0;  i < poleOsob.length;  i++)
      System.out.println("[" + i + "] " + poleOsob[i]);
  }

  public static void main(String[] args) {
    poleOsob = new Osoba[4];
    poleOsob[0] = new Osoba(186, 82.5, "muz");
    poleOsob[1] = new Osoba(172, 63.0, "zena");
    poleOsob[2] = new Osoba(105, 26.1, "dite");
    poleOsob[3] = new Osoba(116, 80.5, "obezni trpaslik");
/*
    System.out.println("Prirozene razeni");
    Arrays.sort(poleOsob);
    vypisOsoby();
*/
    System.out.println("Absolutni razeni podle vahy");
    Arrays.sort(poleOsob, new KomparatorOsobyPodleVahy());
    vypisOsoby();

    System.out.println("Absolutni razeni podle popisu");
    Arrays.sort(poleOsob, new KomparatorOsobyPodlePopisu());
    vypisOsoby();

    System.out.println("Prirozene razeni podle vysky sestupne");
    Arrays.sort(poleOsob, Collections.reverseOrder());
    vypisOsoby();

    System.out.println("Absolutni razeni podle vahy sestupne");
    Arrays.sort(poleOsob, Collections.reverseOrder(
                          new KomparatorOsobyPodleVahy()));
    vypisOsoby();
  }
}

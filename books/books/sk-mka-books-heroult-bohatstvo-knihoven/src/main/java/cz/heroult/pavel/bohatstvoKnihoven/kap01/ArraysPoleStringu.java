package cz.heroult.pavel.bohatstvoKnihoven.kap01;


import java.util.*;

public class ArraysPoleStringu {
  static String[] pole = {"af", "ae", "ac", "ab", "aa"};

  static void vypisPole() {
    for (int i = 0;  i < pole.length;  i++) {
      System.out.print("[" + i + "]=" + pole[i] + ", ");
    }
    System.out.println();
  }

  static void najdi(String hodnota) {
    int k = Arrays.binarySearch(pole, hodnota);
    System.out.print("[" + k + "]=" + hodnota);
    if (k < 0) {
      System.out.println(" --> [" + -(k + 1) + "]=" + hodnota);
    }
    else {
      System.out.println();
    }
  }

  public static void main(String[] args) {
    vypisPole();

    Arrays.sort(pole);
    vypisPole();

    najdi("ac");
    najdi("AA");
    najdi("ad");
    najdi("az");
  }  
}  

package cz.heroult.pavel.bohatstvoKnihoven.kap01;

import java.util.*;

public class ArraysPlneniSerazeniAVyhledavani {
  final static int POCET = 5;
  static int[] pole;

  static void vypisPole() {
    for (int i = 0;  i < pole.length;  i++) {
      System.out.print("[" + i + "]=" + pole[i] + ", ");
    }
    System.out.println();
  }

  static void najdi(int hodnota) {
    int k = Arrays.binarySearch(pole, hodnota);
    System.out.print("[" + k + "]=" + hodnota);
    if (k < 0) {
      System.out.println("\t[" + -(k + 1) + "]=" + hodnota);
    }
    else {
      System.out.println();
    }
  }

  public static void main(String[] args) {
    pole = new int[POCET];
    Arrays.fill(pole, 3);
    // prevedeni pole na retezec pomoci Arrays.toString()
    System.out.println(Arrays.toString(pole));
    vypisPole();
    najdi(3);

    for (int i = 0;  i < pole.length;  i++) {
      pole[i] = (POCET - i) * 2;
    }
    vypisPole();

    Arrays.sort(pole, 0, POCET / 2 + 1);
    vypisPole();

    Arrays.sort(pole);
    vypisPole();

    najdi(6);
    najdi(0);
    najdi(7);
    najdi(POCET * 2 + 1);
  }  
}  

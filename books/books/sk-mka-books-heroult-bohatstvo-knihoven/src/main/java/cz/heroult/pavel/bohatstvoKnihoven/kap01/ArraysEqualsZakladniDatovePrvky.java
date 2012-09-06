package cz.heroult.pavel.bohatstvoKnihoven.kap01;

import java.util.*;

public class ArraysEqualsZakladniDatovePrvky {
  final static int POCET = 10;

  public static void main(String[] args) {
    int[] pole1 = new int[POCET];
    int[] pole2 = new int[POCET * 2];
    int[] pole3 = new int[POCET];

    for (int i = 0;  i < pole1.length;  i++) {
      pole1[i] = i;
    }

    System.arraycopy(pole1, 0, pole2, 0, pole1.length);
    System.arraycopy(pole1, 0, pole3, 0, pole1.length);

    System.out.println("Pole 1 a 2 se rovnaji: "
             + Arrays.equals(pole1, pole2));

    System.out.println("Pole 1 a 3 se rovnaji: "
             + Arrays.equals(pole1, pole3));

    System.out.println("Zmena prvku pole3");
    pole3[3] = 123;

    System.out.println("Pole 1 a 3 se rovnaji: "
             + Arrays.equals(pole1, pole3));
  }
}

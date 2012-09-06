package cz.heroult.pavel.bohatstvoKnihoven.kap01;


import java.util.*;

public class ArraycopyZakladniDatovePrvky {
  final static int POCET = 8;

  public static void main(String[] args) {
    int[] pole1 = new int[POCET];
    int[] pole2 = new int[POCET * 2];

    for (int i = 0;  i < pole1.length;  i++) {
      pole1[i] = i;
    }

    // typicke pouziti
    System.arraycopy(pole1, 0, pole2, 0, pole1.length);
    System.out.println(Arrays.toString(pole2));

    // kopirovani jen casti pole
    System.arraycopy(pole1, 3, pole2, 10, 5);
    System.out.println(Arrays.toString(pole2));

    System.out.println("Zmena kopie neovlivni original");
    pole2[3] = 123;
    System.out.println(Arrays.toString(pole1));
    System.out.println(Arrays.toString(pole2));
  }
}

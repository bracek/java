package cz.heroult.pavel.bohatstvoKnihoven.kap01;


import java.util.*;

public class ArraysPraceSCastiPole {
  final static int POCET = 12;
  static int[] pole;

  public static void main(String[] args) {
    int dolni = POCET / 3;
    int horni = 2 * dolni;
    pole = new int[POCET];

    for (int i = 0;  i < pole.length;  i++) {
      pole[i] = i;
    }
    System.out.println(Arrays.toString(pole));

    Arrays.fill(pole, dolni, horni, 20);
    System.out.println(Arrays.toString(pole));

    for (int i = horni;  i < pole.length;  i++) {
      pole[i] = (int) (Math.random() * 30.0);
    }
    System.out.println(Arrays.toString(pole));

    Arrays.sort(pole, horni, pole.length);
    System.out.println(Arrays.toString(pole));

    Arrays.sort(pole);
    System.out.println(Arrays.toString(pole));
  }
}  

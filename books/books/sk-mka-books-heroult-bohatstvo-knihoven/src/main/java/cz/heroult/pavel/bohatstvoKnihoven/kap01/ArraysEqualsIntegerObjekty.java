package cz.heroult.pavel.bohatstvoKnihoven.kap01;

import java.util.*;

public class ArraysEqualsIntegerObjekty {
  final static int POCET = 5;

  public static void main(String[] args) {
    Integer[] pole1 = new Integer[POCET];
    Integer[] pole4 = new Integer[POCET];

    for (int i = 0;  i < pole1.length;  i++) {
      pole1[i] = new Integer(i);
      pole4[i] = new Integer(i);
    }
    
    System.out.println("Pole 1 a 4 se rovnaji: "
             + Arrays.equals(pole1, pole4));

    System.out.println("Novy prvek pole4 se stejnou hodnotou");
    pole4[3] = new Integer(3);

    System.out.println("Pole 1 a 4 se rovnaji: "
             + Arrays.equals(pole1, pole4));

    System.out.println("Zmena hodnoty prvku pole4");
    pole4[3] = new Integer(123);

    System.out.println("Pole 1 a 4 se rovnaji: "
             + Arrays.equals(pole1, pole4));
  }
}

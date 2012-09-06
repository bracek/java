package cz.heroult.pavel.bohatstvoKnihoven.kap01;


import java.util.*;

class Cele {
  int h;

  Cele(int h) { this.h = h; }
  public String toString() { return "" + h; }
}

public class ArraycopyObecneObjekty {
  final static int POCET = 5;

  public static void main(String[] args) {
    Cele[] pole1 = new Cele[POCET];
    Cele[] pole2 = new Cele[POCET * 2];

    for (int i = 0;  i < pole1.length;  i++) {
      pole1[i] = new Cele(i);
    }
    
    // typicke pouziti
    System.arraycopy(pole1, 0, pole2, 0, pole1.length);
    System.out.println(Arrays.toString(pole2));

    // kopirovani jen casti pole
    System.arraycopy(pole1, 2, pole2, 6, 3);
    // vypis pole pomoci asList()
    System.out.println(Arrays.asList(pole2));

    System.out.println("Zmena kopie zmeni i original");
    pole2[3].h = 123;
    System.out.println(Arrays.toString(pole1));
    System.out.println(Arrays.toString(pole2));
  }
}

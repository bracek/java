package cz.heroult.pavel.bohatstvoKnihoven.kap01;

import java.util.*;

class MyCele {

    int h;

    MyCele(int h) {
        this.h = h;
    }

    public String toString() {
        return "" + h;
    }
}

public class ArraysEqualsObecneObjekty {

    final static int POCET = 5;

    public static void main(String[] args) {
        MyCele[] pole1 = new MyCele[POCET];
        MyCele[] pole2 = new MyCele[POCET * 2];
        MyCele[] pole3 = new MyCele[POCET];
        MyCele[] pole4 = new MyCele[POCET];

        for (int i = 0; i < pole1.length; i++) {
            pole1[i] = new MyCele(i);
            pole4[i] = new MyCele(i);
        }

        System.arraycopy(pole1, 0, pole2, 0, pole1.length);
        System.arraycopy(pole1, 0, pole3, 0, pole1.length);

        System.out.println("Pole 1 a 2 se rovnaji: " + Arrays.equals(pole1, pole2));

        System.out.println("Pole 1 a 3 se rovnaji: " + Arrays.equals(pole1, pole3));

        System.out.println("Zmena prvku pole3");
        pole3[3].h = 123;

        System.out.println("Pole 1 a 3 se rovnaji: " + Arrays.equals(pole1, pole3));

        System.out.println("Pole 1 a 4 se rovnaji: " + Arrays.equals(pole1, pole4));
    }
}

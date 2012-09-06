package cz.heroult.pavel.bohatstvoKnihoven.kap01;


import java.util.*;

class Person implements Comparable<Person> {

    int vyska;
    double vaha;
    String popis;

    Person(int vyska, double vaha, String popis) {
        this.vyska = vyska;
        this.vaha = vaha;
        this.popis = popis;
    }

    public int compareTo(Person os) {
        return this.vyska - os.vyska;
    }

    public String toString() {
        return "vy = " + vyska + ", va = " + vaha + ", " + popis;
    }
}

class KomparatorPersonPodleVysky implements Comparator<Person> {

    public int compare(Person os1, Person os2) {
        return os1.vyska - os2.vyska;
    }
}

class KomparatorPersonPodleVahy implements Comparator<Person> {

    public int compare(Person os1, Person os2) {
        return (int) (os1.vaha - os2.vaha);
    }
}

class KomparatorPersonPodlePopisu implements Comparator<Person> {

    public int compare(Person os1, Person os2) {
        String s1 = os1.popis;
        String s2 = os2.popis;
        return s1.compareTo(s2);
    }
}

public class ArraysVyhledavaniObecnychObjektu {

    static Person[] poleOsob;

    static void vypisOsob() {
        for (int i = 0; i < poleOsob.length; i++) {
            System.out.println("[" + i + "] " + poleOsob[i].toString());
        }
    }

    static void najdi(double vaha) {
        Person os = new Person(0, vaha, "nedulezite");
        int k = Arrays.binarySearch(poleOsob, os,
                new KomparatorPersonPodleVahy());
        System.out.print("[" + k + "]=" + vaha);
        if (k < 0) {
            System.out.println(" --> [" + -(k + 1) + "]=" + vaha);
        } else {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        poleOsob = new Person[4];
        poleOsob[0] = new Person(186, 82.5, "muz");
        poleOsob[1] = new Person(172, 63.0, "zena");
        poleOsob[2] = new Person(105, 26.1, "dite");
        poleOsob[3] = new Person(116, 80.5, "obezni trpaslik");

        System.out.println("Absolutni razeni podle vahy");
        Arrays.sort(poleOsob, new KomparatorPersonPodleVahy());
        vypisOsob();

        najdi(60.0);
        najdi(63.0);
        najdi(20.0);
        najdi(99.0);
    }
}

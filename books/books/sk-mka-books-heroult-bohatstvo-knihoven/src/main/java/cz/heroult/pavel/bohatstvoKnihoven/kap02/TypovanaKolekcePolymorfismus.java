package cz.heroult.pavel.bohatstvoKnihoven.kap02;

import java.util.*;

interface T {

    public void tiskni();
}

class A implements T {

    public void tiskni() {
        System.out.println("A");
    }
}

class B extends A {

    public void tiskni() {
        System.out.println("B potomek A");
    }
}

public class TypovanaKolekcePolymorfismus {

    public static void main(String[] args) {
        ArrayList<T> ar = new ArrayList<T>();
        ar.add(new A());
        ar.add(new B());
//    ar.add(new Integer(3));          // chyba pri prekladu          
        tisk(ar);
    }

    public static void tisk(ArrayList<? extends T> ar) {
        for (int i = 0; i < ar.size(); i++) {
            ar.get(i).tiskni();
        }
    }
}

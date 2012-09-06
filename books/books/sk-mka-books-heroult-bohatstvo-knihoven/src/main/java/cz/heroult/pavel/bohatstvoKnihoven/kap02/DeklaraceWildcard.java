package cz.heroult.pavel.bohatstvoKnihoven.kap02;

import java.util.*;

interface E {

    public void tiskni();
}

class ABS {

    public void tiskni() {
        System.out.println("A");
    }
}

public class DeklaraceWildcard {

    public static void main(String[] args) {
//        ArrayList<?> ar1 = new ArrayList<?>();  
//        ArrayList<? extends T> ar2 = new ArrayList<? extends T>();
//        ArrayList<? extends A> ar3 = new ArrayList<? extends A>();
    }
}


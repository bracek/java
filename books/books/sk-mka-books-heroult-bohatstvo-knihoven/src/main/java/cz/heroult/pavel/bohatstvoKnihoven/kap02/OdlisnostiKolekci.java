package cz.heroult.pavel.bohatstvoKnihoven.kap02;

import java.util.*;

public class OdlisnostiKolekci {

    public static void main(String[] args) {
        ArrayList<String> ar = new ArrayList<String>();
        ar.add("prvni");
        ar.add("druhy");
        ar.add("prvni");
        System.out.println("ArrayList: " + ar);

        HashSet<String> hs = new HashSet<String>();
        hs.add("prvni");
        hs.add("druhy");
        hs.add("prvni");
        System.out.println("HashSet:   " + hs);

        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("prvni", "objekt");
        hm.put("druhy", "objekt");
        hm.put("prvni", "pivo");
        System.out.println("HashMap:   " + hm);
    }
}

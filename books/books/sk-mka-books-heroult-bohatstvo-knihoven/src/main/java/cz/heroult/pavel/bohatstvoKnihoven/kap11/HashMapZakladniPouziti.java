package cz.heroult.pavel.bohatstvoKnihoven.kap11;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                   Java -- bohatství knihoven                //
//                II. opravené a rozšíøené vydání              //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       Cti_me.txt                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2006                      // 
//                                                             //
/////////////////////////////////////////////////////////////////
import java.util.*;

class HashVaha {

    double vaha;

    HashVaha(double vaha) {
        this.vaha = vaha;
    }

    @Override
    public String toString() {
        return "" + vaha;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof HashVaha == false) {
            return false;
        }
        boolean stejnaVaha = (vaha == ((HashVaha) o).vaha);
        return stejnaVaha;
    }

    @Override
    public int hashCode() {
        return (int) vaha;
    }
}

public class HashMapZakladniPouziti {

    public static void main(String[] args) {
        HashMap<String, Vaha> hm = new HashMap<String, Vaha>();
        System.out.println("Mapa je prazdna: " + hm.isEmpty() + " a obsahuje prvku: " + hm.size());
        hm.put("Pavel", new Vaha(85));
        hm.put("Venca", new Vaha(105));
        hm.put("Karel", new Vaha(85));
        System.out.println("Mapa je prazdna: " + hm.isEmpty() + " a obsahuje prvku: " + hm.size());
        System.out.println("Mapa: " + hm);
        hm.remove("Karel");
        System.out.println("Mapa: " + hm);
        hm.put("Karel", new Vaha(70));
        System.out.println("Mapa: " + hm);
        Vaha v = hm.get("Venca");
        System.out.println("Venca vazi: " + v);
        if (hm.containsKey("Pavel")) {
            System.out.println("Pavel vazi: " + hm.get("Pavel"));
        }
        if (hm.containsValue(new Vaha(105)) == true) {
            System.out.println("Nekdo vazi 105 kg");
        }

        hm.get("Pavel").vaha += 10;  // Pavel ztlousnul
        System.out.println("Lidi: " + hm.keySet());
//    ArrayList ar = (ArrayList) hm.values();  // nelze
//    HashSet hs = (HashSet) hm.values();      // nelze
        Collection<Vaha> col = hm.values();
        Iterator<Vaha> it = col.iterator();
        it.next().vaha += 7;       // nekdo ztlousnul
        System.out.println("Vahy: " + col);
        System.out.println("Mapa: " + hm);

        double[] poleVah = new double[col.size()];
        int i = 0;
        for (Vaha va : col) {
            poleVah[i] = va.vaha;
            i++;
        }
        System.out.print("Pole vah: ");
        System.out.println(Arrays.toString(poleVah));
    }
}

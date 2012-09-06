package cz.heroult.pavel.bohatstvoKnihoven.kap14;

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

class Osoba {
    // zakladni stavove atributy

    protected boolean muz;
    protected int vyska;
    protected double vaha;
    protected String jmeno;
    // bitovy obraz vahy pro hashCode() a equals()
    // odvozeny atribut
    protected long longVaha;
    private static Random r = new Random();

    Osoba() {
        this.muz = r.nextBoolean();
        // vyska <170; 200>
        this.vyska = 170 + r.nextInt(31);
        // vaha <50; 100)
        this.vaha = 50 + 50 * r.nextDouble();
        this.longVaha = Double.doubleToLongBits(this.vaha);
        byte[] b = new byte[5];
        for (int i = 0; i < 5; i++) {
            b[i] = (byte) ((r.nextInt(26) + (byte) 'a'));
        }
        jmeno = new String(b);
    }

    public String toString() {
        return jmeno + ", " + (muz ? "muz " : "zena") + ", " + vyska + ", " + vaha;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Osoba == false) {
            return false;
        }
        Osoba os = (Osoba) o;
        boolean bMuz = this.muz == os.muz;
        boolean bVyska = this.vyska == os.vyska;
        boolean bVaha = this.longVaha == os.longVaha;
        boolean bJmeno = this.jmeno.equals(os.jmeno);
        return bMuz && bVyska && bVaha && bJmeno;
    }
}

class NevhodnaOsoba extends Osoba {

    public int hashCode() {
        return vyska;
    }
}

class PrijatelnaOsoba extends Osoba {

    public int hashCode() {
        return (int) (vyska * vaha);
    }
}

class PerfektniOsoba extends Osoba {

    public int hashCode() {
        int vysledek = 17;
        int pom;
        pom = this.muz ? 0 : 1;
        vysledek = 37 * vysledek + pom;
        pom = this.vyska;
        vysledek = 37 * vysledek + pom;
        long l = Double.doubleToLongBits(this.vaha);
        pom = (int) (l ^ (l >>> 32));
        vysledek = 37 * vysledek + pom;
        pom = this.jmeno.hashCode();
        vysledek = 37 * vysledek + pom;
        return vysledek;
    }
}

class NemennaPerfektniOsoba extends PerfektniOsoba {

    protected int hashKod;

    NemennaPerfektniOsoba() {
        super();
        hashKod = super.hashCode();
    }

    @Override
    public int hashCode() {
        return hashKod;
    }
}

public class TypyHashCode {

    static int pocet = 10;

    public static void main(String[] args) {
        if (args.length > 0) {
            pocet = Integer.parseInt(args[0]);
        }

        Osoba[] pole = new Osoba[pocet];

        for (int i = 0; i < pocet; i++) {
//      pole[i] = new NevhodnaOsoba();
//      pole[i] = new PrijatelnaOsoba();
            pole[i] = new PerfektniOsoba();
//      pole[i] = new NemennaPerfektniOsoba();
//      System.out.println("" + i + ": " + pole[i]);
        }

        System.out.println(pole[0].getClass().getName());
        long zac = System.currentTimeMillis();
        HashSet<Osoba> mnOsob = new HashSet<Osoba>(pocet);
        for (int i = 0; i < pocet; i++) {
            mnOsob.add(pole[i]);
        }
        long kon = System.currentTimeMillis();
        System.out.print("Vlozeni: " + mnOsob.size() + " (" + pocet + ") ");
        System.out.println("cas = " + (kon - zac));

        zac = System.currentTimeMillis();
        int n = 0;
        for (int i = pocet - 1; i >= 0; i--) {
            if (mnOsob.contains(pole[i]) == true) {
                n++;
//        System.out.println("" + i + ": " + pole[i]);
            }
        }
        kon = System.currentTimeMillis();
        System.out.print("Pristup: " + n + " (" + pocet + ") ");
        System.out.println("cas = " + (kon - zac));
    }
}

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

class Mosoba {
    // zakladni stavove atributy

    protected boolean muz;
    protected int vyska;
    protected double vaha;
    protected String jmeno;
    // bitovy obraz vahy pro hashCode() a equals()
    // odvozeny atribut
    protected long longVaha;
    private static Random r = new Random(1234);

    Mosoba() {
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

    @Override
    public String toString() {
        return jmeno + ", " + (muz ? "muz " : "zena") + ", " + vyska + ", " + vaha;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Mosoba == false) {
            return false;
        }
        Mosoba os = (Mosoba) o;
        boolean bMuz = this.muz == os.muz;
        boolean bVyska = this.vyska == os.vyska;
        boolean bVaha = this.longVaha == os.longVaha;
        boolean bJmeno = this.jmeno.equals(os.jmeno);
        return bMuz && bVyska && bVaha && bJmeno;
    }
}

class ZlaOsoba extends Mosoba {

    public int hashCode() {
        return vyska;
    }
}

class UjdeOsoba extends Mosoba {

    public int hashCode() {
        return (int) (vyska * vaha);
    }
}

class PerfectOsoba extends Mosoba {

    @Override
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

class NemennaPerfectOsoba extends PerfectOsoba {

    protected int hashKod;

    NemennaPerfectOsoba() {
        super();
        hashKod = super.hashCode();
    }

    @Override
    public int hashCode() {
        return hashKod;
    }
}

public class RozlozeniHashCode {

    static int pocet = 100000;

    public static void main(String[] args) {
        if (args.length > 0) {
            pocet = Integer.parseInt(args[0]);
        }

        ArrayList<Integer> pole = new ArrayList<Integer>(pocet);
        Mosoba o;
        for (int i = 0; i < pocet; i++) {
//      o = new ZlaOsoba();
            o = new UjdeOsoba();
//      o = new PerfectOsoba();
            pole.add(new Integer(o.hashCode()));
        }
        Set<Integer> rozdilne = new HashSet<Integer>(pole);
        System.out.println("Rozdilnych: " + rozdilne.size());

        int shodnych = pocet - rozdilne.size();
        System.out.println("Shodnych:   " + shodnych);
        if (shodnych < 100) {
            Collections.sort(pole);
            for (int i = 0; i < pocet - 1; i++) {
                if (pole.get(i).equals(pole.get(i + 1)) == true) {
                    System.out.print(pole.get(i) + ", ");
                }
            }
        }
    }
}

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
import java.io.*;
import java.text.*;

class OsobaProKolekce implements Comparable<OsobaProKolekce> {
    // zakladni stavove atributy
    // nemenitelne

    private final String krestni,  prijmeni;
    // menitelne
    private int vyska;
    private double vaha;
    // odvozeny atribut - bitovy obraz vahy pro hashCode() a equals()
    protected long longVaha;
    // pomocny atribut pro ceske razeni
    private static final Collator COL =
            Collator.getInstance(new Locale("cs", "CZ"));

    public OsobaProKolekce(String krestni, String prijmeni,
            int vyska, double vaha) {
        if (krestni == null || prijmeni == null) {
            throw new NullPointerException();
        }
        this.krestni = krestni;
        this.prijmeni = prijmeni;
        setVyska(vyska);
        setVaha(vaha);
    }

    public String getKrestni() {
        return krestni;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setVyska(int vyska) {
        if (vyska <= 0) {
            throw new IllegalArgumentException("vyska=" + vyska);
        }
        this.vyska = vyska;
    }

    public void setVaha(double vaha) {
        if (vaha <= 0) {
            throw new IllegalArgumentException("vaha=" + vaha);
        }
        this.vaha = vaha;
        this.longVaha = Double.doubleToLongBits(this.vaha);
    }

    public int getVyska() {
        return vyska;
    }

    public double getVaha() {
        return vaha;
    }

    @Override
    public String toString() {
        return prijmeni + " " + krestni + ", " + vyska + ", " + vaha + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if ((o instanceof OsobaProKolekce) == false) {
            return false;
        }
        OsobaProKolekce opk = (OsobaProKolekce) o;
        boolean stejneKrestni = krestni.equals(opk.krestni);
        boolean stejnePrijmeni = prijmeni.equals(opk.prijmeni);
        boolean stejnaVyska = vyska == opk.vyska;
        boolean stejnaVaha = longVaha == opk.longVaha;

        return stejneKrestni && stejnePrijmeni && stejnaVyska && stejnaVaha;
    }

    @Override
    public int hashCode() {
        int vysledek = 17;
        vysledek = 37 * vysledek + krestni.hashCode();
        vysledek = 37 * vysledek + prijmeni.hashCode();
        vysledek = 37 * vysledek + vyska;
        int pom = (int) (longVaha ^ (longVaha >>> 32));
        vysledek = 37 * vysledek + pom;
        return vysledek;
    }

    // prirozene razeni
    public int compareTo(OsobaProKolekce opk) {
        int tmpP = COL.compare(this.prijmeni, opk.prijmeni);
        int tmpK = COL.compare(this.krestni, opk.krestni);
        return (tmpP == 0 ? tmpK : tmpP);
    }

    // komparatory pro absolutni razeni
    public static final Comparator<OsobaProKolekce> PODLE_VYSKY =
            new Comparator<OsobaProKolekce>() {

                public int compare(OsobaProKolekce o1, OsobaProKolekce o2) {
                    return o1.vyska - o2.vyska;
                }
            };
    public static final Comparator<OsobaProKolekce> PODLE_VAHY =
            new Comparator<OsobaProKolekce>() {

                public int compare(OsobaProKolekce o1, OsobaProKolekce o2) {
                    if (o1.longVaha == o2.longVaha) {
                        return 0;
                    }
                    if (o1.vaha > o2.vaha) {
                        return +1;
                    } else {
                        return -1;
                    }
//      return (int) (o1.vaha - o2.vaha);
                }
            };
    public static final Comparator<OsobaProKolekce> PODLE_JMENA =
            new Comparator<OsobaProKolekce>() {

                public int compare(OsobaProKolekce o1, OsobaProKolekce o2) {
                    int tmpP = COL.compare(o1.prijmeni, o2.prijmeni);
                    int tmpK = COL.compare(o1.krestni, o2.krestni);
                    return (tmpP == 0 ? tmpK : tmpP);
                }
            };
}

public class TestOsobaProKolekce {

    public static void main(String[] argv) throws IOException {
        OutputStreamWriter o = new OutputStreamWriter(System.out, "Cp852");
        PrintWriter p = new PrintWriter(o);

        ArrayList<OsobaProKolekce> a = new ArrayList<OsobaProKolekce>();
        a.add(new OsobaProKolekce("Karel", "Chytrý", 160, 60.));
        a.add(new OsobaProKolekce("Karel", "Ètvrtý", 170, 70.));
        a.add(new OsobaProKolekce("Karel", "Ctvrtý", 180, 80.));
        a.add(new OsobaProKolekce("Josef", "Ètvrtý", 190, 65.));
        a.add(new OsobaProKolekce("Kárel", "Ètvrtý", 200, 90.));
        p.println(a);

        Collections.sort(a);
        p.println("Podle jména (pøirozené øazení)\n" + a);
        Collections.sort(a, OsobaProKolekce.PODLE_VYSKY);
        p.println("Podle výšky\n" + a);
        Collections.sort(a, OsobaProKolekce.PODLE_VAHY);
        p.println("Podle váhy\n" + a);
        Collections.sort(a, OsobaProKolekce.PODLE_JMENA);
        p.println("Podle jména\n" + a);
        p.flush();

        a.add(new OsobaProKolekce("Jan", "Záporný", 0, -5.));
    }
}



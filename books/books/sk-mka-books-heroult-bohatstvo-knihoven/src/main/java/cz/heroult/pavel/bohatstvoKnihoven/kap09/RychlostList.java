package cz.heroult.pavel.bohatstvoKnihoven.kap09;

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

class TypyTestu {

    public static final int POCET_RADU = 3;
    public static final boolean DEBUG = false;   // pro ladeni
    public static int pocetPrvku = 1000;
    private List<Integer> li;
    private String typListu;

    TypyTestu(List<Integer> li) {
        this.li = li;
        this.typListu = li.getClass().getName();
        typListu = typListu.substring(typListu.lastIndexOf(".") + 1);
    }

    private void tisk() {
        if (DEBUG == true) {
            System.out.print(li + " ");
        }
    }

    private abstract class Akce {

        String typ;

        Akce(String typ) {
            this.typ = typ;
        }

        abstract void akce();
    }
    private Akce[] poleAkci = {
        new Akce("naplneni") {

            void akce() {
                for (int i = 0; i < pocetPrvku; i++) {
                    li.add(new Integer(i));
                }
                tisk();
            }
        },
        new Akce("pruchodIndexaci") {

            void akce() {
                int vel = li.size();
                for (int i = 0; i < vel; i++) {
                    li.get(i);
                }
            }
        },
        new Akce("pruchodIteratorem") {

            void akce() {
                for (Iterator<Integer> it = li.iterator(); it.hasNext();) {
                    it.next();
                }
            }
        },
        new Akce("vypusteniPolovinyIndexaciZezadu") {

            void akce() {
                int vel = li.size();
                for (int i = vel - 2; i >= 0; i -= 2) {
                    li.remove(i);
                }
                tisk();
            }
        },
        new Akce("vlozeniPolovinyIndexaci") {

            void akce() {
                int vel = li.size() * 2;
                for (int i = 0; i < vel; i += 2) {
                    li.add(i, new Integer(i));
                }
                tisk();
            }
        },
        new Akce("vypusteniPolovinyIndexaciZepredu") {

            void akce() {
                for (int i = 0; i < li.size(); i++) {
                    li.remove(i);
                }
                tisk();
            }
        },
        new Akce("clearANaplneni") {

            void akce() {
                li.clear();
                for (int i = 0; i < pocetPrvku; i++) {
                    li.add(new Integer(i));
                }
                tisk();
            }
        },
        new Akce("vypusteniPolovinyIteratorem") {

            void akce() {
                for (Iterator<Integer> it = li.iterator(); it.hasNext();) {
                    it.next();
                    it.remove();
                    it.next();           // spinavy trik - nepouzita hasNext()
                }
                tisk();
            }
        },
        new Akce("clearANaplneni") {

            void akce() {
                li.clear();
                for (int i = 0; i < pocetPrvku; i++) {
                    li.add(new Integer(i));
                }
                tisk();
            }
        },
        new Akce("serazeniSestupne") {

            void akce() {
                Collections.sort(li, Collections.reverseOrder());
                tisk();
            }
        },
        new Akce("serazeniVzestupne") {

            void akce() {
                Collections.sort(li);
                tisk();
            }
        },
        new Akce("zamichani") {

            void akce() {
                Collections.shuffle(li);
                tisk();
            }
        },
        new Akce("serazeniVzestupne") {

            void akce() {
                Collections.sort(li);
                tisk();
            }
        },
        new Akce("binarniHledaniOdzadu") {

            void akce() {
                int vel = li.size() - 1;
                for (int i = 0; i <= vel; i++) {
                    Collections.binarySearch(li, new Integer(vel - i));
                }
            }
        },
        new Akce("otoceniPoradi") {

            void akce() {
                Collections.reverse(li);
                tisk();
            }
        },
        new Akce("hledaniOdzadu") {

            void akce() {
                int vel = li.size();
                for (int i = 0; i < vel; i++) {
                    li.indexOf(new Integer(i));
                }
            }
        },
        new Akce("zamichani") {

            void akce() {
                Collections.shuffle(li);
                tisk();
            }
        },
        new Akce("hledani") {

            void akce() {
                int vel = li.size();
                for (int i = 0; i < vel; i++) {
                    li.indexOf(new Integer(i));
                }
            }
        }
    };

    void testy() {
        System.out.println(typListu);
        for (int i = 0; i < poleAkci.length; i++) {
            long zac = System.currentTimeMillis();
            poleAkci[i].akce();
            long kon = System.currentTimeMillis();
            System.out.println(poleAkci[i].typ + ": " + (kon - zac));
        }
        System.out.println();
        li.clear();
        li = null;
        System.gc();
    }
}

public class RychlostList {

    public static void main(String[] args) {
        for (int i = 1; i <= TypyTestu.POCET_RADU; i++) {
            System.out.println("Velikost pole: " + TypyTestu.pocetPrvku);
            new TypyTestu(new ArrayList<Integer>()).testy();
            new TypyTestu(new ArrayList<Integer>(TypyTestu.pocetPrvku)).testy();
            new TypyTestu(new LinkedList<Integer>()).testy();
            TypyTestu.pocetPrvku *= 10;
        }
    }
}

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

class MyTypyTestu {

    public static final int POCET_RADU = 3;
    public static final boolean DEBUG = false;   // pro ladeni
    public static int pocet_prvku = 1000;
    private Integer[] pole;
    private List<Integer> li;

    MyTypyTestu(Integer[] pole) {
        this.pole = pole;
    }

    private void tisk() {
        if (DEBUG == true) {
            System.out.println(Arrays.toString(pole));
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
                for (int i = 0; i < pocet_prvku; i++) {
                    pole[i] = new Integer(i);
                }
                tisk();
            }
        },
        new Akce("pruchodIndexaci") {

            void akce() {
                int vel = pole.length;
                Integer tmp;
                for (int i = 0; i < vel; i++) {
                    tmp = pole[i];
                }
            }
        },
        new Akce("pruchodIteratorem") {

            void akce() {
                System.out.print(" --- ");
            }
        },
        new Akce("vypusteniPolovinyIndexaciZezadu") {

            void akce() {
                System.out.print(" --- ");
            }
        },
        new Akce("vlozeniPolovinyIndexaci") {

            void akce() {
                System.out.print(" --- ");
            }
        },
        new Akce("vypusteniPolovinyIndexaciZepredu") {

            void akce() {
                System.out.print(" --- ");
            }
        },
        new Akce("clearANaplneni") {

            void akce() {
                System.out.print(" --- ");
            }
        },
        new Akce("vypusteniPolovinyIteratorem") {

            void akce() {
                System.out.print(" --- ");
            }
        },
        new Akce("clearANaplneni") {

            void akce() {
                System.out.print(" --- ");
            }
        },
        new Akce("serazeniSestupne") {

            void akce() {
                Arrays.sort(pole, Collections.reverseOrder());
                tisk();
            }
        },
        new Akce("serazeniVzestupne") {

            void akce() {
                Arrays.sort(pole);
                tisk();
            }
        },
        new Akce("zamichaniPresArrayList") {

            void akce() {
                li = new ArrayList<Integer>(Arrays.asList(pole));
                Collections.shuffle(li);
                pole = li.toArray(new Integer[0]);
                tisk();
            }
        },
        new Akce("serazeniVzestupne") {

            void akce() {
                Arrays.sort(pole);
                tisk();
            }
        },
        new Akce("binarniHledaniOdzadu") {

            void akce() {
                int vel = pole.length - 1;
                for (int i = 0; i <= vel; i++) {
                    Arrays.binarySearch(pole, new Integer(vel - i));
                }
            }
        },
        new Akce("otoceniPoradi") {

            void akce() {
                int vel = pole.length;
                Integer tmp;
                for (int i = 0; i < vel / 2; i++) {
                    tmp = pole[i];
                    pole[i] = pole[vel - 1 - i];
                    pole[vel - 1 - i] = tmp;
                }
                tisk();
            }
        },
        new Akce("hledaniOdzadu") {

            void akce() {
                System.out.print(" --- ");
            }
        },
        new Akce("zamichani") {

            void akce() {
                System.out.print(" --- ");
            }
        },
        new Akce("hledani") {

            void akce() {
                System.out.print(" --- ");
            }
        },
        new Akce("pripravaArrayList") {

            void akce() {
                li = new ArrayList<Integer>(Arrays.asList(pole));
            }
        },
        new Akce("konverzeArrayListNaPole") {

            void akce() {
                pole = li.toArray(new Integer[0]);
                tisk();
            }
        }
    };

    void testy() {
        System.out.println("poleInteger");
        for (int i = 0; i < poleAkci.length; i++) {
            long zac = System.currentTimeMillis();
            poleAkci[i].akce();
            long kon = System.currentTimeMillis();
            System.out.println(poleAkci[i].typ + ": " + (kon - zac));
        }
        System.out.println();
        pole = null;
        System.gc();
    }
}

public class RychlostPole {

    public static void main(String[] args) {
        for (int i = 1; i <= MyTypyTestu.POCET_RADU; i++) {
            System.out.println("Velikost pole: " + MyTypyTestu.pocet_prvku);
            new MyTypyTestu(new Integer[MyTypyTestu.pocet_prvku]).testy();
            MyTypyTestu.pocet_prvku *= 10;
        }
    }
}

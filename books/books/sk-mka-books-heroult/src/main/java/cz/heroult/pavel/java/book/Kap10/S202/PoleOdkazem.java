package cz.heroult.pavel.java.book.Kap10.S202;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                  Uèebnice jazyka Java                       //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       CTI_ME.TXT                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2000                      // 
//                                                             //
/////////////////////////////////////////////////////////////////
class PraceSPolem {

    public void nastavPole(final int[] p, final int h) {
        for (int i = 0; i < p.length; i++) {
            p[i] = h + i;
        }
//  h++;          // chyba
//  p = null;     // chyba
    }

    public static void tiskniPole(int[] p) {
        for (int i = 0; i < p.length; i++) {
            System.out.print("[" + i + "] = " + p[i] + ", ");
        }
        System.out.print("\b\b \n");
    }

    public void nastavPrvek(int prvek, int h) {
        prvek = h;
    }
}

public class PoleOdkazem {

    public static void main(String[] args) {
        PraceSPolem obj = new PraceSPolem();
        int[] pole = {5, 4, 3, 2, 1};
        PraceSPolem.tiskniPole(pole);
        obj.nastavPole(pole, 3);
        PraceSPolem.tiskniPole(pole);
        obj.nastavPrvek(pole[0], 5);
        PraceSPolem.tiskniPole(pole);
    }
}


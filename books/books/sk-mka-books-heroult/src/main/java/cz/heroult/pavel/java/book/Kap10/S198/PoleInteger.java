package cz.heroult.pavel.java.book.Kap10.S198;

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
import java.util.Arrays;

public class PoleInteger {

    public static void main(String[] args) {
        Integer[] pole = new Integer[10];
        for (int i = 0; i < pole.length; i++) {
            pole[i] = new Integer(i + 1);
        }
        System.out.println(Arrays.toString(pole));
        Integer objI = pole[2];
        int i = objI.intValue();
        int j = pole[2];
        System.out.println("i = " + i + ", j = " + j);
        pole[3] = new Integer(78);
        pole[4] = 34;
        System.out.println(Arrays.toString(pole));
    }
}

package cz.heroult.pavel.java.book.Kap06.S125;

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
import java.util.Scanner;

public class ScannerStatickaPromenna {

    static Scanner scanner = new Scanner(System.in);

    public static int nactiCislo(int poradi) {
        System.out.print("Zadej " + poradi + ". cislo: ");
        int i = scanner.nextInt();
        return i;
    }

    public static void main(String[] args) {
        int i1 = nactiCislo(1);
        int i2 = nactiCislo(2);
        int i3 = nactiCislo(3);
        System.out.println("Soucet je: " + (i1 + i2 + i3));
    }
}
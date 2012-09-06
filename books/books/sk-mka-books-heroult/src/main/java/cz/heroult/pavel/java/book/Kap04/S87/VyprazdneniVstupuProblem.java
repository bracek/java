package cz.heroult.pavel.java.book.Kap04.S87;

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

import java.util.*;

public class VyprazdneniVstupuProblem {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    System.out.print("Zadej prvni cele cislo: ");
    int i1 = sc.nextInt();
    System.out.println("Zadal jsi: " + i1);

    System.out.print("Zadej retezec: ");
    String s = sc.next();
    System.out.println("Zadal jsi: " + s);
    
    System.out.print("Zadej znak: ");
    char c = sc.nextLine().charAt(0);
    System.out.println("Zadal jsi: " + c);
    
    System.out.print("Zadej druhe cele cislo: ");
    int i2 = sc.nextInt();
    System.out.println("Zadal jsi: " + i2);
  }
}
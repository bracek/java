package cz.heroult.pavel.java.book.Kap05.S104;

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

public class ForBreak {
  public static void main(String[] args) {
    System.out.print("Zacatek ");
 odskok:
    {
      for (int i = 1;  i < 10;  i++) {
        if (i == 5)
           break odskok;  
//        break;
        System.out.print(i + " ");
      }

      System.out.print("Stred ");
    }

    System.out.println("Konec");
  }
}

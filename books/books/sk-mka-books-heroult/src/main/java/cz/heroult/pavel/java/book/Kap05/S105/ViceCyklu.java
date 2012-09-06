package cz.heroult.pavel.java.book.Kap05.S105;

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

public class ViceCyklu {
  public static void main(String[] args) {
    int[] a = new int[10];
    int[] b = new int[10];
    int[] x = new int[10];

    for (int i = 0;  i < 10;  i++) {
      a[i] = i + 1;
      b[i] = i + 1;
      x[i] = i + 1;    // správný výpoèet
//      x[i] = i;        // nulový dìlitel
    }

chyba:
    {
      for (int i = 0;  i < 10;  i++) {
        for (int j = 0;  j < 10;  j++) {
          for (int k = 0;  k < 10;  k++) {
            if (x[k] == 0)
              break chyba;
            a[i] = a[i] + b[j] / x[k];
          }
        }
      }
      System.out.println("Dobre");
      // a napø. tisk výsledkù a return
      for (int i = 0;  i < 10;  i++) {
        System.out.print(a[i] + "  ");
      }
      System.exit(1);

     }
     System.out.println("Nulovy delitel");
  }
}

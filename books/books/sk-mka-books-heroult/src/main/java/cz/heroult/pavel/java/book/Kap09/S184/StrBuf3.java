package cz.heroult.pavel.java.book.Kap09.S184;

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

public class StrBuf3 {
  public static void main(String[] args) {
    StringBuffer b;

    b = new StringBuffer("Ahoj ");

    b.append(true);
    System.out.println(b);   // Ahoj true

    b.append(7);
    System.out.println(b);   // Ahoj true7

    b.delete(5, 9);
    System.out.println(b);   // Ahoj 7

    b.deleteCharAt(0);
    System.out.println(b);   // hoj 7

    b.insert(0, 3.14);
    b.insert(1, "HOJ");
    System.out.println(b);   // 3HOJ.14hoj 7

    b.replace(0, 5, "3,");
    System.out.println(b);   // 3,14hoj 7

    char c = b.charAt(0);
    System.out.println(c);   // 3

    b.setCharAt(1, '!');
    System.out.println(b);   // 3!14hoj 7
  }
}

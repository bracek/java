package cz.heroult.pavel.java.book.Kap03.S42;

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

public class IntNaByte {
  public static void main(String[] args) {
     int i; byte b;
     b = -1;   // -1 = 255 neznaménkovì
     i = (b < 0) ? b + 256 : b;
     System.out.println("i = " + i);
     i = 128;
     b = (byte) ((i > 127) ? i - 256 : i);
     System.out.println("b = " + b);
     i = 100;
     b = (byte) ((i > 127) ? i - 256 : i);
     System.out.println("b = " + b);
  }
}

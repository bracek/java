package cz.heroult.pavel.java.book.Kap17.S294;

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

import java.io.*;

public class Soubory1 {
  public static void main(String[] argv) throws IOException{
    String aktDir = System.getProperty("user.dir");
    File novySou = new File("b.txt");
    File novyAdr = new File("TMP");

    if (novySou.exists() == true)
      System.out.println("b.txt existuje");
    else
      novySou.createNewFile();

    if (novySou.isFile() == true)
      System.out.println("b.txt je soubor");

    if (novyAdr.exists() == true)
      System.out.println("TMP existuje");
    else
      novyAdr.mkdir();
    if (novyAdr.isDirectory() == true)
      System.out.println("TMP je adresar");
  }
}

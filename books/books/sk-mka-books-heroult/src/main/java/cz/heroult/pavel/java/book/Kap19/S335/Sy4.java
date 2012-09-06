package cz.heroult.pavel.java.book.Kap19.S335;

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

public class Sy4 {
  public static void main(String[] args) throws IOException {
    String oddRadek = System.getProperty("line.separator");
    String oddSouboru = System.getProperty("file.separator");
    File adr = new File("TMP");

    adr.mkdir();
    String jmenoSouboru = adr.getName() + oddSouboru + "a.txt";
    FileOutputStream fw = new FileOutputStream(jmenoSouboru);
    PrintStream fwPr = new PrintStream(fw);

    fwPr.print("Jedna radka");
    fwPr.print(oddRadek);
    fwPr.println("Druha radka");
    fwPr.println("Treti radka");
    fw.close();
  }
}

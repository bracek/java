package cz.heroult.pavel.bohatstvoKnihoven.kap19;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                   Java -- bohatství knihoven                //
//                II. opravené a rozšíøené vydání              //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       Cti_me.txt                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2006                      // 
//                                                             //
/////////////////////////////////////////////////////////////////

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.zip.*;

public class ZipFileMode {
  public static void main(String[] args) throws IOException {
    File f = new File("pokus.zip");
//    ZipFile zf = new ZipFile(f, ZipFile.OPEN_READ);
//    ZipFile zf = new ZipFile(f, ZipFile.OPEN_READ | ZipFile.OPEN_DELETE);
    ZipFile zf = new ZipFile(f, ZipFile.OPEN_DELETE);
    System.out.println("pocet zapakovanych polozek: " + zf.size());
  }
}

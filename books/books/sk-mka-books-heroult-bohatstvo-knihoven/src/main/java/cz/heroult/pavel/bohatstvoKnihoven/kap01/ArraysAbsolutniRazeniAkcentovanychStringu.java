package cz.heroult.pavel.bohatstvoKnihoven.kap01;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                 Java -- bohatství knihoven                  //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       CTI_ME.TXT                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2003                      // 
//                                                             //
/////////////////////////////////////////////////////////////////


import java.util.*;
import java.text.*;
import java.io.*;


class KomparatorCeskychAkcentovanychStringu implements Comparator {
  private Collator col = Collator.getInstance(new Locale("cs", "CZ"));

  public int compare(Object o1, Object o2) {
    String s1 = (String) o1;
    String s2 = (String) o2;
    return col.compare(s1, s2);
  }
}

public class ArraysAbsolutniRazeniAkcentovanychStringu {
  public static void main(String[] args) throws IOException {
    OutputStreamWriter o = new OutputStreamWriter(System.out, "Cp852");
    PrintWriter p = new PrintWriter(o);

    String[] akcentovaneRetezce = {
      "nový vìk", "Nový Svìt", "Nový svìt", "nový Svìt", 
      "nový svìt", "Nový Svet", "Nový svet",
      "abc traktoristy", "ABC nástrojaøe",
      "abc nástrojaøe", "ABC kováøe", "ABC klempíøe",
      "abc frézaøe", "ABC", "Abc", "abc", "A", "a" };

    Arrays.sort(akcentovaneRetezce, 
                new KomparatorCeskychAkcentovanychStringu());

    for (int i = 0;  i < akcentovaneRetezce.length;  i++)
      p.println(akcentovaneRetezce[i]);

    p.flush();
  }
}

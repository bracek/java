package cz.heroult.pavel.java.book.Kap18.S324;

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

public class PokusRAF {
  public static void main(String[] args) throws IOException {
    RandomAccessFile frw = new RandomAccessFile("a.bin", "rw");
    int k, pocet = 5;
    long posun;
  
    frw.writeInt(pocet);
    for (int i = 0;  i < pocet;  i++) {
      k = (int) (1000.0 * Math.random());
      System.out.print(k + " ");
      frw.writeInt(k);
    }
    frw.writeDouble(Math.PI);
    frw.writeDouble(Math.E);
    System.out.println("\n" + Math.PI + " " + Math.E);
    System.out.println("Velikost souboru: " 
                                      + frw.getFilePointer());
    System.out.println("Velikost souboru: " + frw.length());

    frw.seek(0L);          // návrat na zaèátek
    pocet = frw.readInt();
    posun = 4 * pocet;     // int je velký 4 bajty
    frw.seek(posun);
    frw.writeInt(1234);    // pøepsání posledního int v souboru
    frw.seek(posun);
    k = frw.readInt();
    System.out.print(k);

    frw.skipBytes(8);      // double je velký 8 bajtù
    double e = frw.readDouble();
    System.out.println("\n" + e);
    frw.close();
  }
}

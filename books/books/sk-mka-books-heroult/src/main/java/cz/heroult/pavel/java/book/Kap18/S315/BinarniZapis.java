package cz.heroult.pavel.java.book.Kap18.S315;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojov� k�d je sou��st� distribuce bal�ku program�,  //
//     poskytovan�ch jako dopl�uj�c� informace ke knize        //
//                                                             //
//                  U�ebnice jazyka Java                       //
//                                                             //
//     P�e�t�te si, pros�m, d�kladn� upozorn�n� v souboru      // 
//                       CTI_ME.TXT                            //
//        kter� je ned�lnou sou��st� t�to distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2000                      // 
//                                                             //
/////////////////////////////////////////////////////////////////

import java.io.*;

public class BinarniZapis {
  public static void main(String[] args) throws IOException {
    FileOutputStream fwJm = new FileOutputStream("data.bin");
    DataOutputStream fw = new DataOutputStream(fwJm);
    int k, pocet;

    pocet = 2 + (int) (Math.random() * (10 - 2));
    fw.writeInt(pocet);

    for (int i = 0;  i < pocet;  i++) {
      k = (int) (1000.0 * Math.random());
      System.out.print(k + " ");
      fw.writeInt(k);
    }

    fw.writeDouble(Math.PI);
    fw.writeDouble(Math.E);
    System.out.println("\n" + Math.PI + " " + Math.E);
    fwJm.close();

    FileInputStream frJm = new FileInputStream("data.bin");
    DataInputStream fr = new DataInputStream(frJm);

    pocet = fr.readInt();
    for (int i = 0;  i < pocet;  i++) {
      k = fr.readInt();
      System.out.print(k + " ");
    }

    double pi = fr.readDouble();
    double e = fr.readDouble();

    System.out.println("\n" + pi + " " + e);
    frJm.close();
  }
}

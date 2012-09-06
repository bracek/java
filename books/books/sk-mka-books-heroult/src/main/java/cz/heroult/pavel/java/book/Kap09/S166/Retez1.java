package cz.heroult.pavel.java.book.Kap09.S166;

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

public class Retez1 {
  public static void main(String[] args) {
    String s1, s2, s3, s4, s5, s6, s7;
    byte[] bajty = {(byte)'E', (byte)'v', (byte)'a'};
    char[] znaky = {'M', 'a', 'r', 't', 'i', 'n', 'a'};
    StringBuffer buf = new StringBuffer("dobry den");

    s1 = new String("cao");
    System.out.println("s1:" + s1);
    s2 = new String(s1);
    System.out.println("s2:" + s2);
    s3 = new String(bajty);
    System.out.println("s3:" + s3);
    s4 = new String(bajty, 1, 2);
    System.out.println("s4:" + s4);
    s5 = new String(znaky);
    System.out.println("s5:" + s5);
    s6 = new String(znaky, 3, 4);
    System.out.println("s6:" + s6);
    s7 = new String(buf);
    System.out.println("s7:" + s7);
    System.out.println("s7 = " + s7.length());
//    System.out.println("s7 = " + s7.length);
  }
}


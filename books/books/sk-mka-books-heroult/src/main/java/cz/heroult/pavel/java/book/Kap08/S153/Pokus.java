package cz.heroult.pavel.java.book.Kap08.S153;

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

public class Pokus {
  public int promInstance;
  public static int promTridy;

  public Pokus(int promInstance, int promTridy) {
    this.promInstance = promInstance;
    this.promTridy = promTridy;    // mo�n� zp�sob
    Pokus.promTridy = promTridy;   // �iteln�j�� zp�sob
  }


  public static void main(String[] args) {
    Pokus p = new Pokus(1, 2);

    System.out.println(p.promInstance + " " + promTridy);
  }
}

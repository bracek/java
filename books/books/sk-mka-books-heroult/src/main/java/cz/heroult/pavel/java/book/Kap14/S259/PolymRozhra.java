package cz.heroult.pavel.java.book.Kap14.S259;

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

interface Vazitelny {
  public void vypisHmotnost();
}

class Clovek implements Vazitelny {
  int vaha;
  String profese;

  Clovek(String povolani, int tiha) {
    profese = new String(povolani);
    vaha = tiha;
  }

  public void vypisHmotnost() {
    System.out.println(profese + ": " + vaha);
  }

  public int getHmotnost() { return vaha; }
}

class Kufr implements Vazitelny {
  int vaha;

  Kufr(int tiha) { vaha = tiha; }

  public void vypisHmotnost() {
    System.out.println("kufr: " + vaha);
  }
}

public class PolymRozhra {
  public static void main(String[] args) {
    int vahaLidi = 0;
    Vazitelny[] kusJakoKus = new Vazitelny[3];

    kusJakoKus[0] = new Clovek("programator", 100);
    kusJakoKus[1] = new Kufr(20);
    kusJakoKus[2] = new Clovek("modelka", 51);

    System.out.println("CD - individualni pristup");
    for (int i = 0;  i < kusJakoKus.length;  i++) {
      kusJakoKus[i].vypisHmotnost();
      if (kusJakoKus[i] instanceof Clovek == true) {
//        vahaLidi += kusJakoKus[i].getHmotnost();
        vahaLidi += ((Clovek) kusJakoKus[i]).getHmotnost();
      }
    }
    System.out.println("Ziva vaha: " + vahaLidi);
  }
}

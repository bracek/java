package cz.heroult.pavel.java.book.Kap14.S257;

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

class Zivocich {
  public void vypisInfo() {
    System.out.print(getClass().getName() + ", ");
  }
}

class Ptak extends Zivocich {
  int delkaKridel;

  Ptak(int delka) { delkaKridel = delka; }

  public void vypisInfo() {
    super.vypisInfo();
    System.out.println("delka kridel: " + delkaKridel);
  }
}

class Slon extends Zivocich {
  int delkaChobotu;

  Slon(int delka) { delkaChobotu = delka; }

  public void vypisInfo() {
    super.vypisInfo();
    System.out.println("delka chobotu: " + delkaChobotu);
  }
}

class Had extends Zivocich {
  int delkaTela;

  Had(int delka) { delkaTela = delka; }

  public void vypisInfo() {
    super.vypisInfo();
    System.out.println("delka tela: " + delkaTela);
  }
}

public class PolymDeden {
  public static void main(String[] args) {
    Zivocich[] z = new Zivocich[6];
    for (int i = 0;  i < z.length;  i++) {
      switch ((int) (1.0 + Math.random() * 3.0)) {
        case 1: z[i] = new Ptak(i); break;
        case 2: z[i] = new Slon(i); break;
        case 3: z[i] = new Had(i); break;
      }
    }

    for (int i = 0;  i < z.length;  i++)
      z[i].vypisInfo();
  }
}

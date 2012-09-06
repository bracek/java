package cz.heroult.pavel.java.book.Kap14.S254;

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

abstract class Zivocich {
  String typ;
  Zivocich(String typ) { this.typ = new String(typ); }

  public void vypisInfo() {
    System.out.print(typ + ", ");
    vypisDelku();
  }

  public abstract void vypisDelku();
}

class Ptak extends Zivocich {
  int delkaKridel;

  Ptak(String typ, int delka) {
    super(typ);
    delkaKridel = delka;
  }

  public void vypisDelku() {
    System.out.println("delka kridel: " + delkaKridel);
  }
}

class Slon extends Zivocich {
  int delkaChobotu;

  Slon(String typ, int delka) {
    super(typ);
    delkaChobotu = delka;
  }

  public void vypisDelku() {
    System.out.println("delka chobotu: " + delkaChobotu);
  }
}

class Had extends Zivocich {
  int delkaTela;

  Had(String typ, int delka) {
    super(typ);
    delkaTela = delka;
  }

  public void vypisDelku() {
    System.out.println("delka tela: " + delkaTela);
  }
}

public class PolymAbstr {
  public static void main(String[] args) {
    Zivocich[] z = new Zivocich[6];
    for (int i = 0;  i < z.length;  i++) {
      switch ((int) (1.0 + Math.random() * 3.0)) {
        case 1: z[i] = new Ptak("ptak", i); break;
        case 2: z[i] = new Slon("slon", i); break;
        case 3: z[i] = new Had("had", i); break;
      }
    }

    Zivocich t;
    for (int i = 0;  i < z.length;  i++) {
      t = z[i];      // zbytecne, staci z[i].vypisInfo();
      t.vypisInfo();
    }
  }
}

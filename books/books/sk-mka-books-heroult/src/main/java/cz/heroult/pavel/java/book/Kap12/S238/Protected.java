package cz.heroult.pavel.java.book.Kap12.S238;

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

class AProt {
  protected int i;
  protected int getI() { return i; }
}

class ABProt extends AProt {
  int j;
  ABProt() {
    i = 1;        // v poøádku 
    j = getI();   // v poøádku
  }
}

class BProt {
  int j;
  BProt() {
    AProt a = new AProt();
    a.i = 1;        // v poøádku
    j = a.getI();   // v poøádku
  }
}

public class Protected {
  public static void main(String[] args) {
    ABProt ab = new ABProt();
    System.out.println("AB: i = " + ab.i + ", j = " + ab.j);
    BProt b = new BProt();
    System.out.println("B: j = " + b.j);
  }
}

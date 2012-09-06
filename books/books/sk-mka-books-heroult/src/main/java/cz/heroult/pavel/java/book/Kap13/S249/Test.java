package cz.heroult.pavel.java.book.Kap13.S249;

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

class Usecka implements Info {
  int delka;

  Usecka(int delka) { this.delka = delka; }

  public void kdoJsem() {
    System.out.println("Usecka");
  }
}

class Obdelnik extends Usecka implements InfoDalsi {
  int sirka;

  Obdelnik(int delka, int sirka) {
    super(delka);
    this.sirka = sirka;
  }

  public void kdoJsem() {
    System.out.print("Obdelnik");
  }

  public void vlastnosti() {
    System.out.println(" = " + delka + ", " + sirka);
  }

   public void vypisSirka() {  // není z žádného rozhraní
    System.out.println(sirka);
  }
}

public class Test {
  public static void main(String[] args) {
    Obdelnik o = new Obdelnik(2, 4);
    Info io = new Obdelnik(3, 6);
    InfoDalsi iod = new Obdelnik(5, 7);
    o.kdoJsem(); o.vlastnosti();
    io.kdoJsem(); ((Obdelnik)io).vlastnosti();
    ((Obdelnik)iod).kdoJsem(); iod.vlastnosti();
//    InfoDalsi iud = new Usecka(6);              // chyba
    ((Obdelnik)io).vypisSirka();
  }
}

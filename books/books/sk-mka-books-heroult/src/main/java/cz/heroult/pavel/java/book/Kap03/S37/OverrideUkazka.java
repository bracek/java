package cz.heroult.pavel.java.book.Kap03.S37;

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

class Rodic {
  public void character() {
    System.out.println("znak");
  }
}
    
public class OverrideUkazka extends Rodic {
//  @Override
  public void characters() {
    System.out.println("jiny znak");
  }
  public static void main(String[] args) {
    new OverrideUkazka().character();
  }
}

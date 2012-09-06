package cz.heroult.pavel.bohatstvoKnihoven.kap14;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                   Java -- bohatství knihoven                //
//                II. opravené a rozšíøené vydání              //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       Cti_me.txt                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2006                      // 
//                                                             //
/////////////////////////////////////////////////////////////////

import java.util.*;

class MyOvoce {
  int cena;
  String typ;
  MyOvoce(int cena) {
    this.cena = cena;
    this.typ = "jablko";   // pro jednoduchost
  }

    @Override
  public String toString() {
    return typ + ":" + cena + " Kc";
  }

    @Override
  public int hashCode() {
    return cena;
  }

    @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (o instanceof MyOvoce == false)
      return false;

    boolean stejnaCena = (cena == ((MyOvoce) o).cena);
    boolean stejnyTyp = (typ == ((MyOvoce) o).typ);
    return (stejnaCena && stejnyTyp);
  }
}

public class StejneHesovaciKody {
  public static void main(String[] args) {
    HashSet<MyOvoce> MyOvoceSet = new HashSet<MyOvoce>();
    MyOvoceSet.add(new MyOvoce(7));
    MyOvoceSet.add(new MyOvoce(7));
    System.out.println("MyOvoceSet: " + MyOvoceSet);
    System.out.println("obsahuje 7: "
             + MyOvoceSet.contains(new MyOvoce(7)));
    MyOvoce hruska = new MyOvoce(7);
    hruska.typ = "hruska";
    MyOvoceSet.add(hruska);
    System.out.println("MyOvoceSet: " + MyOvoceSet);
    System.out.println("obsahuje 7: "
             + MyOvoceSet.contains(new MyOvoce(7)));
  }
}

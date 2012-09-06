package cz.heroult.pavel.java.book.Kap11.S215;

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
//final class Rodic {
class Rodic {

    public int i;

    public Rodic() {
        i = 1;
    }
//  abstract int getI();   // chyba

    void setI(int noveI) {
        i = noveI;
    }
}

public class Potomek extends Rodic {

    int getI() {
        return i * 2;
    }

    void setI() {
        i = 5;
    }   // pøetížená

    public static void main(String[] args) {
        Potomek pot = new Potomek();
        pot.setI(3);
        System.out.println("Hodnota je: " + pot.getI());
        pot.setI();   // pøetížená
        System.out.println("Hodnota je: " + pot.getI());
    }
}

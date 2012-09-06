package cz.heroult.pavel.java.book.Kap20.S373;

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
public class TestPrKonDae {

    public static void main(String[] args) {
        CteniDae s1 = new CteniDae("data10.txt");
        ProducentDae vlPr1 = new ProducentDae(s1);
        KonzumentDae vlKon1 = new KonzumentDae(s1);
        vlKon1.start();
        vlPr1.setDaemon(true);
        vlPr1.start();
    }
}

package cz.heroult.pavel.java.book.Kap10.S194;

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
public class TestBankomatu {

    public static void main(String[] args) {
        Bankomat b = new Bankomat();
        // b.penize = 5000;    // nelze - autorizovany pristup
        b.setPenize(1000);
        System.out.println("vybrano: " + b.getPenize(200));
    }
}

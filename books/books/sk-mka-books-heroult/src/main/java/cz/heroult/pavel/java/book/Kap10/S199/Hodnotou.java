package cz.heroult.pavel.java.book.Kap10.S199;

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
class Nasobeni {

    public static int nasobDvema(int i) {
        return i * 2;
    }

    public int nasobTremi(int i) {
        return i * 3;
    }
}

public class Hodnotou {

    public static void main(String[] args) {
        int prvni = 5, druhy = 7;

        Nasobeni n = new Nasobeni();
        prvni = Nasobeni.nasobDvema(prvni);
        druhy = n.nasobTremi(druhy);
        System.out.println("prvni: " + prvni + ", druhy: " + druhy);
    }
}

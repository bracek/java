package cz.heroult.pavel.java.book.Kap10.S200;

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
class IntOdkazem {

    private int i;

    IntOdkazem(int i) {
        this.i = i;
    }

    public void setInt(int i) {
        this.i = i;
    }

    public int getInt() {
        return this.i;
    }
}

class DoubleOdkazem {

    double d;
}

class Nasobeni {

    public static void nasobPeti(IntOdkazem j, DoubleOdkazem f) {
        int pom = j.getInt();
        j.setInt(pom * 5);
        f.d *= 5;
    }
}

public class Odkazem {

    public static void main(String[] args) {
        int prvni = 5;
        double druhy = 3.14;

        IntOdkazem par1 = new IntOdkazem(prvni);
        DoubleOdkazem par2 = new DoubleOdkazem();
        par2.d = druhy;
        Nasobeni.nasobPeti(par1, par2);
        prvni = par1.getInt();
        druhy = par2.d;

        System.out.println("prvni: " + prvni + ", druhy: " + druhy);
    }
}


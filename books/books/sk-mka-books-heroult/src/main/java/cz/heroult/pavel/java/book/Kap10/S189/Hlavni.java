package cz.heroult.pavel.java.book.Kap10.S189;

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
class B {

    int b;

    public B(int par) {
        b = par;
    }

    public void setB(int par) {
        b = par;
    }

    public int getB() {
        return b;
    }
}

public class Hlavni {

    public static void main(String[] argv) {
        B b = new B(3);
        A a = new A(5);

        System.out.println("a = " + a.getA() + ", b = " + b.getB());
    }
}

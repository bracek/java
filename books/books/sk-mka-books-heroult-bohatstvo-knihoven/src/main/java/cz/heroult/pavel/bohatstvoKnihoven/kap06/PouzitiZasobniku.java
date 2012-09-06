package cz.heroult.pavel.bohatstvoKnihoven.kap06;

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

class Zasobnik<E> {

    private LinkedList<E> zasob = new LinkedList<E>();

    public void add(E elem) {
        zasob.addFirst(elem);
    }

    public E remove() {
        return zasob.removeFirst();
    }

    public E get() {
        return zasob.getFirst();
    }

    public boolean isEmpty() {
        return zasob.isEmpty();
    }
}

public class PouzitiZasobniku {

    public static void main(String[] args) {
        Zasobnik<String> zs = new Zasobnik<String>();
        zs.add("prvni");
        zs.add("druhy");
        zs.add("treti");
        System.out.println(zs.get());
        while (zs.isEmpty() == false) {
            System.out.print(zs.remove() + ", ");
        }

        System.out.println();
        Zasobnik<Integer> zi = new Zasobnik<Integer>();
        zi.add(new Integer(8));
        zi.add(new Integer(9));
        while (zi.isEmpty() == false) {
            System.out.print(zi.remove() + ", ");
        }
    }
}

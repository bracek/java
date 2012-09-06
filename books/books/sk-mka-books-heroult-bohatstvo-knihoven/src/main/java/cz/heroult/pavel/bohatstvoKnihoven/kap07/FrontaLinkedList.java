package cz.heroult.pavel.bohatstvoKnihoven.kap07;


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

public class FrontaLinkedList {

    public static void main(String[] args) {
        Queue<String> fifoFronta = new LinkedList<String>();
        fifoFronta.add("5");
        fifoFronta.add("1");
        fifoFronta.add("3");
        System.out.println(fifoFronta);

        fifoFronta.add("2");
        fifoFronta.add("4");
        System.out.println(fifoFronta);

        System.out.println("Na cele je: " + fifoFronta.element());

        while (fifoFronta.isEmpty() == false) {
            System.out.print(fifoFronta.remove() + ", ");
        }
    }
}

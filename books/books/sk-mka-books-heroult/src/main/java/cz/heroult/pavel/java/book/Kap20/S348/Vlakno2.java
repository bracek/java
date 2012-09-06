package cz.heroult.pavel.java.book.Kap20.S348;

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
public class Vlakno2 extends Thread {

    @Override
    public void run() {
        while (ReadVl.hotovo == false) {
            System.out.print(ReadVl.suma + "\r");
            Thread.yield();
        }
        System.out.println(ReadVl.suma);
    }

    public static void main(String[] args) {
        ReadVl vlCteni = new ReadVl("data.txt");
        vlCteni.start();
        Vlakno2 vlVypis = new Vlakno2();
        vlVypis.start();
    }
}

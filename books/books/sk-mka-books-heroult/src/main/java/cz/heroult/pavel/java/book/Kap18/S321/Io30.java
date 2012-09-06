package cz.heroult.pavel.java.book.Kap18.S321;

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

import java.io.*;

public class Io30 {
  public static Reader vyber(Reader vstup,int index,char znak)
                                        throws IOException {
    BufferedReader bufVstup = new BufferedReader(vstup);

    PipedWriter rouraVystup = new PipedWriter();
    PipedReader rouraVstup = new PipedReader(rouraVystup);

    PrintWriter formRouraVystup = new PrintWriter(rouraVystup);

    String radka;

    while((radka = bufVstup.readLine()) != null) {
      if (index < radka.length() 
          &&  radka.charAt(index) == znak)
        formRouraVystup.println(radka);
    }

    formRouraVystup.close();
    return rouraVstup;
  }

  public static void main(String[] argv) throws IOException {
    FileReader fr = new FileReader("data120.txt");
    Reader jednaDevet = vyber(vyber(fr, 0, '1'), 2, '9');
    fr.close();

    String radka;                          // závìreèný tisk
    BufferedReader br = new BufferedReader(jednaDevet);

    while((radka = br.readLine()) != null) {
      System.out.println(radka);
    }

    br.close();
  }
}



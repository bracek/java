package cz.heroult.pavel.java.book.Kap20.S364;

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

public class BlokSynchr {
  RandomAccessFile file;

  BlokSynchr(RandomAccessFile f) {
    file = f;
  }

  public void presun(long kam) throws IOException {
    synchronized (file) {
      file.seek(kam);
    }
  }

  public int ctiInt() throws IOException {
    synchronized (file) {
      return file.readInt();
    }
  }
}

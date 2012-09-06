package cz.heroult.pavel.java.book.Kap04.S83;

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

// import java.util.Formatter;

public class FormatovaniVystupu {
  public static void main(String [] args) {
    
    System.out.format("nova radka%n");
    
    int i = -1234;
    System.out.format("i = %d%n", i);
    System.out.format("i = %7d%n", i);
    System.out.format("i = %-7d%n", i);
    System.out.format("i = %+7d%n", i);
    System.out.format("i = % 7d%n", i);
    System.out.format("i = %07d%n", i);
    System.out.format("i = %, 7d%n", i);
    
    char c = 'a';
    System.out.format("c = %c%n", c);
    System.out.format("c = %3c%n", c);
    // System.out.format("c = %C%n", c); // nefunguje
    System.out.format("c = %c%n", 66);
    System.out.format("Znak %c ma ASCII hodnotu: %d%n", c, (int) c);
    
    double d = 1234.567;
    System.out.format("d = %f%n", d);
    System.out.format("d = %g%n", d);
    System.out.format("d = %e%n", d);
    System.out.format("d = %10.1f%n", d);
    System.out.format("d = %-10.1f%n", d);
    
    int j = 30;
    System.out.format("j = %o%n", j);
    System.out.format("j = %x%n", j);
    System.out.format("j = %X%n", j);
    System.out.format("j = %3X%n", j);
    System.out.format("j = %#x%n", j);
    
    String s = "Ahoj lidi";
    System.out.format("s = |%s|%n", s);
    System.out.format("s = |%S|%n", s);
    System.out.format("s = |%11s|%n", s);
    System.out.format("s = |%-11s|%n", s);
    System.out.format("s = |%.3s|%n", s);
    System.out.format("s = |%11.3s|%n", s);
    
    
    System.out.format("Znak 'backslash' je \\%n");
    
    j = 30;
    System.out.format("%d = %o = %X%n", j, j, j);
    System.out.format("%1$d = %1$o = %1$X%n", j);
    System.out.format("%d = %<o = %<X%n", j);
    
    
    String zformatovanyRetezec = String.format ("%6d", 123);
    System.out.println("konec");
    
  }
}

/*
 nova radka
i = -1234
i =   -1234
i = -1234  
i =   -1234
i =   -1234
i = -001234
i =  -1 234

c = a
c =   a
c = B

d = 1234,567000
d = 1234.57
d = 1.234567e+03
d =     1234,6
d = 1234,6 

j = 36
j = 1e
j = 1E
j =  1E
j = 0x1e

s = |ahoj lidi|
s = |AHOJ LIDI|
s = |  ahoj lidi|
s = |ahoj lidi  |
s = |aho|
s = |        aho|

Pivo "lezak" ma 12%
Znak 'backslash' je \

30 = 36 = 1E
30 = 36 = 1E
30 = 36 = 1E
konec
*/
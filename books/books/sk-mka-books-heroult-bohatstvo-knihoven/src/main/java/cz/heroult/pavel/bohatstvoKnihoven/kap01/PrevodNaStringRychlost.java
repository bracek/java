package cz.heroult.pavel.bohatstvoKnihoven.kap01;



import java.util.*;

public class PrevodNaStringRychlost {
  public static void main(String[] args) {
    int kolik;
    if (args.length > 0) {
      kolik = Integer.parseInt(args[0]);
    }
    else {
      kolik = 10;
    }

    long z, k;
    String s;
    z = System.currentTimeMillis();
    for (int i = 0;  i < kolik;  i++) {
      for (int j = 0;  j < 1000000;  j++) {
        s = "" + j;
      }
    }  

    k = System.currentTimeMillis();
    System.out.println("cas = " + (k - z));

    z = System.currentTimeMillis();
    for (int i = 0;  i < kolik;  i++) {
      for (int j = 0;  j < 1000000;  j++) {
        s = String.valueOf(j);
      }
    }  

    k = System.currentTimeMillis();
    System.out.println("cas = " + (k - z));
  }
}

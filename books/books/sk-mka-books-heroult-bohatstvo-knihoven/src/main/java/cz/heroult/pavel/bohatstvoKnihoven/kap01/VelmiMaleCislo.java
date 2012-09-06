package cz.heroult.pavel.bohatstvoKnihoven.kap01;


public class VelmiMaleCislo {
  public static void main(String[] args) {
    int i = 2000000000;
    int j = -2000000000;
    int k = i - j;
    int l = j - i;

    System.out.println("i - j = " + k);
    System.out.println("j - i = " + l);
  }
}

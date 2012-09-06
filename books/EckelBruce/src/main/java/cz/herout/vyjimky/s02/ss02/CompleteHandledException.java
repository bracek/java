/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.vyjimky.s02.ss02;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author bracek
 */
public class CompleteHandledException {

    public static int[] createAndLoadArray() {
        int[] pole = null;
        String jmeno = "data.txt";

        while (true) {
            try {
                Scanner sc = new Scanner(new File(jmeno));
                int n = sc.nextInt();
                pole = new int[n];
                for (int i = 0; i < n; i++) {
                    pole[i] = sc.nextInt();
                }
                break;

            } catch (Exception ex) {
                System.out.println("Subor " + jmeno + " nenalezen!");
                System.out.println("Zadaj spravne jmeno:");
                Scanner scKlavesnice = new Scanner(System.in);
                jmeno = scKlavesnice.next();
            }
        }
        return pole;
    }

    public static void main(String[] args) throws IOException {
        int[] abs = createAndLoadArray();
        System.out.println(Arrays.toString(abs));
    }
}

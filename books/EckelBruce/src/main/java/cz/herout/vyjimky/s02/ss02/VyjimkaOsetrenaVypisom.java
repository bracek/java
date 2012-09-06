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
public class VyjimkaOsetrenaVypisom {

    public static int[] createAndLoadArray() {
        int[] pole = null;

        try {
            Scanner sc = new Scanner(new File("cisla.txt"));
            int n = sc.nextInt();
            pole = new int[n];
            for (int i = 0; i < n; i++) {
                pole[i] = sc.nextInt();
            }
            return pole;

        } catch (Exception ex) {
            System.out.println("Subor sa nenasiel");
            ex.printStackTrace();
        }
        return pole;
    }

    public static void main(String[] args) throws IOException {
        int[] abs = createAndLoadArray();
        System.out.println(Arrays.toString(abs));
    }
}

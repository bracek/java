/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.vyjimky.s02.ss01;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author bracek
 */
public class VyjimkaDeklarovana {

    public static int[] createAndLoadArray() throws IOException {
        Scanner sc = new Scanner(new File("cisla.txt"));
        int n = sc.nextInt();
        int[] pole = new int[n];
        for (int i = 0; i < n; i++) {
            pole[i] = sc.nextInt();

        }
        return pole;
    }

    public static void main(String[] args) throws IOException {
        int[] abs = createAndLoadArray();
        System.out.println(Arrays.toString(abs));
    }
}

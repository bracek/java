package cz.heroult.pavel.bohatstvoKnihoven.kap01;

import java.util.*;

public class ArraysVicerozmernaPole {

    public static void main(String[] args) {
        int[][] p1 = {{1, 2, 3},
            {4, 5},
            {6}};
        int[][] p2 = {{1, 2, 3}, {4, 5}, {7}};
        int[][] p3 = {{1, 2, 3}, {4, 5}, {7, 8}};
        int[][] p4 = {{1, 2, 3}, {4, 5}};
        int[][] p5 = {{1, 2, 3}, {4, 5}, {6}};
        System.out.println(Arrays.deepToString(p1));
        System.out.println("1 == 2: " + Arrays.deepEquals(p1, p2));
        System.out.println("1 == 3: " + Arrays.deepEquals(p1, p3));
        System.out.println("1 == 4: " + Arrays.deepEquals(p1, p4));
        System.out.println("1 == 5: " + Arrays.deepEquals(p1, p5));
    }
}  

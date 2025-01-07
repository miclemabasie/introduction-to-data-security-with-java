package com.abasie.utils;

import java.util.Scanner;

public class ModularInverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strList = str.split(" ");
        int a = Integer.parseInt(strList[0]);
        int b = Integer.parseInt(strList[1]);

        try {
            int inverse = ModularInverse.modularInverse(a, b);
            System.out.println("The modular inverse of " + a + " mod " + b + " is: " + inverse);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public static int modularInverse(int a, int m) {

        a = a % m;

        int[] result = extendedGcd(a, m);

        int gcd = result[0];
        int x = result[1];

        if (gcd != 1) {
            throw new IllegalArgumentException("Modular inverse does not exist for " + a + " mod " + m);
        }
        // Ensure the result is positive
        if(x < 0){
            while(x < 0){
                x += m;
            }
        }
        return x;
    }

    public static int[] extendedGcd(int a, int b) {
        if (b == 0) {
            return new int[] { a, 1, 0 };
        }
        int[] result = extendedGcd(b, a % b);
        int gcd = result[0];
        int x1 = result[1];
        int y1 = result[2];

        int x = y1;
        int y = x1 - (a / b) * y1;

        return new int[] { gcd, x, y };
    }
}

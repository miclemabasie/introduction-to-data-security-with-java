package com.abasie.utils;
import com.abasie.utils.ModularInverse;
import com.abasie.utils.MonoAlphabeticCipher;

import java.util.Scanner;

public class AffineCipher {
    public static void main(String[] args) {
        System.out.println("==== AFFINE CIPHER ENCRYPTION ====");
        Scanner scanner = new Scanner(System.in);
        int length_of_alphaber = MonoAlphabeticCipher.get_plain_text().length;
        int option;
        do {
            System.out.print("Enter Option (1) to encrypt, (2) to decrypt, (0) to exit: ");
            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.println("Enter a text to encrypt:");
                String input = scanner.nextLine();
                String cipherText = encrypt(input, 5, 7, length_of_alphaber);
                System.out.println("Encrypted text: " + cipherText);

            } else if (option == 2) {
                System.out.println("Enter a text to decrypt:");
                String input2 = scanner.nextLine();
                String plainText = decrypt(input2, 5, 7, length_of_alphaber);
                System.out.println("Decrypted text: " + plainText);

            } else if (option == 0) {
                System.out.println("Exiting...");
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        } while (option == 1 || option == 2);

        scanner.close();
    }

    public static String encrypt(String text, int a, int b, int m){
        String[] plain_text = MonoAlphabeticCipher.get_plain_text();
        StringBuilder cipher_text = new StringBuilder();
        for (char c : text.toCharArray()){
            boolean found_char = false;
            String character = String.valueOf(c);
            for (int i = 0; i < plain_text.length; i++){
                if (character.equals(plain_text[i])){
                    cipher_text.append(plain_text[find_character_encryption_index(a, i, b, m)]);
                    found_char = true;
                    break;
                }
            }
            if(!found_char){
                cipher_text.append(character);
            }
        }
        return cipher_text.toString();
    }

    public static String decrypt(String text, int a, int b, int m) {
        String[] plain_text = MonoAlphabeticCipher.get_plain_text();
        StringBuilder cipher_text = new StringBuilder();
        for (char c : text.toCharArray()){
            boolean found_char = false;
            String character = String.valueOf(c);
            for (int i = 0; i < plain_text.length; i++){
                if (character.equals(plain_text[i])){
                    cipher_text.append(plain_text[find_character_decryption_index(a, i, b, m)]);
                    found_char = true;
                    break;
                }
            }
            if(!found_char){
                cipher_text.append(character);
            }
        }
        return cipher_text.toString();
    }

    public static int find_character_decryption_index(int a, int x, int b, int m){
        int invertible_modulo = ModularInverse.modularInverse(a, m);
        return ((invertible_modulo * x) + get_positive_b_factor((invertible_modulo * b) * -1, m)) % m;
    }

    public static int find_character_encryption_index(int a, int x, int b, int m){
        return ((a*x) + b) % m; // formula for affine encryption hash function
    }

    public static int get_positive_b_factor(int b, int m){
        if(b > 0){
            return b;
        }
        return get_positive_b_factor(b + m, m);
    }
}

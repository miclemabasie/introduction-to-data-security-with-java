package com.abasie.utils;

import com.abasie.utils.MonoAlphabeticCipher;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CaeserCipher {
    public static void main(String[] args) {
        System.out.println("==== CAESER CIPHER ENCRYPTION ====");
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.print("Enter Option (1) to encrypt, (2) to decrypt, (0) to exit: ");
            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.println("Enter a text to encrypt:");
                String input = scanner.nextLine();
                String cipherText = encrypt(input, 3);
                System.out.println("Encrypted text: " + cipherText);

            } else if (option == 2) {
                System.out.println("Enter a text to decrypt:");
                String input2 = scanner.nextLine();
                String plainText = decrypt(input2, 3);
                System.out.println("Decrypted text: " + plainText);

            } else if (option == 0) {
                System.out.println("Exiting...");
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        } while (option == 1 || option == 2);

        scanner.close();
    }
    public static String encrypt(String text, int k){
        String[] plain_text = MonoAlphabeticCipher.get_plain_text();
        StringBuilder cipher_text = new StringBuilder();

        for (char c: text.toCharArray()){
            boolean found_char = false;
            String character = String.valueOf(c);
            for (int j = 0; j < plain_text.length; j++){
                if (character.equals(plain_text[j])){
                    cipher_text.append(plain_text[((j + k) % plain_text.length)]);
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
    public static String decrypt(String text, int k){
        String[] plain_text = MonoAlphabeticCipher.get_plain_text();
        StringBuilder cipher_text = new StringBuilder();

        for (char c: text.toCharArray()){
            boolean found_char = false;
            String character = String.valueOf(c);
            for (int j = 0; j < plain_text.length; j++){
                if (character.equals(plain_text[j])){
                    cipher_text.append(plain_text[((j - k) % plain_text.length)]);
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
}

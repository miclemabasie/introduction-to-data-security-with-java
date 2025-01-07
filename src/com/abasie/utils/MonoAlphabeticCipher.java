package com.abasie.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MonoAlphabeticCipher {
    public static void main(String[] args) {
        System.out.println("==== MONOALPHABETIC CIPHER ENCRYPTION ====");
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.print("Enter Option (1) to encrypt, (2) to decrypt, (0) to exit: ");
            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.println("Enter a text to encrypt:");
                String input = scanner.nextLine();
                String cipherText = encrypt(input, 7);
                System.out.println("Encrypted text: " + cipherText);

            } else if (option == 2) {
                System.out.println("Enter a text to decrypt:");
                String input2 = scanner.nextLine();
                String plainText = decrypt(input2, 7);
                System.out.println("Decrypted text: " + plainText);

            } else if (option == 0) {
                System.out.println("Exiting...");
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        } while (option == 1 || option == 2);

            scanner.close();
    }

    public static String encrypt(String text, int offset) {
        String[] plain_text = get_plain_text();
        String[] cipher_char_list = generate_cipher_char_list(offset, plain_text);
        Map<String, String> encryptionMap = new HashMap<>();
        for (int i = 0; i < plain_text.length; i++) {
            encryptionMap.put(plain_text[i], cipher_char_list[i]);
        }

        StringBuilder cipher_text = new StringBuilder();
        for (char c : text.toCharArray()) {
            String character = String.valueOf(c);
            if (encryptionMap.containsKey(character)) {
                cipher_text.append(encryptionMap.get(character));
            } else {
                cipher_text.append(character);
            }
        }
        return cipher_text.toString();
    }

    public static String[] generate_cipher_char_list(int offset, String[] plain_text) {
        String[] cipher_text_list = new String[plain_text.length];
        int length = plain_text.length;
        for (int i = 0; i < length; i++) {
            cipher_text_list[i] = plain_text[(i + offset) % length];
        }
        return cipher_text_list;
    }


    public static String decrypt(String text, int offset){
        String[] plain_text = get_plain_text();
        String[] cipher_char_list = generate_cipher_char_list(offset, plain_text);

        Map<String, String> encryptionMap = new HashMap<>();
        for (int i = 0; i < plain_text.length; i++) {
            encryptionMap.put(cipher_char_list[i], plain_text[i]);
        }
        StringBuilder cipher_text = new StringBuilder();
        for (char c : text.toCharArray()) {
            String character = String.valueOf(c);
            if (encryptionMap.containsKey(character)) {
                cipher_text.append(encryptionMap.get(character));
            } else {
                cipher_text.append(character);
            }
        }
        return cipher_text.toString();
    }

    public static String[] get_plain_text(){
        return new String[]{
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };
    }


}
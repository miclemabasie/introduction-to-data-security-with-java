package com.abasie.utils;

import java.util.Scanner;

public class PlayFairCipher {

    private static final int SIZE = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==== PLAYFAIR CIPHER ====");
        System.out.print("Enter a keyword: ");
        String keyword = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace('J', 'I');

        char[][] cipherTable = createCipherTable(keyword);

        System.out.print("Enter text to encrypt: ");
        String plaintext = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace('J', 'I');
        String ciphertext = encrypt(plaintext, cipherTable);

        System.out.println("Encrypted text: " + ciphertext);

        System.out.print("Enter text to decrypt: ");
        String encryptedText = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace('J', 'I');
        String decryptedText = decrypt(encryptedText, cipherTable);

        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }

    private static char[][] createCipherTable(String keyword) {
        boolean[] used = new boolean[26]; // To track used letters
        char[][] table = new char[SIZE][SIZE];
        int index = 0;

        // Add keyword to the table
        for (char c : keyword.toCharArray()) {
            if (!used[c - 'A']) {
                table[index / SIZE][index % SIZE] = c;
                used[c - 'A'] = true;
                index++;
            }
        }

        // Add remaining letters to the table
        for (char c = 'A'; c <= 'Z'; c++) {
            if (!used[c - 'A'] && c != 'J') { // 'J' is replaced by 'I'
                table[index / SIZE][index % SIZE] = c;
                used[c - 'A'] = true;
                index++;
            }
        }
        return table;
    }

    private static String encrypt(String text, char[][] table) {
        text = formatTextForPlayfair(text);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] posA = findPosition(a, table);
            int[] posB = findPosition(b, table);
            // rules for playfair cipher
            if (posA[0] == posB[0]) { // Same row
                result.append(table[posA[0]][(posA[1] + 1) % SIZE]);
                result.append(table[posB[0]][(posB[1] + 1) % SIZE]);
            } else if (posA[1] == posB[1]) { // Same column
                result.append(table[(posA[0] + 1) % SIZE][posA[1]]);
                result.append(table[(posB[0] + 1) % SIZE][posB[1]]);
            } else { // Rectangle swap
                result.append(table[posA[0]][posB[1]]);
                result.append(table[posB[0]][posA[1]]);
            }
        }
        return result.toString();
    }

    private static String decrypt(String text, char[][] table) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] posA = findPosition(a, table);
            int[] posB = findPosition(b, table);

            if (posA[0] == posB[0]) { // Same row
                result.append(table[posA[0]][(posA[1] + SIZE - 1) % SIZE]);
                result.append(table[posB[0]][(posB[1] + SIZE - 1) % SIZE]);
            } else if (posA[1] == posB[1]) { // Same column
                result.append(table[(posA[0] + SIZE - 1) % SIZE][posA[1]]);
                result.append(table[(posB[0] + SIZE - 1) % SIZE][posB[1]]);
            } else { // Rectangle swap
                result.append(table[posA[0]][posB[1]]);
                result.append(table[posB[0]][posA[1]]);
            }
        }
        return result.toString();
    }

    private static String formatTextForPlayfair(String text) {
        StringBuilder formattedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            formattedText.append(c);

            if (i + 1 < text.length() && c == text.charAt(i + 1)) {
                formattedText.append('X'); // Insert filler character
            }
        }
        if (formattedText.length() % 2 != 0) {
            formattedText.append('X'); // Ensure even-length text
        }
        return formattedText.toString();
    }

    private static int[] findPosition(char c, char[][] table) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (table[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("Character not found in cipher table");
    }
}
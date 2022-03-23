package com.javarush.cryptoanalyser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.javarush.cryptoanalyser.CharacterData.ALPHABET_FULL;
import static com.javarush.cryptoanalyser.CharacterData.ALPHABET_FULL_SIZE;

public class Main {

    private static String encryption(String encryptTextBefore, int encryptKey){
        char[] encryptCharBefore = encryptTextBefore.toCharArray();
        char[] encryptCharAfter = new char[encryptCharBefore.length];
        boolean isPresence = false;
        for (int i = 0; i < encryptCharBefore.length; i++) {
            for (int k = 0; k < ALPHABET_FULL_SIZE; k++) {
                if (encryptCharBefore[i] == ALPHABET_FULL[k]) {
                    isPresence = true;
                }
                if (isPresence){
                    encryptCharAfter[i] = ALPHABET_FULL[(k + encryptKey)%ALPHABET_FULL_SIZE];
                    isPresence = false;
                }
            }
        }
        return new String(encryptCharAfter);
    }

    private static String decryption(String decryptTextBefore, int decryptKey){
        char[] decryptCharBefore = decryptTextBefore.toCharArray();
        char[] decryptCharAfter = new char[decryptCharBefore.length];
        boolean isPresence = false;
        for (int i = 0; i < decryptCharBefore.length; i++) {
            for (int k = 0; k < ALPHABET_FULL_SIZE; k++) {
                if (decryptCharBefore[i] == ALPHABET_FULL[k]) {
                    isPresence = true;
                }
                if (isPresence){
                    if ((k-decryptKey) > 0) {
                        decryptCharAfter[i] = ALPHABET_FULL[(k - decryptKey)%ALPHABET_FULL_SIZE];
                    } else {
                        decryptCharAfter[i] = ALPHABET_FULL[(ALPHABET_FULL_SIZE + (k - decryptKey))%ALPHABET_FULL_SIZE];
                    }
                    isPresence = false;
                }
            }
        }
        return new String(decryptCharAfter);
    }

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите путь файла для шифрования текста:");
            byte[] buffer = Files.readAllBytes(Path.of(reader.readLine()));
            String encryptTextBefore = new String(buffer, StandardCharsets.UTF_8);

            System.out.println("Введите ключ для шифрования ввиде цифры:");
            int encryptKey = Integer.parseInt(reader.readLine());

            System.out.println("Введите путь для создания файла и записи зашифрованного текста:");
            Path encryptFile = Files.createFile(Path.of(reader.readLine()));
            Files.writeString(encryptFile,encryption(encryptTextBefore, encryptKey));

            String decryptTextBefore = encryption(encryptTextBefore, encryptKey);

            System.out.println("Введите путь для создания файла и записи расшифрованного текста:");
            Path decryptFile = Files.createFile(Path.of(reader.readLine()));

            System.out.println("Введите ключ для расшифрования ввиде цифры:");
            int decryptKey = Integer.parseInt(reader.readLine());
            Files.writeString(decryptFile,decryption(decryptTextBefore, decryptKey));

        } catch (IOException e) {
            System.out.println("The file does not exist " + e.getMessage());
        }
    }
}

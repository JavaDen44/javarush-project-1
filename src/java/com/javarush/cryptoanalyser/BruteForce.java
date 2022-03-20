package com.javarush.cryptoanalyser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteForce {
    private static final char[] ALPHABET_FULL = {'А', 'а', 'Б', 'б', 'В', 'в', 'Г', 'г', 'Д', 'д', 'Е', 'е',
            'Ё', 'ё', 'Ж', 'ж', 'З', 'з', 'И', 'и', 'Й', 'й', 'К', 'к', 'Л', 'л', 'М', 'м', 'Н', 'н', 'О', 'о',
            'П', 'п', 'Р', 'р', 'С', 'с', 'Т', 'т', 'У', 'у', 'Ф', 'ф', 'Х', 'х', 'Ц', 'ц', 'Ч', 'ч', 'Ш', 'ш', 'Щ', 'щ',
            'Ъ', 'ъ', 'Ы', 'ы', 'Ь', 'ь', 'Э', 'э', 'Ю', 'ю', 'Я', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    private static final int ALPHABET_FULL_SIZE = ALPHABET_FULL.length;

    private static final List<String> KEY_CHARACTERS = Arrays.asList(" без ", " в " , " до " , " для ", " за " , " из ",
            " за ", " к ", " на ", " над ", " об ", " от ", " перед ", " под ", " при ", " с ", " у ", " через ", ". ",
            ", ", "? ", ": ", "; ", "! ", "-", ") ", " (", " ");

    private static String decryption(String encryptText, int decryptKey){
        char[] decryptCharBefore = encryptText.toCharArray();
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

    private static int getKeyCharactersCount(String force){
        int keyCharactersCount = 0;

        for (String keyCh : KEY_CHARACTERS) {
            if (force.contains(keyCh)) {
                keyCharactersCount++;
            }
        }
        return keyCharactersCount;
    }

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите путь файла для взлома текста:");
            byte[] buffer = Files.readAllBytes(Path.of(reader.readLine()));
            String encryptText = new String(buffer, StandardCharsets.UTF_8);
            System.out.println("""
                    Введите размер зашифрованного текста
                    (маленький текст - введите "small"
                    средний текст - введите "medium"
                    большой текст - введите "large"):""");

            String sizeText = reader.readLine();
            int sizeNumber = 0;

            switch (sizeText) {
                case "small":
                    break;
                case "medium":
                    sizeNumber = 4;
                    break;
                case "large":
                    sizeNumber = 8;
                    break;
                default:
                    System.out.println("Неправильно введён размер (по умолчанию выбран размер \"small\").");
                    break;
            }

            System.out.println("Введите путь для создания файла и записи взломанного текста:");
            Path forceTextFile = Files.createFile(Path.of(reader.readLine()));
            List<String> listVariants = new ArrayList<>();
            List<String> forceText = new ArrayList<>();
            for (int i = 0; i < ALPHABET_FULL_SIZE; i++) {
                listVariants.add(decryption(encryptText,i));
                boolean endText = (((listVariants.get(i).charAt(listVariants.get(i).length()-1)) == '!')
                        || ((listVariants.get(i).charAt(listVariants.get(i).length()-1)) == '?')
                        || ((listVariants.get(i).charAt(listVariants.get(i).length()-1)) == '.'));

                if ((getKeyCharactersCount(listVariants.get(i)) > sizeNumber) && (endText)){
                    forceText.add(listVariants.get(i));
                }
            }
            Files.write(forceTextFile, forceText);

        } catch (IOException e) {
            System.out.println("The file does not exist " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No text " + e.getMessage());
        }
    }
}

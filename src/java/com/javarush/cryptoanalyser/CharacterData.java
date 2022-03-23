package com.javarush.cryptoanalyser;

import java.util.Arrays;
import java.util.List;

public class CharacterData {
    public static final char[] ALPHABET_FULL = {'А', 'а', 'Б', 'б', 'В', 'в', 'Г', 'г', 'Д', 'д', 'Е', 'е',
            'Ё', 'ё', 'Ж', 'ж', 'З', 'з', 'И', 'и', 'Й', 'й', 'К', 'к', 'Л', 'л', 'М', 'м', 'Н', 'н', 'О', 'о',
            'П', 'п', 'Р', 'р', 'С', 'с', 'Т', 'т', 'У', 'у', 'Ф', 'ф', 'Х', 'х', 'Ц', 'ц', 'Ч', 'ч', 'Ш', 'ш', 'Щ', 'щ',
            'Ъ', 'ъ', 'Ы', 'ы', 'Ь', 'ь', 'Э', 'э', 'Ю', 'ю', 'Я', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    public static final int ALPHABET_FULL_SIZE = ALPHABET_FULL.length;

    public static final List<String> KEY_CHARACTERS = Arrays.asList(" без ", " в " , " до " , " для ", " за " , " из ",
            " за ", " к ", " на ", " над ", " об ", " от ", " перед ", " под ", " при ", " с ", " у ", " через ", ". ",
            ", ", "? ", ": ", "; ", "! ", "-", ") ", " (", " ");
}

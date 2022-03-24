package ru.javarush.ryazanski.cryptoanalizer.constants;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    private static final String rus = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮЁ";
    private static final String symbols = ".,();:-!? ";

    public static char[] getAlphabet() {
        return ALPHABET;
    }

    public static final char[] ALPHABET = (rus.toLowerCase() + symbols).toCharArray();
    public static final String TXT_FOLDER = System.getProperty("user.dir") + File.separator +
            "text" + File.separator;

    public static Map<Character, Integer> alphabetIndex = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < ALPHABET.length; i++) {
            alphabetIndex.put(ALPHABET[i], i);
        }
        System.out.println(ALPHABET.length);
    }
}
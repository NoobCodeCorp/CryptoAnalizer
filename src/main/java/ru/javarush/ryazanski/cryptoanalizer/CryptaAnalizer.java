package ru.javarush.ryazanski.cryptoanalizer;

import ru.javarush.ryazanski.cryptoanalizer.commands.Bruteforce;
import ru.javarush.ryazanski.cryptoanalizer.commands.Decode;
import ru.javarush.ryazanski.cryptoanalizer.commands.Encode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CryptaAnalizer {
      public static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
              'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
              'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З',
              'И', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
              'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', '«', '»', '"', '\'', ':', '!', '?', ',', '-', '.', '0', '1',
              '2', '3', '4', '5', '6', '7', '8', '9', ' ', '\n'};
      public static Map<Character, Integer> alphabetIndex = new HashMap<>();
      public static final String USER_DIR = System.getProperty("user.dir")
              + File.separator + "text" + File.separator;
      public static final String FILE_NOT_FOUND = "Файл не найден. ";
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Select mode
                1- кодирование
                2- декодирование
                3- брутфорс
                4- выход""");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < ALPHABET.length; i++) {
            alphabetIndex.put(ALPHABET[i], i);
        }
            int mode = scanner.nextInt();
            if (mode == 1) {
                Encode.encode();
            }
            if (mode == 2) {
                Decode.decode();
            }
            if (mode == 3) {
                Bruteforce.bruteforce();
            }
            if (mode == 4) {
                System.exit(0);
            }
        }
    }


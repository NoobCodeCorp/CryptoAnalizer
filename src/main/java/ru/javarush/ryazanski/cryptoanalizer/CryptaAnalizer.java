package ru.javarush.ryazanski.cryptoanalizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < ALPHABET.length; i++) {
            alphabetIndex.put(ALPHABET[i], i);
        }
        System.out.println("""
                Select mode
                1- кодирование
                2- декодирование
                3- брутфорс""");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        if (mode == 1) {
            Encode();
        }
        if (mode == 2) {
            Decode();
        }
        if (mode == 3) {
            Bruteforce();
        }
    }


    public static void Encode() throws IOException {
        System.out.println("Введите имя кодируемого файла");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        System.out.println("Введите ключ(любое положительное число)");
        int key = scanner.nextInt();
        Path file = Path.of("C:\\JDK\\Encoded.txt");
        Path text = Path.of("C:\\JDK\\text.txt");
        if (Files.exists(file)) {
            Files.delete(file);
        }
        Files.createFile(file);
        try (FileReader fileReader = new FileReader(String.valueOf(text));
             FileWriter fileWriter = new FileWriter(String.valueOf(file))) {
            while (fileReader.ready()) {
                char c = (char) fileReader.read();
                int index = 0;
                if (alphabetIndex.get(c) != null) {
                    index = alphabetIndex.get(c);
                } else if (alphabetIndex.get(c) == null) {
                    continue;
                }
                if (index + (key % ALPHABET.length) >= ALPHABET.length) {
                    fileWriter.write(ALPHABET[(index + key % ALPHABET.length) - ALPHABET.length]);
                } else {
                    fileWriter.write(ALPHABET[index + (key % ALPHABET.length)]);
                }
            }
        } catch (IOException e) {

        }
    }

    private static void Decode() throws IOException {
        System.out.println("Введите имя декодируемого файла");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        System.out.println("Введите ключ(любое положительное число)");
        int key = scanner.nextInt();
        Path file = Path.of("C:\\JDK\\Encoded.txt");
        Path text = Path.of("C:\\JDK\\Decoded.txt");
        if (Files.exists(text)) {
            Files.delete(text);
        }
        Files.createFile(text);
        try (FileReader fileReader = new FileReader(String.valueOf(file));
             FileWriter fileWriter = new FileWriter(String.valueOf(text))) {
            while (fileReader.ready()) {
                char c = (char) fileReader.read();
                int index = 0;
                if (alphabetIndex.get(c) != null) {
                    index = alphabetIndex.get(c);
                } else if (alphabetIndex.get(c) == null) {
                    continue;
                }
                if (index - (key % ALPHABET.length) < 0) {
                    fileWriter.write(ALPHABET[(index - key % ALPHABET.length) + ALPHABET.length]);
                } else {
                    fileWriter.write(ALPHABET[index - (key % ALPHABET.length)]);
                }
            }
        } catch (IOException e) {

        }
    }

    private static void Bruteforce() throws IOException {
        System.out.println("Введите имя файла для Брутфорса");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        for (int i = 0; i < ALPHABET.length; i++) {
            Path file = Path.of("C:\\JDK\\Encoded.txt");
            Path text = Path.of("C:\\JDK\\Bruteforced.txt");
            if (Files.exists(text)) {
                Files.delete(text);
            }
            Files.createFile(text);
            try (FileReader fileReader = new FileReader(String.valueOf(file));
                 FileWriter fileWriter = new FileWriter(String.valueOf(text))) {
                while (fileReader.ready()) {
                    char c = (char) fileReader.read();
                    int index = 0;
                    if (alphabetIndex.get(c) != null) {
                        index = alphabetIndex.get(c);
                    } else if (alphabetIndex.get(c) == null) {
                        continue;
                    }
                    if (index - (i % ALPHABET.length) < 0) {
                        fileWriter.write(ALPHABET[(index - i % ALPHABET.length) + ALPHABET.length]);
                    } else {
                        fileWriter.write(ALPHABET[index - (i % ALPHABET.length)]);
                    }
                }
                if (validate("C:\\JDK\\Bruteforced.txt")) {
                    System.out.println("Текст взломан ключём " + i);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static boolean validate(String file) {
        int conditionTrue = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String text = reader.readLine();
                String[] arr = text.split("[а-я],");
                for (int i = 1; i < arr.length; i++) {
                    if (arr[i].startsWith(" ")) {
                        conditionTrue++;
                    } else {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conditionTrue > 0;
    }
}


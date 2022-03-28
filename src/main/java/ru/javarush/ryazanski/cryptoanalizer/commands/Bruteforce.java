package ru.javarush.ryazanski.cryptoanalizer.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static ru.javarush.ryazanski.cryptoanalizer.CryptaAnalizer.*;

public class Bruteforce {
    public static void bruteforce() throws IOException {
        System.out.println("Введите имя файла для Брутфорса" +
                " или Exit для завершения программы");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        if (fileName.equals("Exit")) {
            System.exit(0);
        }
        if (Files.notExists(Path.of(USER_DIR + fileName))) {
            System.out.println(FILE_NOT_FOUND);
            bruteforce();
        } else {
            for (int i = 0; i < ALPHABET.length; i++) {
                Path fileForBrute = Path.of(USER_DIR + fileName);
                Path brutforcedFile = Path.of(USER_DIR + "Bruteforced.txt");
                if (Files.exists(brutforcedFile)) {
                    Files.delete(brutforcedFile);
                }
                Files.createFile(brutforcedFile);
                try (FileReader fileReader = new FileReader(String.valueOf(fileForBrute));
                     FileWriter fileWriter = new FileWriter(String.valueOf(brutforcedFile))) {
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
                    if (validate(String.valueOf(brutforcedFile))) {
                        System.out.println("Текст взломан ключём " + i+" в файл "+ brutforcedFile);
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

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

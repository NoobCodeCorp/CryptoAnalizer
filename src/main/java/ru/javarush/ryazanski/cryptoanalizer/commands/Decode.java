package ru.javarush.ryazanski.cryptoanalizer.commands;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static ru.javarush.ryazanski.cryptoanalizer.CryptaAnalizer.*;

public class Decode {
    public static void decode() throws IOException {
        System.out.println("Введите имя декодируемого файла(с указанием формата)" +
                " или Exit для завершения программы.");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        if (fileName.equals("Exit")) {
            System.exit(0);
        }
        Path decodingFile = Path.of(USER_DIR + fileName);
        Path decodedFile = Path.of(USER_DIR + "Decoded.txt");
        System.out.println("Введите ключ к закодированному файлу");
        int key = scanner.nextInt();
        if (Files.exists(decodedFile)) {
            Files.delete(decodedFile);
            Files.createFile(decodedFile);
        }
        if (Files.notExists(decodingFile)) {
            System.out.println(FILE_NOT_FOUND);
            decode();
        } else {
            try (FileReader fileReader = new FileReader(String.valueOf(decodingFile));
                 FileWriter fileWriter = new FileWriter(String.valueOf(decodedFile))) {
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
                System.out.println("Error");
            }
            System.out.println("Текст раскодирован в файл "+decodedFile);
        }
    }
}

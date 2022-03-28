package ru.javarush.ryazanski.cryptoanalizer.commands;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static ru.javarush.ryazanski.cryptoanalizer.CryptaAnalizer.*;

public class Encode {
    public static void encode() throws IOException {
        System.out.println("Введите имя кодируемого файла(с указанием расширения)");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        if (fileName.equals("Exit")) {
            System.exit(0);
        }
        Path fileForEncode = Path.of(USER_DIR + fileName);
        Path encodedFile = Path.of(USER_DIR + "Encoded.txt");
        if (Files.notExists(fileForEncode)) {
            System.out.println(FILE_NOT_FOUND);
            encode();
        } else {
            System.out.println("Введите ключ(любое положительное число)");
            int key = scanner.nextInt();
            if (Files.exists(encodedFile)) {
                Files.delete(encodedFile);
                Files.createFile(encodedFile);
            }
            try (FileReader fileReader = new FileReader(String.valueOf(fileForEncode));
                 FileWriter fileWriter = new FileWriter(String.valueOf(encodedFile))) {
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
                System.out.println("Error");
            }
            System.out.println("Текст закодирован в файл "+encodedFile);
        }
    }
}

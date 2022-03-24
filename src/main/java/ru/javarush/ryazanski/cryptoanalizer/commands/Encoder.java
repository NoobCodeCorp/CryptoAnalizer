package ru.javarush.ryazanski.cryptoanalizer.commands;

import ru.javarush.ryazanski.cryptoanalizer.entity.Result;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import ru.javarush.ryazanski.cryptoanalizer.constants.Constants;

public class Encoder implements Action{
    @Override
    public Result execute(String[] parameters) {
        char[] Alphabet = Constants.ALPHABET;
        int key = 23;
        // Path encoded = Files.createFile(Paths.get("C\\JDK\\Encoded.txt"));
        try (FileReader fileReader = new FileReader("text.txt");
             FileWriter fileWriter = new FileWriter("Encoded.txt")) {
            while (fileReader.ready()) {
                char c = (char) fileReader.read();
                int index = Arrays.asList(Alphabet).indexOf(c);
                fileWriter.write(Alphabet[index + key]);
            }
        }
        catch (IOException e) {

        }
        return new Result("Кодирование выполено","OK");
    }
}

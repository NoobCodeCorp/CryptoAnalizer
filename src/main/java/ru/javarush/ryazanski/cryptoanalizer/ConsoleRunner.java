package ru.javarush.ryazanski.cryptoanalizer;

import ru.javarush.ryazanski.cryptoanalizer.entity.Result;

import java.util.Scanner;

public class ConsoleRunner {
    public static void main(String[] args) {
Application application = new Application();
args = getArgs(args);
        Result result = application.run(args);
        System.out.println(result);
    }

    private static String[] getArgs(String[] args) {
        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            //todo
        }
        return args;
    }
}

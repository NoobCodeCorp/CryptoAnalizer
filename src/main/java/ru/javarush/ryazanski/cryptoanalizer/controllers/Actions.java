package ru.javarush.ryazanski.cryptoanalizer.controllers;

import ru.javarush.ryazanski.cryptoanalizer.Exeptions.AppException;
import ru.javarush.ryazanski.cryptoanalizer.commands.*;

public enum Actions {
    ENCODE(new Encoder()),
    DECODE(new Decoder()),
    BRUTEFORCE(new BruteForce()),
    ANALYZE(new Analyzer());


    private final Action action;

    Actions(Action action) {
        this.action = action;
    }
public static Action get(String actionName) {
       return find(actionName);
        }

    public static Action find(String actionName) {
        try {
            Actions value = Actions.valueOf(actionName.toUpperCase());
            Action action = value.action;
            return action;
        } catch (IllegalArgumentException e) {
            throw new AppException("not found" + actionName, e);
        }
    }
}

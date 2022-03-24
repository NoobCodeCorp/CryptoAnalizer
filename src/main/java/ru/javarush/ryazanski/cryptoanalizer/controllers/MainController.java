package ru.javarush.ryazanski.cryptoanalizer.controllers;

import ru.javarush.ryazanski.cryptoanalizer.commands.Action;
import ru.javarush.ryazanski.cryptoanalizer.entity.Result;

public class MainController {
    public Result doAction(String actionName, String[] parameters) {
        Action action = Actions.get(actionName);
        return action.execute(parameters);
    }
}

package ru.javarush.ryazanski.cryptoanalizer.commands;

import ru.javarush.ryazanski.cryptoanalizer.entity.Result;

public interface Action {
    Result execute(String[] parameters);
}

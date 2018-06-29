package ru.rakhimova.javacore.exception;

public class MyArrayDataException extends Exception{

    public MyArrayDataException(int i, int j){
        super("Incorrect value:" + i + "," + j);
    }
}
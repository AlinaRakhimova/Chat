package ru.rakhimova.javacore.entity;

public class Phonebook {
    private static String name;
    private static String phone;


    public Phonebook(String name, String phone){
        this.name = name;
        this.phone = phone;
    }
    
    public static String getName(){
        return name;
    }
}

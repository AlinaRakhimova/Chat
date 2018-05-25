package com.company;

public class Person {

//    public static void main(String[] args) {
//	// write your code here
//        System.out.println("Вывести текст");
//    }
    private String name;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Person(String name, String position, String email, String phone, int salary, int age){
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;

    }

    public int getAge() {
        return age;
    }

    public void printPerson() {
        System.out.println("Данные о сотруднике");
        System.out.println("ФИО: " +  name);
        System.out.println("Должность: " +  position);
        System.out.println("Эл. почта: " +  email);
        System.out.println("Телефон: " +  phone);
        System.out.println("Заработаная плата: " +  salary);
        System.out.println("Возраст: " +  age);
        System.out.println(" ");
    }

}

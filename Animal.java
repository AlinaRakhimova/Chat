package com.company;

public class Animal {

    public void run(double par) {
        System.out.println("Run, " + par);
    }

    public void swim(double par) {
        System.out.println("Swim, " + par);
    }

    public void jump(double par) {
        System.out.println("Jump, " + par);
    }


}

class Cat extends Animal {
    public void run(double par) {
        if ( par <= 200) System.out.println("The cat is running, " + par);
        else System.out.println("The cat can't run more than 200 meters");
    }

    public void swim(double par) {
        System.out.println("I can't swim!!! HELP!");
    }

    public void jump(double par) {
        if ( par <= 2) System.out.println("The cat is jumping, " + par);
        else System.out.println("The cat can't jump more than 2 meters");
    }

}

class Dog extends Animal {
    public void run(double par) {
        if ( par <= 500) System.out.println("The dog is running, " + par);
        else System.out.println("The dog can't run more than 500 meters");
    }

    public void swim(double par) {
        if ( par <= 10) System.out.println("The dog is swimming, " + par);
        else System.out.println("The dog can't swim more than 10 meters");
    }

    public void jump(double par) {
        if ( par <= 0.5) System.out.println("The dog is jumping, " + par);
        else System.out.println("The dog can't jump more than 0.5 meters");
    }

}
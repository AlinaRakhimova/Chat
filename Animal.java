package com.company;

class Main {

    public static void main(String[] args) {
        // write your code here
        Cat cat1 = new Cat();
        cat1.jump(2);
        cat1.run(100);
        cat1.run(500);
        cat1.swim(200);
        System.out.println("");
        Dog dog1 = new Dog();
        dog1.swim(10);
        dog1.jump(1.5);
        dog1.run(1000);
        dog1.run(200);

    }

}

 class Animal {

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
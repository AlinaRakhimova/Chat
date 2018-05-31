/**
 * Java. Level 1. Lesson 7. Example for homework
 * @author Alina Rakhimova
 * @version dated May 31, 2018
 * @link https://github.com/AlinaRakhimova
 */

package com.company;

public class Lesson7 {

    public static void main(String[] args) {
        Cat cat = new Cat("Barsik", 60, false);
        Plate plate = new Plate(50);
        System.out.println(plate);
        cat.eat(plate);

        System.out.println(plate);
        System.out.println(cat);
    }
}

class Cat {
    private String name;
    private int appetite;
    private boolean satiety;

    Cat(String name, int appetite, boolean satiety) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = satiety;
    }

    void eat(Plate plate) {
        satiety = plate.dicreaseFood(appetite);
        setSatiety(satiety);
    }

    void setSatiety(boolean satiety){
        this.satiety = satiety;
    }

    boolean getSatiety(){
        return satiety;
    }


    @Override
    public String toString() {
        return "Cat: "+ name + ". Satiety: " + satiety;
    }
}

class Plate {
    private int food;
    boolean enoughFood = false;

    Plate(int food) {
        this.food = food;
    }

    boolean dicreaseFood(int food) {
        if (this.food >= food) {
            this.food -= food;
            enoughFood = true;
        } else System.out.println("Not enough food. Appetite: " + food);
        return enoughFood;
    }

    @Override
    public String toString() {
        return "Food: " + food;
    }
}


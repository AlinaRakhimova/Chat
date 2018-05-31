/**
 * Java. Level 1. Lesson 7. Example for homework
 * @author Alina Rakhimova
 * @version dated May 31, 2018
 * @link https://github.com/AlinaRakhimova
 */

package com.company;

public class Lesson7 {

    public static void main(String[] args) {
        Cat[] cat = new Cat[]{new Cat("Barsik", 10, false),
                new Cat("Samson", 10, false),
                new Cat("Murzik", 10, false),
                new Cat("Zevs", 10, false),
                new Cat("Kuzya", 20, false)
        };

        Plate plate = new Plate(50, 100);
        System.out.println(plate);
        for (Cat currentСat : cat) {
            currentСat.eat(plate);
            System.out.println(currentСat);
        }
        System.out.println(plate);
        plate.addFood(40);
        System.out.println(plate);
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
    private int capacity; //емкость тарелки
    boolean enoughFood = false;

    Plate(int food, int capacity) {
        this.food = food;
        this.capacity = capacity;
    }

    boolean dicreaseFood(int food) {
        enoughFood = false;
        if (this.food >= food) {
            this.food -= food;
            enoughFood = true;
        } else System.out.println("Not enough food. Appetite for the next cat: " + food);
        return enoughFood;
    }

    void addFood(int food){
        int raz = capacity - this.food;
       if (food<=raz) {
           this.food += food;
       } else this.food += (raz);
    }

    @Override
    public String toString() {
        return "Food: " + food;
    }
}


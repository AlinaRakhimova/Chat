package com.company;

public class Main {

    private static int SIZE = 5;
    public static void main(String[] args) {
        // write your code here
        Person[] persArray = new Person[SIZE]; // Вначале объявляем массив объектов
        persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 40000, 40); // потом для каждой ячейки массива задаем объект
        persArray[1] = new Person("Petrov Pavel", "Engineer", "Petrov@mailbox.com", "892312355", 70000, 41);
        persArray[2] = new Person("Selivanov Vladimir", "Engineer", "Selivanov@mailbox.com", "892312377", 30000, 30);
        persArray[3] = new Person("Pavlov Igor", "Engineer", "Pavlov@mailbox.com", "892312398", 55000, 45);
        persArray[4] = new Person("Kudakov Ivan", "Engineer", "Kudakov@mailbox.com", "892312378", 45000, 31);


        for (int i = 0; i< SIZE; i++) {
            if (persArray[i].getAge() > 40) {
                persArray[i].printPerson();
            }

        }

    }


}

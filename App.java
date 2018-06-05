/**
 * JavaCore. Level 1. Lesson 3. Example of homework
 *
 * @author Alina Rakhimova
 * @version dated June 05, 2018
 * @link https://github.com/AlinaRakhimova
 */

package ru.rakhimova.javacore;

import ru.rakhimova.javacore.entity.Phonebook;

import java.util.*;

public class App
{
    public static void main( String[] args )
    {
        HashSet<String> worlds = new HashSet<String>();
        worlds.add("book");
        worlds.add("book");
        worlds.add("table");
        worlds.add("sun");
        worlds.add("sunset");
        worlds.add("push");
        worlds.add("main");
        worlds.add("book");
        worlds.add("bus");
        worlds.add("sun");

        System.out.println(worlds);




        List<Phonebook> pb = new ArrayList<Phonebook>();
        pb.add(new Phonebook("Ivanov", "5632123"));
        pb.add(new Phonebook("Petrov", "4545151"));
        pb.add(new Phonebook("Vasilev", "7899898"));
        pb.add(new Phonebook("Ivanov", "1245689"));
        pb.add(new Phonebook("Mikulin", "2567889"));
        pb.add(new Phonebook("Kulikov", "7414578"));


        //System.out.println("New Germany Entry: " + pb.get("Germany"));

    }
}

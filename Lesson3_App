/**
 * JavaCore. Level 1. Lesson 3. Example of homework
 *
 * @author Alina Rakhimova
 * @version dated June 07, 2018
 * @link https://github.com/AlinaRakhimova
 */

package ru.rakhimova.javacore;

import ru.rakhimova.javacore.entity.Phonebook;
import java.util.*;

public class App
{
    public static void main( String[] args )
    {
        HashSet<String> setWorlds = new HashSet<>();
        List<String> listWorlds = new ArrayList<>();
        listWorlds.add("book");
        listWorlds.add("book");
        listWorlds.add("table");
        listWorlds.add("sun");
        listWorlds.add("sunset");
        listWorlds.add("push");
        listWorlds.add("main");
        listWorlds.add("book");
        listWorlds.add("bus");
        listWorlds.add("sun");

        for (String world : listWorlds) {
            setWorlds.add(world);
        }
        System.out.println(setWorlds);

        for (String sWorld : setWorlds) {
            int count = 0;
            for (String lWorld : listWorlds) {
                if (sWorld.equals(lWorld)) {
                    count ++;
                }
            }
            System.out.println(sWorld + ":" + count);
        }

        List<Phonebook> pb = new ArrayList<>();
        pb.add(new Phonebook("Ivanov", "5632123"));
        pb.add(new Phonebook("Petrov", "4545151"));
        pb.add(new Phonebook("Vasilev", "7899898"));
        pb.add(new Phonebook("Ivanov", "1245689"));
        pb.add(new Phonebook("Mikulin", "2567889"));
        pb.add(new Phonebook("Kulikov", "7414578"));

        String subscriber = "Ivanov";
        for (Phonebook sub : pb) {
            if (sub.getName().equals(subscriber)) {
                System.out.println(sub);
            }
        }
    }
}

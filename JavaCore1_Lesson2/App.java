/**
 * JavaCore. Level 1. Lesson 2. Example of homework
 *
 * @author Alina Rakhimova
 * @version dated June 03, 2018
 * @link https://github.com/AlinaRakhimova
 */

package ru.rakhimova.javacore;

import ru.rakhimova.javacore.exception.MyArrayDataException;
import ru.rakhimova.javacore.exception.MyArraySizeException;

public class App {
    final static int SIZE = 4;

    public static void main(String[] args){
        final String[][] arrayString = new String[SIZE][SIZE];
        arrayString[0][0] = "2";
        arrayString[0][1] = "yes";
        arrayString[0][2] = "1";
        arrayString[0][3] = "1";
        arrayString[1][0] = "1";
        arrayString[1][1] = "1";
        arrayString[1][2] = "1";
        arrayString[1][3] = "1";
        arrayString[2][0] = "1";
        arrayString[2][1] = "1";
        arrayString[2][2] = "1";
        arrayString[2][3] = "2";
        arrayString[3][0] = "1";
        arrayString[3][1] = "1";
        arrayString[3][2] = "1";
        arrayString[3][3] = "1";
        try{
            System.out.println(stringToInt(arrayString));
        }  catch (MyArrayDataException exception) {
            System.out.println(exception.getMessage());
        } catch (MyArraySizeException exception) {
            System.out.println("Ошибка размера массива: " + exception.getStackTrace());
        }
    }

    public static int stringToInt(String[][] arrayString) throws MyArrayDataException, MyArraySizeException {
        int count = 0;
        if (arrayString.length != SIZE) throw new MyArraySizeException();
        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++){
                if (!arrayString[i][j].matches("\\d+")) throw new MyArrayDataException(i,j);
                count =  Integer.parseInt(arrayString[i][j]) + count;
            }
        }
        return count;
    }
}

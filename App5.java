/**
 * JavaCore. Level 1. Lesson 5. Example of homework
 *
 * @author Alina Rakhimova
 * @version dated June 14, 2018
 * @link https://github.com/AlinaRakhimova
 */

package ru.rakhimova.javacore;

import java.util.Arrays;

public class App {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];
    static float[] a1 = new float[h];
    static float[] a2 = new float[h];

    public static void main(String[] args) {
        countArray();
        countArrayWithThreads();
    }

    public static void countArray () {
        System.out.println("countArray started");
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    public static void countArrayWithThreads () {
        System.out.println("countArrayWithThreads started");
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread thread1 = new Thread(new MyRunnableClass(a1));
        thread1.start();
        Thread thread2 = new Thread(new MyRunnableClass(a2));
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - a);
    }

    static class MyRunnableClass implements Runnable {
        private float[] arr;
        MyRunnableClass(float[] arr){
           this.arr = arr;
        }
        @Override
        public void run() {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
    }
}

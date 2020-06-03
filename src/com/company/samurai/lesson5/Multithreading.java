package com.company.samurai.lesson5;

import java.util.Arrays;

public class Multithreading {
    private static final int SIZE = 10000000;
    private static final int HALF = SIZE / 2;
    private static Float[] oneThreadArr = new Float[SIZE];
    private static Float[] multiThreadArr = new Float[SIZE];

    public static void main(String[] args) {
        Arrays.fill(oneThreadArr, 1.0f);
        Arrays.fill(multiThreadArr, 1.0f);

        long startTime = System.currentTimeMillis();
        calculate(oneThreadArr, 0, SIZE);
        System.out.println("Выполнение для одного потока: " + (System.currentTimeMillis() - startTime) + "мс");

        Float[] arr1 = new Float[HALF];
        Float[] arr2 = new Float[HALF];

        startTime = System.currentTimeMillis();
        System.arraycopy(multiThreadArr, 0, arr1, 0, HALF);

        Thread thread1 = new Thread(() -> calculate(arr1, 0, HALF));
        Thread thread2 = new Thread(() -> calculate(multiThreadArr, HALF, SIZE));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.arraycopy(multiThreadArr, HALF, arr2, 0, HALF);
        System.arraycopy(arr1, 0, multiThreadArr, 0, HALF);
        System.arraycopy(arr2, 0, multiThreadArr, HALF, HALF);
        System.out.println("Выполнение для двух потоков: " + (System.currentTimeMillis() - startTime) + "мс");
    }


    public static synchronized void calculate(Float arr[], int start, int stop) {
        for (int i = start; i < stop; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}

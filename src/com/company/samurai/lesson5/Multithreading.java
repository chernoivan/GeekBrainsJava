package com.company.samurai.lesson5;

public class Multithreading {
    private static final int size = 10000000;
    private static final int half = size / 2;
    private static float[] arr = new float[size];


    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        calculation(arr);
        multithreadingCalculation(arr);
    }

    public static void calculation(float arr[]) {
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        }
        System.out.println("lead time for one thread = " + (System.currentTimeMillis() - a));
    }

    public static void multithreadingCalculation(float arr[]) {
        float[] arr1 = new float[half];
        float[] arr2 = new float[half];
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, half);
        System.arraycopy(arr, half, arr2, 0, half);

        MyThread t1 = new MyThread(arr1);
        MyThread t2 = new MyThread(arr2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        arr1 = t1.getArr();
        arr2 = t2.getArr();

        System.arraycopy(arr1, 0, arr, 0, half);
        System.arraycopy(arr2, 0, arr, half, half);
        System.out.println("lead time for multithreading = " + (System.currentTimeMillis() - a));
    }
}

class MyThread extends Thread {
    private float[] arr;

    MyThread(float[] arr) {
        this.arr = arr;
    }

    float[] getArr() {
        return arr;
    }

    @Override
    public void run() {
        calculate();
    }

    private void calculate() {
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}

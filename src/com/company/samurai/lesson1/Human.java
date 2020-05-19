package com.company.samurai.lesson1;

public class Human implements Entities {
    private boolean success;

    public boolean Run(int length) {
        if (length <= 3000) {
            System.out.println("Человек пробежал " + length + " метров");
            success = true;
        } else {
            System.out.println("Человек не пробежал " + length + " метров");
            success = false;
        }
        return success;
    }

    public boolean Jump(int length) {
        if (length <= 100) {
            System.out.println("Человек перепрыгнул стену высотой " + length + " сантиметров");
            success = true;
        } else {
            System.out.println("Человек не перепрыгнул стену высотой " + length + " сантиметров");
            success = false;
        }
        return success;
    }
}

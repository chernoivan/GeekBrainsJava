package com.company.samurai.lesson1;

public class Cat implements Entities {
    private boolean success;

    public boolean Run(int length) {
        if (length <= 2000) {
            System.out.println("Кот пробежал " + length + " метров");
            success = true;
        } else {
            System.out.println("Кот не пробежал " + length + " метров");
            success = false;
        }
        return success;
    }

    public boolean Jump(int length) {
        if (length <= 150) {
            System.out.println("Кот перепрыгнул стену высотой " + length + " сантиметров");
            success = true;
        } else {
            System.out.println("Кот не перепрыгнул стену высотой " + length + " сантиметров");
            success = false;
        }
        return success;
    }
}

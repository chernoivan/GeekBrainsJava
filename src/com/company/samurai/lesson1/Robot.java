package com.company.samurai.lesson1;

public class Robot implements Entities {
    private boolean success;

    public boolean Run(int length) {
        if (length <= 10000) {
            System.out.println("Робот пробежал " + length + " метров");
            success = true;
        } else {
            System.out.println("Робот не пробежал " + length + " метров");
            success = false;
        }
        return success;
    }

    public boolean Jump(int length) {
        if (length <= 200) {
            System.out.println("Робот перепрыгнул стену высотой " + length + " сантиметров");
            success = true;
        } else {
            System.out.println("Робот не перепрыгнул стену высотой " + length + " сантиметров");
            success = false;
        }
        return success;
    }
}
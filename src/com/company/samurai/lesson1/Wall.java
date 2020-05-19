package com.company.samurai.lesson1;

public class Wall implements Obstacles {
    private int quantity = 5;

    public int ObstacleLength(int arrayIndex) {
        int[] walls = new int[quantity];
        for (int i = 0; i < quantity; i++) {
            walls[i] = 50 + 50 * i;
        }
        return walls[arrayIndex];
    }

    public int getLength() {
        return quantity;
    }
}

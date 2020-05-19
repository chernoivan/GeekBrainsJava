package com.company.samurai.lesson1;

public class RaceTrack implements Obstacles {

    private int quantity = 10;

    public int ObstacleLength(int arrayIndex) {
        int[] raceTracks = new int[quantity];
        for (int i = 0; i < quantity; i++) {
            raceTracks[i] = 1000 + 1000 * i;
        }
        return raceTracks[arrayIndex];
    }

    public int getLength() {
        return quantity;
    }
}

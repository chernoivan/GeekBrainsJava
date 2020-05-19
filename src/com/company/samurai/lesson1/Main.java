package com.company.samurai.lesson1;

public class Main {

    public static void main(String[] args) {

        Obstacles[] obstacles = new Obstacles[2];
        obstacles[0] = new RaceTrack();
        obstacles[1] = new Wall();

        Entities[] entities = new Entities[3];
        entities[0] = new Cat();
        entities[1] = new Human();
        entities[2] = new Robot();

        for (int i = 0; i < entities.length; i++) {
            for (int j = 0; j < obstacles[0].getLength(); j++) {
                if (entities[i].Run(obstacles[0].ObstacleLength(j)) == false)
                    break;
            }
        }

        for (int i = 0; i < entities.length; i++) {
            for (int j = 0; j < obstacles[1].getLength(); j++) {
                if (entities[i].Jump(obstacles[1].ObstacleLength(j)) == false)
                    break;
            }
        }
    }
}

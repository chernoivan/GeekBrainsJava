package com.company.samurai.lesson8;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimeOutExit {
    Timeline idlestage;

    public void start(Stage stage) {
        idlestage = new Timeline(new KeyFrame(Duration.seconds(10), event ->
                ((Stage) stage.getScene().getWindow()).close()));
        idlestage.setCycleCount(1);
        idlestage.play();
    }
    public void stop() {
    }
}

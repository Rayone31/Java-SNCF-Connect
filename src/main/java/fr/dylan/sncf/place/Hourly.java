package fr.dylan.sncf.place;

import java.util.Random;

public class Hourly {

    int[] startHours = {8, 11, 14, 17, 20};
    int[] endHours = new int[5];
    int[] durations = new int[5];

    public static Hourly generateHourly() {
        Hourly hourly = new Hourly();
        Random rand = new Random();
        for (int i = 0; i < hourly.startHours.length; i++) {
            int randomNum = rand.nextInt((23 - hourly.startHours[i]) + 1) + hourly.startHours[i];
            hourly.endHours[i] = randomNum;
            hourly.durations[i] = hourly.endHours[i] - hourly.startHours[i];
        }
        return hourly;
    }

    public int[] getStartHours() {
        return startHours;
    }

    public int[] getEndHours() {
        return endHours;
    }

    public int[] getDurations() {
        return durations;
    }
}
package fr.dylan.sncf.place;

import java.util.Random;

// Hourly class
public class Hourly {

    // Attributes
    int[] startHours = {8, 11, 14, 17, 20};
    int[] endHours = new int[5];
    int[] durations = new int[5];

    // Generate hourly
    public static Hourly generateHourly() {
        Hourly hourly = new Hourly();
        Random rand = new Random();
        for (int i = 0; i < hourly.startHours.length; i++) {

            int duration = rand.nextInt(4) + 1;
            hourly.endHours[i] = hourly.startHours[i] + duration;
            hourly.durations[i] = duration;
        }
        return hourly;
    }

    // Getters
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
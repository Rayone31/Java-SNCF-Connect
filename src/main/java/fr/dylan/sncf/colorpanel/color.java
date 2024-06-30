package fr.dylan.sncf.colorpanel;

public class color {
    private static final String RESET = "\u001B[0m";
    private String colorCode;

    // Constructor
    private color(String colorCode) {
        this.colorCode = colorCode;
    }

    // Print color
    public static void printColor(String message, color colorInstance) {
        System.out.println(colorInstance.colorCode + message + RESET);
    }

    // Red color
    public static color Red() {
        return new color("\u001B[31m");
    }

    // Green color
    public static color Green() {
        return new color("\u001B[32m");
    }

    // Blue color
    public static color Blue() {
        return new color("\u001B[34m");
    }

    // Yellow color
    public static color Yellow() {
        return new color("\u001B[33m");
    }

    // white color
    public static color Reset() {
        return new color(RESET);
    }
}
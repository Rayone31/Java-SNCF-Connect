package fr.dylan.sncf.colorpanel;

public class color {
    private static final String RESET = "\u001B[0m";
    private String colorCode;

    private color(String colorCode) {
        this.colorCode = colorCode;
    }

    public static void printColor(String message, color colorInstance) {
        System.out.println(colorInstance.colorCode + message + RESET);
    }

    public static color Red() {
        return new color("\u001B[31m");
    }

    public static color Green() {
        return new color("\u001B[32m");
    }

    public static color Blue() {
        return new color("\u001B[34m");
    }

    public static color Yellow() {
        return new color("\u001B[33m");
    }

    public static color Reset() {
        return new color(RESET);
    }
}
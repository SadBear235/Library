package org.sadbear.util;

public class Time {

    static long lastFrame = System.nanoTime();
    static float dt = -1;

    // Dt calculation and update function
    public static float dt() {
        return dt;
    }

    // Run at the start of a loop
    public static void updateDt() {
        if (dt == -1) dt = 1f/60;
        else dt = (float) ((System.nanoTime() - lastFrame) / 1E9);
        lastFrame = System.nanoTime();
    }
}

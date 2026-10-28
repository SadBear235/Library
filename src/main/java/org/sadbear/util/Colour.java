package org.sadbear.util;

public class Colour {
    public static int hueToRGB(float hue) {
        hue = hue % 256;
        if (hue < 0) {
            hue = 255 - hue;
        }

        float h = (float)hue / 256;

        float kr = (5 + h * 6) % 6;
        float kg = (3 + h * 6) % 6;
        float kb = (1 + h * 6) % 6;

        int r = (int)(256 * (1 - Math.max(Math.min(kr, Math.min(4f-kr, 1f)), 0f)));
        int g = (int)(256 * (1 - Math.max(Math.min(kg, Math.min(4f-kg, 1f)), 0f)));
        int b = (int)(256 * (1 - Math.max(Math.min(kb, Math.min(4f-kb, 1f)), 0f)));

        return colour(r, g, b);
    }

    public static int colour(int r, int g, int b) {
        if (r > 255) r = 255; else if (r < 0) r = 0;
        if (g > 255) g = 255; else if (g < 0) g = 0;
        if (b > 255) b = 255; else if (b < 0) b = 0;

        return 0xff000000 | (r << 16) | (g << 8) | b;
    }

    public static int colour(int gray) {
        if (gray > 255) gray = 255; else if (gray < 0) gray = 0;

        return 0xff000000 | (gray << 16) | (gray << 8) | gray;
    }
}

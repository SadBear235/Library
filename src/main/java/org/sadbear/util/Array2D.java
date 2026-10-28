package org.sadbear.util;

public class Array2D<E> {
    final Object[] array;

    public final int width, height;

    public Array2D(int width, int height) {
        if (width < 0) throw new IllegalArgumentException("Illegal Width: " + width);
        if (height < 0) throw new IllegalArgumentException("Illegal Height: " + height);

        array = new Object[width * height];

        this.width = width;
        this.height = height;
    }

    public void set(int x, int y, E element) {
        if (x < 0 || x >= width) throw new IllegalArgumentException("Illegal X Position: " + x);
        if (y < 0 || y >= height) throw new IllegalArgumentException("Illegal Y Position: " + x);
        array[y * width + x] = element;
    }

    public E get(int x, int y) {
        if (x < 0 || x >= width) throw new IllegalArgumentException("Illegal X Position: " + x);
        if (y < 0 || y >= height) throw new IllegalArgumentException("Illegal Y Position: " + x);
        return (E) array[y * width + x];
    }

    public boolean checkWithinBounds(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return false;

        return true;
    }
}

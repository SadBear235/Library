package org.sadbear.util;

public class Vec2i {
    public int x, y;

    public Vec2i() {
        x = 0;
        y = 0;
    }

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2i(float x, float y) {
        this.x = (int)x;
        this.y = (int)y;
    }

    public Vec2i(Vec2i i) {
        this.x = i.x;
        this.y = i.y;
    }

    public Vec2i(Vec2f v) {
        this.x = (int)v.x;
        this.y = (int)v.y;
    }

    public Vec2i copy() {
        return new Vec2i(x, y);
    }

    public Vec2i set(int x, int y) {
        this.x = x;
        this.y = y;

        return this;
    }

    public Vec2i set(float x, float y) {
        this.x = (int)x;
        this.y = (int)y;

        return this;
    }

    public Vec2i set(Vec2i i) {
        x = i.x;
        y = i.y;

        return this;
    }

    public Vec2i add(Vec2i i) {
        return new Vec2i(x + i.x, y + i.y);
    }

    public Vec2i add(int x, int y) {
        return new Vec2i(this.x + x, this.y + y);
    }

    public Vec2i addLocal(Vec2i i) {
        x += i.x;
        y += i.y;

        return this;
    }

    public Vec2i addLocal(int x, int y) {
        this.x += x;
        this.y += y;

        return this;
    }

    public Vec2i sub(Vec2i i) {
        return new Vec2i(x - i.x, y - i.y);
    }

    public Vec2i sub(int x, int y) {
        return new Vec2i(this.x - x, this.y - y);
    }

    public Vec2i subLocal(Vec2i i) {
        x -= i.x;
        y -= i.y;

        return this;
    }

    public Vec2i subLocal(int x, int y) {
        this.x -= x;
        this.y -= y;

        return this;
    }

    public Vec2i mult(int scale) {
        return new Vec2i(x * scale, y * scale);
    }

    public Vec2i multLocal(int scale) {
        x *= scale;
        y *= scale;

        return this;
    }

    public Vec2i div(int scale) {
        return new Vec2i(x / scale, y / scale);
    }

    public Vec2i divLocal(int scale) {
        x /= scale;
        y /= scale;

        return this;
    }

    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }

    @Override
    public boolean equals(Object obj) {
        // Check that obj isn't null
        if (obj == null) return false;

        // Check that obj is the Vec2, convert to Vec2 if so
        if (obj.getClass() != this.getClass()) return false;
        final Vec2i v = (Vec2i) obj;

        // Check that x and y values match
        return x == v.x && y == v.y;
    }
}

package org.sadbear.util;

public class Vec2f {
    public float x, y;

    public Vec2f() {
        x = 0;
        y = 0;
    }

    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2f(Vec2i i) {
        x = i.x;
        y = i.y;
    }

    public Vec2f copy() {
        return new Vec2f(x, y);
    }

    public Vec2f set(float x, float y) {
        this.x = x;
        this.y = y;

        return this;
    }

    public Vec2f set(Vec2f v) {
        x = v.x;
        y = v.y;

        return this;
    }

    public static Vec2f fromAngle(float angle) {
        return new Vec2f((float)Math.cos(angle), (float)Math.sin(angle));
    }

    public float mag() {
        return (float)Math.sqrt(x*x + y*y);
    }

    public float magSq() {
        return x*x + y*y;
    }

    public static float dist(Vec2f v1, Vec2f v2) {
        return v1.sub(v2).mag();
    }

    public float dist(Vec2f v) {
        return sub(v).mag();
    }

    public float dot(Vec2f v) {
        return x * v.x + y * v.y;
    }

    public float cross(Vec2f v) {
        return x * v.y - y * v.x;
    }

    public Vec2f normalize() {
        float mag = mag();
        divLocal(mag);
        return this;
    }

    public Vec2f limit(float max) {
        float mag = mag();
        if (mag > max) {
            multLocal(mag / max);
        }
        return this;
    }

    public Vec2f setMag(float max) {
        float mag = mag();
        multLocal(mag / max);
        return this;
    }

    public float heading() {
        return (float)Math.atan2(y, x);
    }

    public Vec2f setHeading(float angle) {
        float mag = mag();
        set(Vec2f.fromAngle(angle).multLocal(mag));
        return this;
    }

    public Vec2f rotate(float theta) {
        float mag = mag();
        float heading = heading();

        set(Vec2f.fromAngle(heading+theta).multLocal(mag));
        return this;
    }

    public static Vec2f lerp(Vec2f v1, Vec2f v2, float amt) {
        return new Vec2f(v1.x + (v2.x - v1.x) * amt, v1.y + (v2.y - v1.y) * amt);
    }

    public Vec2f lerp(Vec2f v, float amt) {
        set(x + (v.x - x) * amt, y + (v.y - y) * amt);
        return this;
    }

    public Vec2f lerp(float x, float y, float amt) {
        set(this.x + (x - this.x) * amt, this.y + (y - this.y) * amt);
        return this;
    }
    
    public static float angleBetween(Vec2f v1, Vec2f v2) {
        float heading1 = v1.heading();
        float heading2 = v2.heading();

        return heading2 - heading1;
    }

    public Vec2f add(Vec2f v) {
        return new Vec2f(x + v.x, y + v.y);
    }

    public Vec2f add(float x, float y) {
        return new Vec2f(this.x + x, this.y + y);
    }

    public Vec2f addLocal(Vec2f v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vec2f addLocal(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec2f sub(Vec2f v) {
        return new Vec2f(x - v.x, y - v.y);
    }

    public Vec2f sub(float x, float y) {
        return new Vec2f(this.x - x, this.y - y);
    }

    public Vec2f subLocal(Vec2f v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    public Vec2f subLocal(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec2f mult(float scale) {
        return new Vec2f(x * scale, y * scale);
    }

    public Vec2f multLocal(float scale) {
        x *= scale;
        y *= scale;
        return this;
    }

    public Vec2f div(float scale) {
        return new Vec2f(x / scale, y / scale);
    }

    public Vec2f divLocal(float scale) {
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
        final Vec2f v = (Vec2f) obj;

        // Check that x and y values match
        return x == v.x && y == v.y;
    }

    public Vec2i toInt() {
        return new Vec2i((int)x, (int)y);
    }

    public float[] array() {
        return new float[] { x, y };
    }
}

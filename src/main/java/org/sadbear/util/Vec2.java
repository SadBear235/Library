package org.sadbear.util;

public class Vec2 {
    public float x;
    public float y;

    public Vec2() {
        x = 0;
        y = 0;
    }

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 copy() {
        return new Vec2(x, y);
    }

    public Vec2 set(float x, float y) {
        this.x = x;
        this.y = y;

        return this;
    }

    public Vec2 set(Vec2 v) {
        x = v.x;
        y = v.y;

        return this;
    }

    public static Vec2 fromAngle(float angle) {
        return new Vec2((float)Math.cos(angle), (float)Math.sin(angle));
    }

    public float mag() {
        return (float)Math.sqrt(x*x + y*y);
    }

    public float magSq() {
        return x*x + y*y;
    }

    public static float dist(Vec2 v1, Vec2 v2) {
        return v1.sub(v2).mag();
    }

    public float dist(Vec2 v) {
        return sub(v).mag();
    }

    public float dot(Vec2 v) {
        return x * v.x + y * v.y;
    }

    public float cross(Vec2 v) {
        return x * v.y - y * v.x;
    }

    public Vec2 normalize() {
        float mag = mag();
        divLocal(mag);
        return this;
    }

    public Vec2 limit(float max) {
        float mag = mag();
        if (mag > max) {
            multLocal(mag / max);
        }
        return this;
    }

    public Vec2 setMag(float max) {
        float mag = mag();
        multLocal(mag / max);
        return this;
    }

    public float heading() {
        return (float)Math.atan2(y, x);
    }

    public Vec2 setHeading(float angle) {
        float mag = mag();
        set(Vec2.fromAngle(angle).multLocal(mag));
        return this;
    }

    public Vec2 rotate(float theta) {
        float mag = mag();
        float heading = heading();

        set(Vec2.fromAngle(heading+theta).multLocal(mag));
        return this;
    }

    public static Vec2 lerp(Vec2 v1, Vec2 v2, float amt) {
        return new Vec2(v1.x + (v2.x - v1.x) * amt, v1.y + (v2.y - v1.y) * amt);
    }

    public Vec2 lerp(Vec2 v, float amt) {
        set(x + (v.x - x) * amt, y + (v.y - y) * amt);
        return this;
    }

    public Vec2 lerp(float x, float y, float amt) {
        set(this.x + (x - this.x) * amt, this.y + (y - this.y) * amt);
        return this;
    }
    
    public static float angleBetween(Vec2 v1, Vec2 v2) {
        float heading1 = v1.heading();
        float heading2 = v2.heading();

        return heading2 - heading1;
    }

    public Vec2 add(Vec2 v) {
        return new Vec2(x + v.x, y + v.y);
    }

    public Vec2 add(float x, float y) {
        return new Vec2(this.x + x, this.y + y);
    }

    public Vec2 addLocal(Vec2 v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vec2 addLocal(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec2 sub(Vec2 v) {
        return new Vec2(x - v.x, y - v.y);
    }

    public Vec2 sub(float x, float y) {
        return new Vec2(this.x - x, this.y - y);
    }

    public Vec2 subLocal(Vec2 v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    public Vec2 subLocal(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec2 mult(float scale) {
        return new Vec2(x * scale, y * scale);
    }

    public Vec2 multLocal(float scale) {
        x *= scale;
        y *= scale;
        return this;
    }

    public Vec2 div(float scale) {
        return new Vec2(x / scale, y / scale);
    }

    public Vec2 divLocal(float scale) {
        x /= scale;
        y /= scale;
        return this;
    }

    public void print() {
        System.out.println(x + ", " + y);
    }
}

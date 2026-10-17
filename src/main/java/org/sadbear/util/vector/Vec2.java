package org.sadbear.util.vector;

public class Vec2 {
    public double x, y;

    public Vec2() {
        x = 0;
        y = 0;
    }

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(Vec2i i) {
        x = i.x;
        y = i.y;
    }

    public Vec2 copy() {
        return new Vec2(x, y);
    }

    public Vec2 invert() {
        return new Vec2(-x, -y);
    }

    public Vec2 invertLocal() {
        this.x = -x;
        this.y = -y;

        return this;
    }

    public Vec2 set(double x, double y) {
        this.x = x;
        this.y = y;

        return this;
    }

    public Vec2 set(Vec2 v) {
        x = v.x;
        y = v.y;

        return this;
    }

    public static Vec2 fromAngle(double angle) {
        return new Vec2(Math.cos(angle), Math.sin(angle));
    }

    public double mag() {
        return Math.sqrt(x*x + y*y);
    }

    public double magSq() {
        return x*x + y*y;
    }

    public static double dist(Vec2 v1, Vec2 v2) {
        return v1.sub(v2).mag();
    }

    public double dist(Vec2 v) {
        return sub(v).mag();
    }

    public double dot(Vec2 v) {
        return x * v.x + y * v.y;
    }

    public double cross(Vec2 v) {
        return x * v.y - y * v.x;
    }

    public Vec2 normalize() {
        double mag = mag();
        divLocal(mag);
        return this;
    }

    public Vec2 limit(double max) {
        double mag = mag();
        if (mag > max) {
            multLocal(mag / max);
        }
        return this;
    }

    public Vec2 setMag(double max) {
        double mag = mag();
        multLocal(mag / max);
        return this;
    }

    public double heading() {
        return Math.atan2(y, x);
    }

    public Vec2 setHeading(double angle) {
        double mag = mag();
        set(Vec2.fromAngle(angle).multLocal(mag));
        return this;
    }

    public Vec2 rotate(double theta) {
        double mag = mag();
        double heading = heading();

        set(Vec2.fromAngle(heading+theta).multLocal(mag));
        return this;
    }

    public static Vec2 lerp(Vec2 v1, Vec2 v2, double amt) {
        return new Vec2(v1.x + (v2.x - v1.x) * amt, v1.y + (v2.y - v1.y) * amt);
    }

    public Vec2 lerp(Vec2 v, double amt) {
        set(x + (v.x - x) * amt, y + (v.y - y) * amt);
        return this;
    }

    public Vec2 lerp(double x, double y, double amt) {
        set(this.x + (x - this.x) * amt, this.y + (y - this.y) * amt);
        return this;
    }
    
    public static double angleBetween(Vec2 v1, Vec2 v2) {
        double heading1 = v1.heading();
        double heading2 = v2.heading();

        return heading2 - heading1;
    }

    public Vec2 add(Vec2 v) {
        return new Vec2(x + v.x, y + v.y);
    }

    public Vec2 add(double x, double y) {
        return new Vec2(this.x + x, this.y + y);
    }

    public Vec2 addLocal(Vec2 v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vec2 addLocal(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec2 sub(Vec2 v) {
        return new Vec2(x - v.x, y - v.y);
    }

    public Vec2 sub(double x, double y) {
        return new Vec2(this.x - x, this.y - y);
    }

    public Vec2 subLocal(Vec2 v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    public Vec2 subLocal(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec2 mult(double scale) {
        return new Vec2(x * scale, y * scale);
    }

    public Vec2 multLocal(double scale) {
        x *= scale;
        y *= scale;
        return this;
    }

    public Vec2 div(double scale) {
        return new Vec2(x / scale, y / scale);
    }

    public Vec2 divLocal(double scale) {
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
        final Vec2 v = (Vec2) obj;

        // Check that x and y values match
        return x == v.x && y == v.y;
    }

    public Vec2i toInt() {
        return new Vec2i((int)x, (int)y);
    }

    public double[] array() {
        return new double[] { x, y };
    }
}

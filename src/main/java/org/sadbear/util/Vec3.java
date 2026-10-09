package org.sadbear.util;

public class Vec3 {
    public double x;
    public double y;
    public double z;

    public Vec3() {
        x = 0;
        y = 0;
    }

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3 set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    public Vec3 set(Vec3 v) {
        x = v.x;
        y = v.y;
        z = v.z;

        return this;
    }

    public Vec3 copy() {
        return new Vec3(x, y, z);
    }

    public double mag() {
        return (double)Math.sqrt(x*x + y*y + z*z);
    }

    public double magSq() {
        return x*x + y*y + z*z;
    }

    public static double dist(Vec3 v1, Vec3 v2) {
        return v1.sub(v2).mag();
    }

    public double dist(Vec3 v) {
        return sub(v).mag();
    }

    public double dot(Vec3 v) {
        return x * v.x + y * v.y + x * v.x;
    }

    public Vec3 cross(Vec3 v) {
        return new Vec3(y * v.z - v.y * z, z * v.x - v.z * x, x * v.y - v.x * y);
    }

    public Vec3 normalize() {
        double mag = mag();
        if (mag == 0) return this;
        divLocal(mag);
        return this;
    }

    public Vec3 limit(double max) {
        double mag = mag();
        if (mag > max) {
            multLocal(mag / max);
        }
        return this;
    }

    public Vec3 setMag(double max) {
        double mag = mag();
        multLocal(mag / max);
        return this;
    }

    public static Vec3 lerp(Vec3 v1, Vec3 v2, double amt) {
        return new Vec3(v1.x + (v2.x - v1.x) * amt, v1.y + (v2.y - v1.y) * amt, v1.z + (v2.z - v1.z) * amt);
    }

    public Vec3 lerp(Vec3 v, double amt) {
        set(x + (v.x - x) * amt, y + (v.y - y) * amt, z + (v.z - z) * amt);
        return this;
    }

    public Vec3 lerp(double x, double y, double z, double amt) {
        set(this.x + (x - this.x) * amt, this.y + (y - this.y) * amt, this.z + (z - this.z) * amt);
        return this;
    }

    // Taken from PVector implementation for processing
    public static double angleBetween(Vec3 v1, Vec3 v2) {
        if (v1.x == 0 && v1.y == 0 && v1.z == 0) return 0f;
        if (v2.x == 0 && v2.y == 0 && v2.z == 0) return 0f;

        double dot = v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
        double v1mag = Math.sqrt(v1.x * v1.x + v1.y * v1.y + v1.z * v1.z);
        double v2mag = Math.sqrt(v2.x * v2.x + v2.y * v2.y + v2.z * v2.z);
        // This should be a number between -1 and 1, since it's "normalized"
        double amt = dot / (v1mag * v2mag);
        if (amt <= -1) {
            return (double)Math.PI;
        } else if (amt >= 1) {
            return 0;
        }
        return (double) Math.acos(amt);
    }

    public Vec3 add(Vec3 v) {
        return new Vec3(x + v.x, y + v.y, z + v.z);
    }

    public Vec3 add(double x, double y, double z) {
        return new Vec3(this.x + x, this.y + y, this.z + z);
    }

    public Vec3 addLocal(Vec3 v) {
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    public Vec3 addLocal(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3 sub(Vec3 v) {
        return new Vec3(x - v.x, y - v.y, z - v.z);
    }

    public Vec3 sub(double x, double y, double z) {
        return new Vec3(this.x - x, this.y - y, this.z - z);
    }

    public Vec3 subLocal(Vec3 v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
        return this;
    }

    public Vec3 subLocal(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3 mult(double scale) {
        return new Vec3(x * scale, y * scale, z * scale);
    }

    public Vec3 multLocal(double scale) {
        x *= scale;
        y *= scale;
        z *= scale;
        return this;
    }

    public Vec3 div(double scale) {
        return new Vec3(x / scale, y / scale, z / scale);
    }

    public Vec3 divLocal(double scale) {
        x /= scale;
        y /= scale;
        z /= scale;
        return this;
    }

    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z;
    }

    @Override
    public boolean equals(Object obj) {
        // Check that obj isn't null
        if (obj == null) return false;

        // Check that obj is the Vec3, convert to Vec2 if so
        if (obj.getClass() != this.getClass()) return false;
        final Vec3 v = (Vec3) obj;

        // Check that x, y and z values match
        return x == v.x && y == v.y && z == v.z;
    }
}

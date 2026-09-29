package org.sadbear.util;

public class Vec3 {
    public float x;
    public float y;
    public float z;

    public Vec3() {
        x = 0;
        y = 0;
    }

    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3 set(float x, float y, float z) {
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

    public float mag() {
        return (float)Math.sqrt(x*x + y*y + z*z);
    }

    public float magSq() {
        return x*x + y*y + z*z;
    }

    public static float dist(Vec3 v1, Vec3 v2) {
        return v1.sub(v2).mag();
    }

    public float dist(Vec3 v) {
        return sub(v).mag();
    }

    public float dot(Vec3 v) {
        return x * v.x + y * v.y + x * v.x;
    }

    public Vec3 cross(Vec3 v) {
        return new Vec3(y * v.z - v.y * z, z * v.x - v.z * x, x * v.y - v.x * y);
    }

    public Vec3 normalize() {
        float mag = mag();
        divLocal(mag);
        return this;
    }

    public Vec3 limit(float max) {
        float mag = mag();
        if (mag > max) {
            multLocal(mag / max);
        }
        return this;
    }

    public Vec3 setMag(float max) {
        float mag = mag();
        multLocal(mag / max);
        return this;
    }

    public static Vec3 lerp(Vec3 v1, Vec3 v2, float amt) {
        return new Vec3(v1.x + (v2.x - v1.x) * amt, v1.y + (v2.y - v1.y) * amt, v1.z + (v2.z - v1.z) * amt);
    }

    public Vec3 lerp(Vec3 v, float amt) {
        set(x + (v.x - x) * amt, y + (v.y - y) * amt, z + (v.z - z) * amt);
        return this;
    }

    public Vec3 lerp(float x, float y, float z, float amt) {
        set(this.x + (x - this.x) * amt, this.y + (y - this.y) * amt, this.z + (z - this.z) * amt);
        return this;
    }

    // Taken from PVector implementation for processing
    public static float angleBetween(Vec3 v1, Vec3 v2) {
        if (v1.x == 0 && v1.y == 0 && v1.z == 0) return 0f;
        if (v2.x == 0 && v2.y == 0 && v2.z == 0) return 0f;

        double dot = v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
        double v1mag = Math.sqrt(v1.x * v1.x + v1.y * v1.y + v1.z * v1.z);
        double v2mag = Math.sqrt(v2.x * v2.x + v2.y * v2.y + v2.z * v2.z);
        // This should be a number between -1 and 1, since it's "normalized"
        double amt = dot / (v1mag * v2mag);
        if (amt <= -1) {
            return (float)Math.PI;
        } else if (amt >= 1) {
            return 0;
        }
        return (float) Math.acos(amt);
    }

    public Vec3 add(Vec3 v) {
        return new Vec3(x + v.x, y + v.y, z + v.z);
    }

    public Vec3 add(float x, float y, float z) {
        return new Vec3(this.x + x, this.y + y, this.z + z);
    }

    public Vec3 addLocal(Vec3 v) {
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    public Vec3 addLocal(float x, float y) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3 sub(Vec3 v) {
        return new Vec3(x - v.x, y - v.y, z - v.z);
    }

    public Vec3 sub(float x, float y, float z) {
        return new Vec3(this.x - x, this.y - y, this.z - z);
    }

    public Vec3 subLocal(Vec3 v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
        return this;
    }

    public Vec3 subLocal(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3 mult(float scale) {
        return new Vec3(x * scale, y * scale, z * scale);
    }

    public Vec3 multLocal(float scale) {
        x *= scale;
        y *= scale;
        z *= scale;
        return this;
    }

    public Vec3 div(float scale) {
        return new Vec3(x / scale, y / scale, z / scale);
    }

    public Vec3 divLocal(float scale) {
        x /= scale;
        y /= scale;
        z /= scale;
        return this;
    }

    public void print() {
        System.out.println(x + ", " + y + ", " + z);
    }
}

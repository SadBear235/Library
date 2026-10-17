package org.sadbear.util.vector;

public class Vec3f {
    public float x;
    public float y;
    public float z;

    public Vec3f() {
        x = 0;
        y = 0;
    }

    public Vec3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3f set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    public Vec3f set(Vec3f v) {
        x = v.x;
        y = v.y;
        z = v.z;

        return this;
    }

    public Vec3f copy() {
        return new Vec3f(x, y, z);
    }

    public Vec3f invert() {
        return new Vec3f(-x, -y, -z);
    }

    public Vec3f invertLocal() {
        this.x = -x;
        this.y = -y;
        this.z = -z;

        return this;
    }

    public float mag() {
        return (float)Math.sqrt(x*x + y*y + z*z);
    }

    public float magSq() {
        return x*x + y*y + z*z;
    }

    public static float dist(Vec3f v1, Vec3f v2) {
        return v1.sub(v2).mag();
    }

    public float dist(Vec3f v) {
        return sub(v).mag();
    }

    public float dot(Vec3f v) {
        return x * v.x + y * v.y + z * v.z;
    }

    public Vec3f cross(Vec3f v) {
        return new Vec3f(y * v.z - v.y * z, z * v.x - v.z * x, x * v.y - v.x * y);
    }

    public Vec3f normalize() {
        float mag = mag();
        if (mag == 0) return this;
        divLocal(mag);
        return this;
    }

    public Vec3f limit(float max) {
        float mag = mag();
        if (mag > max) {
            multLocal(mag / max);
        }
        return this;
    }

    public Vec3f setMag(float max) {
        float mag = mag();
        multLocal(mag / max);
        return this;
    }

    public static Vec3f lerp(Vec3f v1, Vec3f v2, float amt) {
        return new Vec3f(v1.x + (v2.x - v1.x) * amt, v1.y + (v2.y - v1.y) * amt, v1.z + (v2.z - v1.z) * amt);
    }

    public Vec3f lerp(Vec3f v, float amt) {
        set(x + (v.x - x) * amt, y + (v.y - y) * amt, z + (v.z - z) * amt);
        return this;
    }

    public Vec3f lerp(float x, float y, float z, float amt) {
        set(this.x + (x - this.x) * amt, this.y + (y - this.y) * amt, this.z + (z - this.z) * amt);
        return this;
    }

    // Taken from PVector implementation for processing
    public static float angleBetween(Vec3f v1, Vec3f v2) {
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

    public Vec3f add(Vec3f v) {
        return new Vec3f(x + v.x, y + v.y, z + v.z);
    }

    public Vec3f add(float x, float y, float z) {
        return new Vec3f(this.x + x, this.y + y, this.z + z);
    }

    public Vec3f addLocal(Vec3f v) {
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    public Vec3f addLocal(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3f sub(Vec3f v) {
        return new Vec3f(x - v.x, y - v.y, z - v.z);
    }

    public Vec3f sub(float x, float y, float z) {
        return new Vec3f(this.x - x, this.y - y, this.z - z);
    }

    public Vec3f subLocal(Vec3f v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
        return this;
    }

    public Vec3f subLocal(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3f mult(float scale) {
        return new Vec3f(x * scale, y * scale, z * scale);
    }

    public Vec3f multLocal(float scale) {
        x *= scale;
        y *= scale;
        z *= scale;
        return this;
    }

    public Vec3f div(float scale) {
        return new Vec3f(x / scale, y / scale, z / scale);
    }

    public Vec3f divLocal(float scale) {
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
        final Vec3f v = (Vec3f) obj;

        // Check that x, y and z values match
        return x == v.x && y == v.y && z == v.z;
    }
}

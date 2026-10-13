package org.sadbear.util;

public class Vec3i {
    public int x, y, z;

    public Vec3i() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vec3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3i(float x, float y, float z) {
        this.x = (int)x;
        this.y = (int)y;
        this.z = (int)z;
    }

    public Vec3i(Vec3i i) {
        this.x = i.x;
        this.y = i.y;
        this.z = i.z;
    }

    public Vec3i(Vec3f v) {
        this.x = (int)v.x;
        this.y = (int)v.y;
        this.z = (int)v.z;
    }

    public Vec3i copy() {
        return new Vec3i(x, y, z);
    }

    public Vec3i invert() {
        return new Vec3i(-x, -y, -z);
    }

    public Vec3i invertLocal() {
        this.x = -x;
        this.y = -y;
        this.z = -z;

        return this;
    }

    public Vec3i set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    public Vec3i set(float x, float y, float z) {
        this.x = (int)x;
        this.y = (int)y;
        this.z = (int)z;

        return this;
    }

    public Vec3i set(Vec3i i) {
        x = i.x;
        y = i.y;
        z = i.z;

        return this;
    }

    public Vec3i add(Vec3i i) {
        return new Vec3i(x + i.x, y + i.y, z + i.z);
    }

    public Vec3i add(int x, int y) {
        return new Vec3i(this.x + x, this.y + y, this.z + z);
    }

    public Vec3i addLocal(Vec3i i) {
        x += i.x;
        y += i.y;
        z += i.z;

        return this;
    }

    public Vec3i addLocal(int x, int y, int z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    public Vec3i sub(Vec3i i) {
        return new Vec3i(x - i.x, y - i.y, z - i.z);
    }

    public Vec3i sub(int x, int y, int z) {
        return new Vec3i(this.x - x, this.y - y, this.z - z);
    }

    public Vec3i subLocal(Vec3i i) {
        x -= i.x;
        y -= i.y;
        z -= i.z;

        return this;
    }

    public Vec3i subLocal(int x, int y, int z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    public Vec3i mult(int scale) {
        return new Vec3i(x * scale, y * scale, z * scale);
    }

    public Vec3i multLocal(int scale) {
        x *= scale;
        y *= scale;
        z *= scale;

        return this;
    }

    public Vec3i div(int scale) {
        return new Vec3i(x / scale, y / scale, z / scale);
    }

    public Vec3i divLocal(int scale) {
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

        // Check that obj is the Vec2, convert to Vec2 if so
        if (obj.getClass() != this.getClass()) return false;
        final Vec3i v = (Vec3i) obj;

        // Check that x and y values match
        return x == v.x && y == v.y;
    }
}

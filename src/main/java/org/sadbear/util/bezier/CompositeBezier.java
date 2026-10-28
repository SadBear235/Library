package org.sadbear.util.bezier;

import org.sadbear.util.vector.Vec2f;

import java.util.ArrayList;

public class CompositeBezier {
    private final ArrayList<UniformBezier> beziers = new ArrayList<>();

    int size = 0;

    boolean lineDebug = true;

    public CompositeBezier(Vec2f a, Vec2f b, Vec2f c) {
        beziers.add(new QuadraticBezier(a, b, c));
        size = 1;
    }

    public CompositeBezier(Vec2f a, Vec2f b, Vec2f c, Vec2f d) {
        beziers.add(new CubicBezier(a, b, c, d));
        size = 1;
    }

    public void addQuadraticBezier(Vec2f b, Vec2f c) {
        beziers.add(new QuadraticBezier(getLastControlPoint(), b, c).setOffset(size));
        size++;
    }

    public void addCubicBezier(Vec2f b, Vec2f c, Vec2f d) {
        beziers.add(new CubicBezier(getLastControlPoint(), b, c, d).setOffset(size));
        size++;
    }

    private Vec2f getLastControlPoint() {
        UniformBezier b = beziers.get(size-1);

        if (b.getClass() == QuadraticBezier.class) {
            return b.getControlPoint(2);
        } else if (b.getClass() == CubicBezier.class) {
            return b.getControlPoint(3);
        }

        // Return null if neither
        return null;
    }

    public Vec2f point(float t) {
        return getCurve(t).point(t);
    }

    public float calculateNextPoint(float t, float length) {
        float tCurr = getCurve(t).calculateNextPoint(t, length);
        while (tCurr - getCurveNum(t) > 1 && t < 1) {
            length -= getCurve(t).getLength(t, 1);
            t = (float) Math.ceil(t);

            tCurr = getCurve(t).calculateNextPoint(t, length);
        }

        return tCurr;
    }

    UniformBezier getCurve(float t) {
        return getCurve(getCurveNum(t));
    }

    public UniformBezier getCurve(int index) {
        return beziers.get(index);
    }

    public void checkResize() {
        for (UniformBezier b : beziers) {
            b.checkResize();
        }
    }

    int getCurveNum(float t) {
        int num = (int)Math.floor(t);
        if (num >= size) return size-1;
        return Math.max(num, 0);
    }

    public float getMaxT() {
        return size;
    }

    public void drawDebug(float resolution) {
        // Resolution is the number of points
        Vec2f lastPoint;
        Vec2f point = getCurve(0).getControlPoint(0);

        float t = 0;
        for (int i = 0; i < resolution; i++) {
            t = (i + 1) / resolution;

            lastPoint = point;
            point = point(t);

            if (lastPoint != null && point != null) {
                //if (lineDebug) Render.line(lastPoint, point);
                //else Render.point(point);
            }
        }

        System.err.println("Rendering requires an instantiated object now, temporary solution lol");
    }

    public void drawDebugSpacing(float distance) {
        // Distance is the distance between points
        Vec2f lastPoint;
        Vec2f point = getCurve(0).getControlPoint(0);

        float t = 0;
        while (t <= size) {
            lastPoint = point;
            point = point(t);

            if (lastPoint != null && point != null) {
                //if (lineDebug) Render.line(lastPoint, point);
                //else Render.point(point);
            }

            t = calculateNextPoint(t, distance);
        }

        // Make sure the last point gets rendered
        lastPoint = point;
        point = point(t);

        if (lastPoint != null && point != null) {
            //if (lineDebug) Render.line(lastPoint, point);
            //else Render.point(point);
        }

        System.err.println("Rendering requires an instantiated object now, temporary solution lol");
    }

    public void setLineDebugMode(boolean mode) {
        lineDebug = mode;
    }
}
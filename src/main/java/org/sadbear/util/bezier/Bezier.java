package org.sadbear.util.bezier;

import org.sadbear.util.Render;
import org.sadbear.util.vector.Vec2f;

import java.util.Arrays;
import java.util.List;

// Implementation of an n-numbered point bezier curve
public class Bezier {
    Vec2f[] points;
    int pointNum = 0;

    // Iterations to perform when calculating length
    int accuracy = 100;

    float length = 0;
    boolean calculateLength = false;

    boolean lineDebug = true;

    public Bezier(Vec2f... points) {
        this.points = points;
    }

    public float getLength() {
        if (calculateLength) calculateLength();

        return length;
    }

    public Vec2f getControlPoint(int index) {
        return points[index];
    }

    public Vec2f[] getControlPoints() {
        return points;
    }

    public void setPoint(int index, Vec2f point) {
        points[index].set(point);
        calculateLength = true;
    }

    public void addPoint(Vec2f point) {
        List<Vec2f> pointList = Arrays.asList(points);
        pointList.add(point);
        points = pointList.toArray(new Vec2f[0]);

        calculateLength = true;
    }

    public void insertPoint(int index, Vec2f point) {
        List<Vec2f> pointList = Arrays.asList(points);
        pointList.add(index, point);
        points = pointList.toArray(new Vec2f[0]);

        calculateLength = true;
    }

    public void removePoint(int index) {
        List<Vec2f> pointList = Arrays.asList(points);
        pointList.remove(index);
        points = pointList.toArray(new Vec2f[0]);

        calculateLength = true;
    }

    public Vec2f point(float t) {
        if (t <= 0) return points[0];
        if (t >= 1) return points[points.length-1];

        pointNum = points.length;

        Vec2f[] lastPoints = points;

        for (int i = 0; i < pointNum - 1; i++) {
            Vec2f[] currentPoints = new Vec2f[pointNum - i - 1];

            for (int e = 0; e < pointNum - i - 1; e++) {
                currentPoints[e] = lastPoints[e].add(lastPoints[e + 1].sub(lastPoints[e]).mult(t));
            }

            lastPoints = currentPoints;
        }

        // Return the result
        return lastPoints[0];
    }

    public void calculateLength() {
        float t = 0;
        float len = 0;

        Vec2f lastPoint;
        Vec2f point = new Vec2f();
        while (t <= 1) {
            lastPoint = point;
            point = this.point(t);

            t += 1f/accuracy;

            len += point.dist(lastPoint);
        }

        length = len;
        calculateLength = false;
    }

    public void drawDebug(float resolution) {
        // Resolution is the number of points
        Vec2f lastPoint;
        Vec2f point = getControlPoint(0);

        float t = 0;
        for (int i = 0; i < resolution; i++) {
            t = (i + 1) / resolution;

            lastPoint = point;
            point = point(t);

            if (lastPoint != null && point != null) {
                if (lineDebug) Render.line(lastPoint, point);
                else Render.point(point);
            }
        }
    }

    public void setLineDebugMode(boolean mode) {
        lineDebug = mode;
    }
}

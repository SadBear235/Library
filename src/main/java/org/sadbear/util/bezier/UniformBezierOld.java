package org.sadbear.util.bezier;

import org.sadbear.util.Render;
import org.sadbear.util.vector.Vec2f;

// Creates a bezier curve that can
public abstract class UniformBezierOld extends BezierOld {
    // Vectors used to calculate next point
    private final Vec2f[] vec;

    private Vec2f[] oldPoints;

    float offset = 0;

    public UniformBezierOld(Vec2f... points) {
        super(points);

        updatePoints();

        vec = new Vec2f[points.length-1];

        resizeCalculations();
    }

    protected void setVec(int index, Vec2f vec) {
        this.vec[index] = vec;
    }

    @Override
    public Vec2f point(float t) {
        return super.point(t - offset);
    }

    public UniformBezierOld setOffset(float offset) {
        this.offset = offset;

        return this;
    }

    public float getLength(float t1, float t2) {
        return (t2 - t1) * calculateLength(t1 - offset);
    }

    public float calculateNextPoint(float t, float length) {
        t -= offset;

        int iter = 10;
        while (length > 1) {
            length *= 0.1f;
            iter *= 10;
        }
        length *= 0.1f;
        for (int i = 0; i < iter; i++) {
            t += calculateLength(t, length);
        }
        return t + offset;
    }

    private float calculateLength(float t) {
        Vec2f v = new Vec2f();
        for (int i = 0; i < vec.length; i++) {
            v.addLocal(vec[i].mult((float) Math.pow(t, vec.length-i-1)));
        }
        return v.mag();
    }

    private float calculateLength(float t, float length) {
        return length / calculateLength(t);
    }

    public void checkResize() {
        for (int i = 0; i < points.length; i++) {
            if (!points[i].equals(oldPoints[i])) {
                resizeCalculations();
                updatePoints();
                return;
            }
        }
    }

    private void updatePoints() {
        if (oldPoints == null) oldPoints = new Vec2f[points.length];

        for (int e = 0; e < points.length; e++) {
            oldPoints[e] = points[e].copy();
        }
    }

    abstract void resizeCalculations();

    @Override
    public void setPoint(int index, Vec2f point) {
        super.setPoint(index, point);

        resizeCalculations();
    }

    @Override
    public void addPoint(Vec2f point) {

    }

    @Override
    public void insertPoint(int index, Vec2f point) {

    }

    @Override
    public void removePoint(int index) {

    }

    public void drawDebugSpacing(float distance) {
        // Distance is the distance between points
        Vec2f lastPoint;
        Vec2f point = getControlPoint(0);

        float t = 0;
        while (t <= 1) {
            lastPoint = point;
            point = point(t);

            if (lastPoint != null && point != null) {
                if (lineDebug) Render.line(lastPoint, point);
                else Render.point(point);
            }

            t = calculateNextPoint(t, distance);
        }
    }
}

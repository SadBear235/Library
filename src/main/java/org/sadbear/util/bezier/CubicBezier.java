package org.sadbear.util.bezier;

import org.sadbear.util.vector.Vec2f;

// Implementation of a 3 point bezier curve
public class CubicBezier extends UniformBezier {
    public CubicBezier(Vec2f a, Vec2f b, Vec2f c, Vec2f d) {
        super(a, b, c, d);
    }

    void resizeCalculations() {
        Vec2f a = getControlPoint(0);
        Vec2f b = getControlPoint(1);
        Vec2f c = getControlPoint(2);
        Vec2f d = getControlPoint(3);

        setVec(0, a.mult(-3).add(b.mult(9)).sub(c.mult(9)).add(d.mult(3)));
        setVec(1, a.mult(6).sub(b.mult(12)).add(c.mult(6)));
        setVec(2, a.mult(-3).add(b.mult(3)));
    }
}

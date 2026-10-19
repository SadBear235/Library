package org.sadbear.util.bezier;

import org.sadbear.util.vector.Vec2f;

// Implementation of a 4 point bezier curve
public class QuadraticBezier extends UniformBezier {
    public QuadraticBezier(Vec2f a, Vec2f b, Vec2f c) {
        super(a, b, c);
    }

    @Override
    void resizeCalculations() {
        Vec2f a = getControlPoint(0);
        Vec2f b = getControlPoint(1);
        Vec2f c = getControlPoint(2);

        setVec(0, a.mult(2).sub(b.mult(4)).add(c.mult(2)));
        setVec(1, a.mult(-2).add(b.mult(2)));
    }
}

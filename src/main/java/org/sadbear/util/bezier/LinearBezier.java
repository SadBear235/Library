package org.sadbear.util.bezier;

import org.sadbear.util.vector.Vec2f;

public class LinearBezier extends UniformBezier {

    public LinearBezier(Vec2f a, Vec2f b) {
        super(a, b);
    }

    @Override
    void resizeCalculations() {
        Vec2f a = getControlPoint(0);
        Vec2f b = getControlPoint(1);

        setVec(0, a.sub(b));
    }
}

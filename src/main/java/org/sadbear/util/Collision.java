package org.sadbear.util;

import org.sadbear.util.vector.Vec2f;

public class Collision {
    // rP = rectPos, rS = rectSize, pP = pointPos
    public static boolean rectCollision(Vec2f rP, Vec2f rS, Vec2f pP) {
        return rP.x < pP.x && rP.x + rS.x > pP.x && rP.y < pP.y && rP.y + rS.y > pP.y;
    }
}

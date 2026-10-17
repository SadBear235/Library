package org.sadbear.util.debug;

import org.sadbear.util.Render;
import org.sadbear.util.vector.Vec2f;

import java.util.ArrayList;

import static processing.core.PConstants.*;

public class DebugText {
    static int fill = 0;
    static int textSize = 24;

    static float x = 10;
    static float y = 10;

    static int alignX = LEFT;
    static int alignY = TOP;

    static ArrayList<String> strings = new ArrayList<>();

    static public void add(Object... objects) {
        for (Object o : objects) {
            strings.add(o.toString());
        }
    }

    static public void draw() {
        Render.fill(fill);
        Render.textSize(textSize);
        Render.textAlign(alignX, alignY);

        Vec2f pos = new Vec2f();
        for (int i = 0; i < strings.size(); i++) {
            pos.set(x, y + i * textSize);

            Render.text(strings.get(i), pos);
        }

        strings.clear();
    }
}
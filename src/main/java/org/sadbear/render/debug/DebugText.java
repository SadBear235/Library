package org.sadbear.render.debug;

import org.sadbear.render.Render;
import org.sadbear.util.vector.Vec2f;

import java.util.ArrayList;

import static processing.core.PConstants.*;

public class DebugText {
    Render render;

    int fill = 0;
    int textSize = 24;

    float x = 10;
    float y = 10;

    int alignX = LEFT;
    int alignY = TOP;

    ArrayList<String> strings = new ArrayList<>();

    public DebugText(Render render) {
        this.render = render;
    }

    public void add(Object... objects) {
        for (Object o : objects) {
            strings.add(o.toString());
        }
    }

    public void draw() {
        render.fill(fill);
        render.textSize(textSize);
        render.textAlign(alignX, alignY);

        Vec2f pos = new Vec2f();
        for (int i = 0; i < strings.size(); i++) {
            pos.set(x, y + i * textSize);

            render.text(strings.get(i), pos);
        }

        strings.clear();
    }
}
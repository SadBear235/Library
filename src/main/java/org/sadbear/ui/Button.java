package org.sadbear.ui;

import org.sadbear.util.Vec2;

public class Button extends Drawable {
    int alignX = LEFT;
    int alignY = TOP;

    int textAlignX = LEFT;
    int textAlignY = CENTER;

    String label;

    public Button(String label, Vec2 position, Vec2 size) {
        super(position, size);
    }

    @Override
    protected void initLocal() {

    }

    @Override
    protected void updateLocal() {

    }

    @Override
    protected void drawLocal() {

    }
}

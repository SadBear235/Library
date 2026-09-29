package org.sadbear.ui;

import org.sadbear.util.Vec2;

public class Button extends Drawable {
    // Start/origin position
    Vec2 startPosition;
    Vec2 startSize;

    Vec2 textPosition;
    float textSize = 12;
    int textColour = 0;

    float padding = 12;

    int alignX = LEFT;
    int alignY = TOP;

    int textAlignX = LEFT;
    int textAlignY = CENTER;

    String label;

    public Button(String label, Vec2 position, Vec2 size, int textSize) {
        super(new Vec2(), new Vec2());
        textPosition = new Vec2();

        startPosition = position;
        startSize = size;

        this.textSize = textSize;

        this.label = label;
    }

    @Override
    protected void initLocal() {

    }

    @Override
    protected void updateLocal() {
        position.set(startPosition);
        switch (alignX) {
            case LEFT:
                break;
            case CENTER:
                position.addLocal(size.x/2, 0);
                break;
            case RIGHT:
                position.addLocal(size.x, 0);
                break;
        }
        switch (alignY) {
            case TOP:
                break;
            case CENTER:
                position.addLocal(0, size.y/2);
                break;
            case BOTTOM:
                position.addLocal(0, size.y);
        }
        size = startSize;

        textPosition.set(new Vec2());
        switch (textAlignX) {
            case LEFT:
                textPosition.add(padding, 0);
                break;
            case CENTER:
                textPosition.add(size.x/2, 0);
                break;
            case RIGHT:
                textPosition.add(size.x - padding, 0);
                break;
        }
        switch (textAlignY) {
            case TOP:
                textPosition.add(0, padding);
                break;
            case CENTER:
                textPosition.add(0, size.y/2);
                break;
            case BOTTOM:
                textPosition.add(0, size.y - padding);
                break;
        }


    }

    @Override
    protected void drawLocal() {
        fill(textColour);
        textAlign(textAlignX, textAlignY);
        text(label, new Vec2());
    }
}

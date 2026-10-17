package org.sadbear.ui.elements;

import org.sadbear.ui.Mouse;
import org.sadbear.util.Collision;
import org.sadbear.util.vector.Vec2f;

public class Button extends Drawable {
    // Start/origin position
    Vec2f startPosition;
    Vec2f startSize;

    Vec2f textPosition;
    float textSize = 12;
    int textColour = TEXT_COLOUR;

    public int hoverColour = HOVER_BACKGROUND_COLOUR;
    public int clickColour = CLICK_BACKGROUND_COLOUR;

    public float padding = 6;

    int alignX = LEFT;
    int alignY = TOP;

    int textAlignX = LEFT;
    int textAlignY = CENTER;

    boolean hover;
    boolean press;
    boolean click;

    String label;

    public Button(String label, Vec2f position, Vec2f size, int textSize) {
        super(position, size);
        textPosition = new Vec2f();

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
                break;
        }
        size = startSize;

        textPosition.set(new Vec2f());
        switch (textAlignX) {
            case LEFT:
                textPosition.addLocal(padding, 0);
                break;
            case CENTER:
                textPosition.addLocal(size.x/2, 0);
                break;
            case RIGHT:
                textPosition.addLocal(size.x - padding, 0);
                break;
        }
        switch (textAlignY) {
            case TOP:
                textPosition.addLocal(0, padding);
                break;
            case CENTER:
                textPosition.addLocal(0, size.y/2);
                break;
            case BOTTOM:
                textPosition.addLocal(0, size.y - padding);
                break;
        }

        hover = Collision.rectCollision(position, size, Mouse.mousePos) && isVisible();

        click = hover && Mouse.leftClick;

        if (hover && Mouse.leftPressed) {
            press = true;
        }
        if (!Mouse.leftPressed) {
            press = false;
        }
    }

    @Override
    protected void drawLocal() {
        if (press) background(clickColour);
        else if (hover) background(hoverColour);
        else background(fillColour);

        fill(textColour);
        textSize(textSize);
        textAlign(textAlignX, textAlignY);
        text(label, textPosition);
    }

    public boolean click() {
        return click;
    }
}

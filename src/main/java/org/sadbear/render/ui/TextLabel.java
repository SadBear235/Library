package org.sadbear.render.ui;

import org.sadbear.util.vector.Vec2f;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.LEFT;

public class TextLabel extends UIElement {
    Vec2f position;
    String label;

    int alignX = LEFT;
    int alignY = CENTER;

    int fillColour = 0;
    float textSize = 24;

    public TextLabel(String label, Vec2f position) {
        this.label = label;
        this.position = position;
    }

    @Override
    public void reset() {

    }
    @Override
    public void update() {

    }

    @Override
    public void draw() {
        render.fill(fillColour);
        render.textAlign(alignX, alignY);
        render.textSize(textSize);
        render.text(label, position);
    }

    @Override
    public void setOffset(Vec2f offset) {
        this.position.addLocal(offset);
    }

    public TextLabel setLabel(String label) {
        this.label = label;
        return this;
    }
    public TextLabel setPosition(Vec2f position) {
        this.position = position;
        return this;
    }
    public TextLabel setPosition(float x, float y) {
        this.position.set(x, y);
        return this;
    }
    public TextLabel changePosition(Vec2f change) {
        this.position.addLocal(change);
        return this;
    }
    public TextLabel changePosition(float changeX, float changeY) {
        this.position.addLocal(changeX, changeY);
        return this;
    }
    public TextLabel setSize(float size) {
        this.textSize = size;
        return this;
    }
    public TextLabel setAlign(int alignX) {
        this.alignX = alignX;
        return this;
    }
    public TextLabel setAlign(int alignX, int alignY) {
        this.alignX = alignX;
        this.alignY = alignY;
        return this;
    }
    public TextLabel setFillColour(int colour) {
        this.fillColour = colour;
        return this;
    }

    public String getLabel() {
        return label;
    }
    public Vec2f getPosition() {
        return position;
    }
    public float getSize() {
        return textSize;
    }
    public int getFillColour() {
        return fillColour;
    }
}

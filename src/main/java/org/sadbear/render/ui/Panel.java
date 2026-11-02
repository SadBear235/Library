package org.sadbear.render.ui;

import org.sadbear.util.Collision;
import org.sadbear.util.Colour;
import org.sadbear.util.vector.Vec2f;

public class Panel extends UIElement {
    Vec2f position;
    Vec2f size;

    int colour = Colour.colour(200);

    boolean hovered;

    public Panel(Vec2f position, Vec2f size) {
        this.position = position;
        this.size = size;
    }

    public Panel(Vec2f position, Vec2f size, int colour) {
        this.position = position;
        this.size = size;
        this.colour = colour;
    }

    public Panel setColour(int colour) {
        this.colour = colour;
        return this;
    }

    public boolean isHovered() {
        return hovered;
    }

    @Override
    public void reset() {

    }

    @Override
    public void update() {
        hovered = Collision.rectPoint(position, size, mouse.getMousePos());
    }

    @Override
    public void draw() {
        render.fill(colour);
        render.noStroke();
        render.rect(position, size);
    }

    @Override
    public void setOffset(Vec2f offset) {
        this.position.addLocal(offset);
    }
}

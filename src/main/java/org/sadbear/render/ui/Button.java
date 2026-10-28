package org.sadbear.render.ui;

import org.sadbear.util.Collision;
import org.sadbear.util.vector.Vec2f;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.LEFT;

public class Button extends UIElement {
    Vec2f position, size;
    String label;

    boolean resize = false;

    float padding = 3;
    float textSize = 24;

    public int pressColour = 210;
    public int hoverColour = 230;
    public int backgroundColour = 255;

    public float rounding = 0;

    public int strokeColour = 0;
    public float strokeWeight = 1.5f;

    boolean hovered, pressed, clicked;

    @Override
    public void reset() {
        hovered = false;
        pressed = false;
        clicked = false;
    }

    public Button(String label, Vec2f position, Vec2f size) {
        this.label = label;
        this.position = position;
        this.size = size;
    }

    @Override
    public void update() {
        if (isHidden()) {
            hovered = false;
            pressed = false;
            clicked = false;
            return;
        }

        hovered = Collision.rectPoint(position, size, mouse.getMousePos());
        clicked = hovered && pressed && mouse.clicked(LEFT);

        if (hovered && mouse.startClicked(LEFT)) {
            pressed = true;
        }

        if (!mouse.pressed(LEFT) || (!hovered && mouse.startClicked(LEFT))) {
            pressed = false;
        }
    }

    @Override
    public void draw() {
        if (isHidden()) return;

        // Calculate text size
        render.textSize(24);
        if (resize) {
            size.x = render.textWidth(label) + padding * 2;
        } else {
            float heightSize = size.y - padding * 2;
            float widthSize = (size.x - padding * 2) / render.textWidth(label) * 24;

            textSize = Math.min(heightSize, widthSize);
        }

        if (pressed) render.fill(pressColour);
        else if (hovered) render.fill(hoverColour);
        else render.fill(backgroundColour);

        if (strokeWeight > 0) {
            render.stroke(strokeColour);
            render.strokeWeight(strokeWeight);
        } else render.noStroke();

        render.rect(position, size, rounding);

        render.textAlign(CENTER, CENTER);
        render.textSize(textSize);
        render.fill(0);

        render.text(label, position.add(size.mult(0.5f)));
    }

    public boolean isClicked() {
        return clicked;
    }
    public Vec2f getSize() {
        return size;
    }

    public void setResize(boolean resize) {
        this.resize = resize;
    }
    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }
}

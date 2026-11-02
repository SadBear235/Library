package org.sadbear.render.ui;

import org.sadbear.util.Colour;
import org.sadbear.util.vector.Vec2f;
import processing.core.PApplet;
import processing.core.PConstants;

public class Slider extends UIElement {
    Vec2f position;
    Vec2f thumbPosition;

    NumberBox numberBox;

    float trackLength = 80;
    float trackSize = 8;
    float thumbSize = 15;

    float increment = 0;

    float minValue = 0;
    float maxValue = 1;

    float value = 1;

    boolean hovered;
    boolean pressed;

    int trackColour = Colour.colour(225);
    int thumbColour = Colour.colour(180);
    int hoverColour = Colour.colour(160);
    int moveColour = Colour.colour(140);

    public Slider(Vec2f position, float length, float minValue, float maxValue) {
        this.position = position;
        this.trackLength = length;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public Slider setValue(float value) {
        this.value = value;

        if (numberBox != null && numberBox.formatString(numberBox.getInput()) != value) numberBox.setInput(Float.toString(value));
        return this;
    }

    public float getValue() {
        return value;
    }

    @Override
    public void reset() {

    }

    @Override
    public void update() {
        thumbPosition = position.add(PApplet.map(PApplet.constrain(value, minValue, maxValue), minValue, maxValue, 0, trackLength), 0);

        hovered = mouse.getMousePos().sub(thumbPosition).mag() <= thumbSize/2;

        if (hovered && mouse.startClicked(PConstants.LEFT)) {
            pressed = true;
        }

        if (!mouse.pressed()) {
            pressed = false;
        }

        if (pressed) {
            value = PApplet.constrain(PApplet.map(mouse.getMousePos().x, position.x, position.x + trackLength, minValue, maxValue), minValue, maxValue);

            if (numberBox != null) {
                numberBox.setInput(Float.toString(value));
            }
        }
    }

    @Override
    public void draw() {
        render.strokeWeight(trackSize);
        render.stroke(trackColour);
        render.line(position, position.add(trackLength, 0));

        //System.out.println(hovered);
        render.strokeWeight(thumbSize);
        if (pressed) render.stroke(moveColour);
        else if (hovered) render.stroke(hoverColour);
        else render.stroke(thumbColour);
        render.point(thumbPosition);
    }

    @Override
    public void setOffset(Vec2f offset) {
        position.addLocal(offset);
    }

    public Slider connectNumberBox(NumberBox numberBox) {
        if (this.numberBox != null) return this;
        this.numberBox = numberBox;
        numberBox.connectSlider(this);
        return this;
    }
}

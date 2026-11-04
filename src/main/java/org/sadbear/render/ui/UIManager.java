package org.sadbear.render.ui;

import org.sadbear.input.Keyboard;
import org.sadbear.input.Mouse;
import org.sadbear.render.Render;
import org.sadbear.util.vector.Vec2f;

import java.util.ArrayList;

public class UIManager {
    Render render;
    Mouse mouse;
    Keyboard keyboard;

    Vec2f offset = new Vec2f(0, 0);

    ArrayList<UIElement> elements = new ArrayList<>();

    public UIManager(Render render, Mouse mouse, Keyboard keyboard) {
        this.render = render;
        this.mouse = mouse;
        this.keyboard = keyboard;
    }

    public UIManager setOffset(Vec2f offset) {
        this.offset.set(offset);
        return this;
    }

    public UIManager setOffset(float x, float y) {
        this.offset.set(x, y);
        return this;
    }

    public void addUI(UIElement element) {
        element.setStuff(render, mouse, keyboard);
        element.setOffset(offset);
        elements.add(element);
    }

    public void addUI(UIElement[] elements) {
        for (UIElement element : elements) {
            addUI(element);
        }
    }

    public ArrayList<UIElement> getElements() {
        return elements;
    }

    public void reset() {
        for (UIElement element : elements) {
            element.reset();
        }
    }

    public void update() {
        for (UIElement element : elements) {
            element.update();
        }
    }

    public void draw() {
        render.pushMatrix();
        //if (offset != null) render.translate(offset);

        for (UIElement element : elements) {
            element.draw();
        }

        render.popMatrix();
    }
}

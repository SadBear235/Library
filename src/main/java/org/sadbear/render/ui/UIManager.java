package org.sadbear.render.ui;

import org.sadbear.input.Keyboard;
import org.sadbear.input.Mouse;
import org.sadbear.render.Render;

import java.util.ArrayList;

public class UIManager {
    Render render;
    Mouse mouse;
    Keyboard keyboard;

    ArrayList<UIElement> elements = new ArrayList<>();

    public UIManager(Render render, Mouse mouse, Keyboard keyboard) {
        this.render = render;
        this.mouse = mouse;
        this.keyboard = keyboard;
    }

    public void addUI(UIElement element) {
        element.setStuff(render, mouse, keyboard);
        elements.add(element);
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
        for (UIElement element : elements) {
            element.draw();
        }
    }
}

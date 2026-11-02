package org.sadbear.render.ui;

import org.sadbear.input.Keyboard;
import org.sadbear.input.Mouse;
import org.sadbear.render.Render;
import org.sadbear.util.vector.Vec2f;

public abstract class UIElement {
    Render render;
    Mouse mouse;
    Keyboard keyboard;

    boolean visible = true;

    public void setStuff(Render render, Mouse mouse, Keyboard keyboard) {
        this.render = render;
        this.mouse = mouse;
        this.keyboard = keyboard;
    }

    public abstract void reset();
    public abstract void update();
    public abstract void draw();
    public abstract void setOffset(Vec2f offset);

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    protected boolean isHidden() {
        return !visible;
    }
}

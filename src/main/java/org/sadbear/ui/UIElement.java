package org.sadbear.ui;

import org.sadbear.util.Vec2;
import processing.core.PApplet;
import processing.core.PConstants;

public abstract class UIElement implements Constants {
    Vec2 position;
    Vec2 size;

    Panel parent;

    private boolean visible = true;
    private boolean parentVisible = true;

    public UIElement(Vec2 position, Vec2 size) {
        this.position = position;
        this.size = size;
    }

    public void init() {
        initLocal();
    }
    public void update() {
        updateLocal();
    }
    public void draw() {
        drawLocal();
    }

    protected abstract void initLocal();
    protected abstract void updateLocal();
    protected abstract void drawLocal();

    // Getters and Setters
    public void setPosition(Vec2 position) { this.position = position; }
    public Vec2 getPosition() { return position; }
    public void setSize(Vec2 size) { this.size = size; }
    public Vec2 getSize() { return size; }
    public void show() { this.visible = true; }
    public void hide() { this.visible = false; }
    public void setVisibility(boolean visible) { this.visible = visible; }
    public void setParentVisibility(boolean parentVisible) { this.parentVisible = parentVisible; }
    public boolean isVisible() { return visible && parentVisible; }

    protected PApplet getMain() { return parent.getInternalMain(); }

    public void setParent(Panel parent) { this.parent = parent; }

    protected void pushDraw() {
        UIManager.pushDraw(this);
    }
}

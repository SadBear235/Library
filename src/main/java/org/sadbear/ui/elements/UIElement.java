package org.sadbear.ui.elements;

import org.sadbear.ui.Constants;
import org.sadbear.ui.UIManager;
import org.sadbear.util.Vec2f;
import processing.core.PApplet;

public abstract class UIElement implements Constants {
    protected Vec2f position;
    protected Vec2f size;

    Panel parent;

    private boolean visible = true;
    private boolean parentVisible = true;

    public UIElement(Vec2f position, Vec2f size) {
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
    public void setPosition(Vec2f position) { this.position = position; }
    public Vec2f getPosition() { return position; }
    public void setSize(Vec2f size) { this.size = size; }
    public Vec2f getSize() { return size; }
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

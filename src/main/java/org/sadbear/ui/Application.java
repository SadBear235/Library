package org.sadbear.ui;

// Anything that needs updating by UIManager should extend this abstract class
public abstract class Application {
    protected UIManager parent;

    public void setParent(UIManager parent) {
        this.parent = parent;
    }
    public boolean checkRegistered() {
        return parent != null;
    }

    public abstract void init();
    public abstract void update();
    public abstract void draw();
}

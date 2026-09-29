package org.sadbear.ui;

import org.sadbear.util.Vec2;
import processing.core.PApplet;

import java.util.ArrayList;

// General purpose panel that other UIElement can go on.
// Can have a scrollbar, but isn't necessary
// All panels are clipping
public class Panel extends Drawable {
    ArrayList<UIElement> elements;

    private PApplet main;

    public Panel(Vec2 position, Vec2 size) {
        super(position, size);
    }

    @Override
    protected void initLocal() {
        elements = new ArrayList<>();
    }

    @Override
    protected void updateLocal() {
        for (UIElement element : elements) {
            element.setParentVisibility(isVisible());
            element.update();
        }
    }

    @Override
    protected void drawLocal() {
    }

    public void setInternalMain(PApplet main) { this.main = main; }
    public PApplet getInternalMain() { return main; }

    public void addUIElement(UIElement element) {
        // Return if element has already been added
        if (elements.contains(element)) return;

        elements.add(element);

        if (element instanceof Panel panel) {
            panel.setInternalMain(main);
        }

        element.setParent(this);
        element.init();
    }

    public void removeUIElement(UIElement element) {
        // Return if element hasn't been added
        if (!elements.contains(element)) return;

        elements.remove(element);
    }
}

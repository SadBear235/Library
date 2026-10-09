package org.sadbear.ui;

import org.sadbear.ui.elements.Panel;
import org.sadbear.ui.elements.UIElement;
import org.sadbear.util.Vec2f;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class UIManager extends PApplet {
    // The panel that all UIElements draw onto
    ParentPanel parentPanel;

    // Classes that will have their "update" and "draw" functions called
    ArrayList<Application> registeredClasses = new ArrayList<>(100);

    static Queue<UIElement> drawQueue = new LinkedList<>();

    UIManager selected;

    int initWidth;
    int initHeight;

    public UIManager(int initWidth, int initHeight) {
        super();

        this.initWidth = initWidth;
        this.initHeight = initHeight;

        // Start sketch
        PApplet.runSketch(new String[] {this.getClass().getName()}, this);
    }

    @Override
    public void settings() {
        size(initWidth, initHeight);

        Mouse.setParent(this);

        parentPanel = new ParentPanel(new Vec2f(), new Vec2f(initWidth, initHeight), this);

        parentPanel.init();
        parentPanel.setInternalMain(this);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        Mouse.update();

        // Update registered classes
        for (Application registered : registeredClasses) {
            registered.update();
        }

        parentPanel.update();

        // Draw registered classes
        for (Application registered : registeredClasses) {
            registered.draw();
        }

        // Draw UIElements
        /*for (UIElement element : elements) {
            element.draw();
        }*/

        for (UIElement element : drawQueue.toArray(new UIElement[0])) {
            element.draw();
        }
        drawQueue.clear();
    }

    public void register(Application application) {
        // Return if registered has already been registered
        if (registeredClasses.contains(application)) return;
        if (application.checkRegistered()) {
            System.err.println("Application is registered to another UIManager: " + application);
            return;
        }

        application.setParent(this);

        registeredClasses.add(application);

        application.init();
    }

    public void unregister(Application application) {
        // Return if application hasn't been registered
        if (!registeredClasses.contains(application)) return;

        application.setParent(null);

        registeredClasses.remove(application);
    }

    public void addUIElement(UIElement element) {
        parentPanel.addUIElement(element);
    }

    public void removeUIElement(UIElement element) {
        parentPanel.removeUIElement(element);
    }

    public static void pushDraw(UIElement element) {
        drawQueue.add(element);
    }

    private static class ParentPanel extends Panel {

        public ParentPanel(Vec2f position, Vec2f size, PApplet main) {
            super(position, size);

            setParent(this);
            setInternalMain(main);
            init();
        }

        @Override
        protected void endDraw() {
            graphics.endDraw();

            getMain().image(graphics, position.x, position.y);
        }
    }
}

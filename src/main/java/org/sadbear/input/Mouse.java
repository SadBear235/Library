package org.sadbear.input;

import org.sadbear.ui.UIManager;
import org.sadbear.util.vector.Vec2f;
import processing.core.PApplet;
import processing.event.MouseEvent;

import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.RIGHT;

import static processing.event.MouseEvent.*;

public class Mouse {
    PApplet parent;

    protected Vec2f mousePos = new Vec2f();
    protected Vec2f mouseDrag = new Vec2f();

    protected int mouseWheel = 0;

    protected boolean mousePressed, leftPressed, centrePressed, rightPressed,
                    mouseClicked, leftClicked, centreClicked, rightClicked;

    public Mouse(PApplet parent) {
        this.parent = parent;

        // Register methods
        parent.registerMethod("post", this);
        parent.registerMethod("mouseEvent", this);
    }

    public void post() {
        setMouseClicked(false, LEFT);
        setMouseClicked(false, CENTER);
        setMouseClicked(false, RIGHT);

        mouseWheel = 0;
    }

    public boolean pressed(int button) {
        return switch (button) {
            case LEFT -> leftPressed;
            case CENTER -> centrePressed;
            case RIGHT -> rightPressed;
            default -> mousePressed;
        };
    }

    public boolean pressed() {
        return mousePressed;
    }

    public boolean clicked(int button) {
        return switch (button) {
            case LEFT -> leftClicked;
            case CENTER -> centreClicked;
            case RIGHT -> rightClicked;
            default -> mousePressed;
        };
    }

    public boolean clicked() {
        return mouseClicked;
    }

    public int getMouseWheel() {
        return mouseWheel;
    }

    public Vec2f getMousePos() {
        return mousePos.copy();
    }

    public Vec2f getMouseDrag() {
        return mouseDrag.copy();
    }

    public void mouseEvent(MouseEvent e) {
        int button = e.getButton();
        switch (e.getAction()) {
            case PRESS:
                setMousePressed(true, button);
                break;
            case RELEASE:
                setMousePressed(false, button);
                mouseDrag.set(0, 0);
            case CLICK:
                setMouseClicked(true, button);
                break;
            case DRAG:
                mouseDrag.add(e.getX() - mousePos.x, e.getY() - mousePos.y);
            case MOVE:
                mousePos.set(e.getX(), e.getY());
                break;
            case WHEEL:
                mouseWheel += e.getCount();
                break;
        }
    }

    // Helper function for mouseEvent function
    private void setMousePressed(boolean value, int button) {
        mousePressed = value;
        switch (button) {
            case LEFT:
                leftPressed = value;
                break;
            case CENTER:
                centrePressed = value;
                break;
            case RIGHT:
                rightPressed = value;
                break;
        }
    }

    // Helper function for mouseEvent function
    private void setMouseClicked(boolean value, int button) {
        mouseClicked = value;
        switch (button) {
            case LEFT:
                leftClicked = value;
                break;
            case CENTER:
                centreClicked = value;
                break;
            case RIGHT:
                rightClicked = value;
                break;
        }
    }
}

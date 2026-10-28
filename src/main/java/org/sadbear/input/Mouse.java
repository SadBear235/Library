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

    protected int mouseWheel, mouseWheelPublic;

    protected boolean mousePressed, leftPressed, centrePressed, rightPressed,
                    mouseClicked, leftClicked, centreClicked, rightClicked, // Only true when the mouse stops being pressed
                    lastMouseClicked, lastLeftClicked, lastCentreClicked, lastRightClicked,
                    mouseStartClicked, leftStartClicked, centreStartClicked, rightStartClicked, // Only true when the mouse start to be pressed
                    lastMouseStartClicked, lastLeftStartClicked, lastCentreStartClicked, lastRightStartClicked;

    public Mouse(PApplet parent) {
        this.parent = parent;

        // Register methods
        parent.registerMethod("draw", this);
        parent.registerMethod("mouseEvent", this);
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
            case LEFT -> lastLeftClicked;
            case CENTER -> lastCentreClicked;
            case RIGHT -> lastRightClicked;
            default -> lastMouseClicked;
        };
    }

    public boolean clicked() {
        return mouseClicked;
    }

    public boolean startClicked(int button) {
        return switch (button) {
            case LEFT -> lastLeftStartClicked;
            case CENTER -> lastCentreStartClicked;
            case RIGHT -> lastRightStartClicked;
            default -> lastMouseStartClicked;
        };
    }

    public boolean startClicked() {
        return lastMouseStartClicked;
    }

    public int getMouseWheel() {
        return mouseWheelPublic;
    }

    public Vec2f getMousePos() {
        return mousePos.copy();
    }

    public Vec2f getMouseDrag() {
        return mouseDrag.copy();
    }

    public void draw() {
        lastLeftClicked = leftClicked;
        lastCentreClicked = centreClicked;
        lastRightClicked = rightClicked;
        lastMouseClicked = mouseClicked;
        setMouseClicked(false, LEFT);
        setMouseClicked(false, CENTER);
        setMouseClicked(false, RIGHT);

        lastLeftStartClicked = leftStartClicked;
        lastCentreStartClicked = centreStartClicked;
        lastRightStartClicked = rightStartClicked;
        lastMouseStartClicked = mouseStartClicked;
        setStartMouseClicked(false, LEFT);
        setStartMouseClicked(false, CENTER);
        setStartMouseClicked(false, RIGHT);

        mouseWheelPublic = mouseWheel;
        mouseWheel = 0;
    }

    public void mouseEvent(MouseEvent e) {
        int button = e.getButton();
        switch (e.getAction()) {
            case PRESS:
                setMousePressed(true, button);
                setStartMouseClicked(true, button);
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

    // Helper function for mouseEvent function
    private void setStartMouseClicked(boolean value, int button) {
        mouseStartClicked = value;
        switch (button) {
            case LEFT:
                leftStartClicked = value;
                break;
            case CENTER:
                centreStartClicked = value;
                break;
            case RIGHT:
                rightStartClicked = value;
                break;
        }
    }
}

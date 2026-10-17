package org.sadbear.ui;

import org.sadbear.util.vector.Vec2f;

import static processing.core.PConstants.*;

public class Mouse {
    public static Vec2f mousePos = new Vec2f();

    public static boolean mousePressed, leftPressed, rightPressed;
    public static boolean mouseDown, leftDown, rightDown;
    public static boolean mouseClick, leftClick, rightClick;

    private static UIManager parent;

    public static void setParent(UIManager parent) {
        Mouse.parent = parent;
    }

    public static void update() {
        mousePos.set(parent.mouseX, parent.mouseY);

        mouseDown = mousePressed;
        leftDown = leftPressed;
        rightDown = rightPressed;

        mousePressed = parent.mousePressed;
        leftPressed = parent.mousePressed && parent.mouseButton == LEFT;
        rightPressed = parent.mousePressed && parent.mouseButton == RIGHT;

        mouseClick = mousePressed && !mouseDown;
        leftClick = leftPressed && !leftDown;
        rightClick = rightPressed && !rightDown;
    }
}

package org.sadbear.input;

import processing.core.PApplet;
import processing.event.KeyEvent;

import static processing.event.KeyEvent.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Keyboard {
    PApplet parent;

    int lastKeyCode;
    char lastKey;

    //List of all typable characters
    private static final List<Character> allowedChars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', '{', ']', '}',
            '\\', '|', '`', '~', ';', ':', '\'', '"', ',', '<', '.', '>', '/', '?', ' ');

    // List of keys typed and in what order, good for input boxes
    StringBuilder keyList = new StringBuilder();
    String typedString;
    // How many times each key has been pressed since the start of the frame
    HashMap<Integer, Integer> keysTyped = new HashMap<>();
    HashMap<Integer, Integer> keysTypedPublic = new HashMap<>();
    // Maps keyCodes to whether a key is pressed or not
    HashMap<Integer, Boolean> keysPressed = new HashMap<>();

    public Keyboard(PApplet parent) {
        this.parent = parent;

        parent.registerMethod("draw", this);
        parent.registerMethod("keyEvent", this);
    }

    public boolean getKeyPressed(int keyCode) {
        return keysPressed.getOrDefault(keyCode, false);
    }

    public int getKeyTyped(int keyCode) {
        return keysTypedPublic.getOrDefault(keyCode, 0);
    }

    public String getKeyList() {
        return typedString;
    }

    public char getLastKey() {
        return lastKey;
    }

    public int getLastKeyCode() {
        return lastKeyCode;
    }

    public boolean allowedChar(char c) {
        return allowedChars.contains(c);
    }

    public static List<Character> getAllowedChars() {
        return allowedChars;
    }

    public void draw() {
        typedString = keyList.toString();
        keyList = new StringBuilder();

        keysTypedPublic = (HashMap<Integer, Integer>) keysTyped.clone();
        keysTyped.clear();
    }

    public void keyEvent(KeyEvent e) {
        switch (e.getAction()) {
            case PRESS:
                keysPressed.put(e.getKeyCode(), true);
                // Increment current value in keysTyped hashmap for the key that's been typed
                keysTyped.put(e.getKeyCode(), keysTyped.getOrDefault(e.getKeyCode(), 0) + 1);

                lastKeyCode = e.getKeyCode();
                break;
            case RELEASE:
                keysPressed.put(e.getKeyCode(), false);
                break;
            case TYPE:
                keyList.append(e.getKey());

                lastKey = e.getKey();
                break;
        }
    }
}

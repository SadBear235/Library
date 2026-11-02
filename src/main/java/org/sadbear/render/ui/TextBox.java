package org.sadbear.render.ui;

import org.sadbear.input.Keyboard;
import org.sadbear.util.Collision;
import org.sadbear.util.vector.Vec2f;

import java.util.List;

import static processing.core.PConstants.*;

public class TextBox extends UIElement {
    Vec2f position, size;
    String defaultText;
    StringBuilder input = new StringBuilder();

    private List<Character> allowedChars;

    boolean resizeText = false;

    float padding = 3;
    float textSize = 24;
    float defaultTextSize = 24;

    public int pressColour = 210;
    public int hoverColour = 230;
    public int backgroundColour = 255;

    public float rounding = 0;

    public int strokeColour = 0;
    public float strokeWeight = 1.5f;

    boolean hovered, selected;

    @Override
    public void reset() {
        hovered = false;
        selected = false;
    }

    public TextBox(String defaultText, Vec2f position, Vec2f size) {
        this.defaultText = defaultText;
        this.position = position;
        this.size = size;

        rounding = size.x > size.y ? size.y*0.2f : size.x*0.2f;

        setAllowedChars(Keyboard.getAllowedChars());
    }

    @Override
    public void update() {
        if (isHidden()) {
            hovered = false;
            selected = false;
            return;
        }



        hovered = Collision.rectPoint(position, size, mouse.getMousePos());

        if (hovered && mouse.startClicked(LEFT)) {
            selected = true;
        }

        if (selected && !hovered && mouse.startClicked(LEFT)) {
            selected = false;

            deselectedFunction();
        }

        if (selected) {
            String keyList = keyboard.getKeyList();
            for (int i = 0; i < keyList.length(); i++) {
                char ch = keyList.charAt(i);
                if (allowedChars.contains(ch)) {
                    input.append(ch);
                    continue;
                }
                if (ch == BACKSPACE && !input.isEmpty()) {
                    input.deleteCharAt(input.length()-1);
                    continue;
                }
            }
        }
    }

    @Override
    public void draw() {
        if (isHidden()) return;

        // Calculate text size
        render.textSize(24);
        if (resizeText) {
            float heightSize = size.y - padding * 2;
            float widthSize = (size.x - padding * 2) / render.textWidth(defaultText) * 24;

            textSize = Math.min(heightSize, widthSize);
            if (textSize > defaultTextSize) {
                textSize = defaultTextSize;
            }
        } else {
            textSize = defaultTextSize;
        }

        if (selected) render.fill(pressColour);
        else if (hovered) render.fill(hoverColour);
        else render.fill(backgroundColour);

        if (strokeWeight > 0) {
            render.stroke(strokeColour);
            render.strokeWeight(strokeWeight);
        } else render.noStroke();

        render.rect(position, size, rounding);

        render.textAlign(LEFT, CENTER);
        render.textSize(textSize);

        Vec2f renderPos = position.add(padding, size.y * 0.5f);
        if (input.isEmpty()) {
            render.fill(100);
            render.text(defaultText, renderPos);
        } else {
            render.fill(0);
            render.text(input.toString(), renderPos);
        }
    }

    @Override
    public void setOffset(Vec2f offset) {
        this.position.addLocal(offset);
    }

    public Vec2f getSize() {
        return size;
    }

    public TextBox setResizeText(boolean resizeText) {
        this.resizeText = resizeText;
        return this;
    }
    public TextBox setTextSize(float textSize) {
        this.defaultTextSize = textSize;
        return this;
    }

    void setAllowedChars(List<Character> allowedChars) {
        this.allowedChars = allowedChars;
    }

    boolean allowedChar(char c) {
        return allowedChars.contains(c);
    }

    public TextBox setInput(String input) {
        this.input = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (allowedChars.contains(input.charAt(i))) {
                this.input.append(input.charAt(i));
            }
        }
        return this;
    }

    public String getInput() {
        return input.toString();
    }

    // Call this function once when the textbox is deselected
    public void deselectedFunction() {
        // Default implementation does nothing
        return;
    }
}

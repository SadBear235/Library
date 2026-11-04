package org.sadbear.render.ui;

import org.sadbear.util.vector.Vec2f;

import java.util.ArrayList;

import static processing.core.PConstants.CENTER;

public class UIBuilder {
    ArrayList<UIElement> elements = new ArrayList<>();
    Panel background;

    float textSize = 24;
    float padding = 3;
    Vec2f pos = new Vec2f(padding, padding);

    float width = 100;


    public UIBuilder(float width) {
        background = new Panel(new Vec2f(0, 0), new Vec2f(width + padding*2, padding));
        elements.add(background);

        this.width = width;
    }

    public UIElement[] getUIElements() {
        UIElement[] out = elements.toArray(new UIElement[0]);
        elements = null;
        return out;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    void extend(float xExtend, float yExtend) {
        background.changeSize(xExtend, yExtend);

        pos.addLocal(xExtend, yExtend);
    }

    public void createTextLabel(String name) {
        elements.add(new TextLabel(name, pos.add(width/2, textSize/2)).setAlign(CENTER, CENTER));

        extend(0, textSize + padding*2);
    }

    public void createTextBox(String name) {
        elements.add(new TextBox(name, pos.copy(), new Vec2f(100, textSize + padding*2)));

        extend(0, textSize + padding*2);
    }

    // 1/3 is textbox, 2/3 is slider
    public void createNumberTextBoxSlider(float min, float max, float value) {
        Slider slider = new Slider(pos.add(padding*2, textSize/2), width - padding*4, min, max);
        elements.add(slider);
        elements.add(new NumberBox("textbox", pos.add(0, textSize + padding*1), new Vec2f(width, textSize)).connectSlider(slider).setInput(Float.toString(value)));

        extend(0, (textSize + padding*2) * 2);
    }
}

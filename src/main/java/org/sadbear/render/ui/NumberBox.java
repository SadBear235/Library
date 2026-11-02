package org.sadbear.render.ui;

import org.sadbear.util.vector.Vec2f;

import java.util.Arrays;

public class NumberBox extends TextBox {
    Slider slider;

    public NumberBox(String defaultText, Vec2f position, Vec2f size) {
        super(defaultText, position, size);

        setAllowedChars(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '-'));
    }

    @Override
    public void update() {
        super.update();

        if (selected && slider != null) {
            slider.setValue(formatString(getInput()));
        }
    }

    @Override
    public void deselectedFunction() {
        if (slider != null) {
            slider.setValue(formatString(getInput()));
        }
    }

    public NumberBox connectSlider(Slider slider) {
        if (this.slider != null) return this;
        this.slider = slider;
        slider.connectNumberBox(this);
        return this;
    }

    @Override
    public NumberBox setInput(String input) {
        super.setInput(input);

        if (slider != null) slider.setValue(Float.parseFloat(input));
        return this;
    }

    public float formatString(String string) {
        StringBuilder out = new StringBuilder();
        boolean hasNegative = false, hasDecimal = false, hasNumber = false;
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            if (ch == '-' && i != 0) continue;
            if (ch == '.' && (hasDecimal || (hasNegative && !hasNumber))) continue;
            if (allowedChar(ch)) {
                out.append(ch);
                if (ch == '.') hasDecimal = true;
                else if (ch == '-') hasNegative = true;
                else hasNumber = true;
            }
        }

        if (!hasNumber) out.append('0');

        return Float.parseFloat(out.toString());
    }
}

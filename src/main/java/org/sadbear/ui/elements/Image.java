package org.sadbear.ui.elements;

import org.sadbear.util.vector.Vec2f;
import processing.core.PImage;

public class Image extends Panel {
    PImage image;

    public Image(PImage image, Vec2f position, Vec2f size) {
        super(position, size);

        this.image = image;
    }

    public Image(PImage image, Vec2f position) {
        super(position, new Vec2f(image.width, image.height));

        this.image = image;
    }

    @Override
    public void draw() {
        image(image, new Vec2f(0, 0), size);
    }
}

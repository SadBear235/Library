package org.sadbear.ui.elements;

import org.sadbear.util.Vec2f;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;

public abstract class Drawable extends UIElement {
    // Graphics that this Panel draws onto
    protected PGraphics graphics;
    boolean updateGraphics = true;
    Vec2f lastSize;
    boolean drawing = false;

    public int fillColour = BASE_BACKGROUND_COLOUR;
    public int strokeColour = STROKE_COLOUR;
    public float strokeWeight = STROKE_WEIGHT;

    public Drawable(Vec2f position, Vec2f size) {
        super(position, size);
    }

    @Override
    public void update() {
        updateGraphics();

        pushDraw();

        super.update();

        pushDraw();
    }

    @Override
    public void draw() {
        if (drawing) endDraw();
        else beginDraw();

        drawing = !drawing;

        if (drawing) super.draw();
    }

    // Gets graphics ready for
    protected void beginDraw() {
        graphics.beginDraw();
        //graphics.clear();
        graphics.background(fillColour);
    }

    protected void endDraw() {
        graphics.endDraw();

        if (isVisible()) {
            parent.image(graphics, position);

            parent.noFill();
            parent.stroke(strokeColour);
            parent.strokeWeight(strokeWeight);
            parent.rect(position, size);
        }
    }

    private void updateGraphics() {
        if (!updateGraphics) return;

        updateGraphics = false;

        if (size.equals(lastSize)) return;

        // Check that the size is correct
        if (size.x <= 0 || size.y <= 0) {
            if (graphics == null) graphics = getMain().createGraphics(1, 1);
            System.err.println("Drawable size invalid: " + size.x + ", " + size.y);
            return;
        }

        if (graphics == null) graphics = getMain().createGraphics((int)size.x, (int)size.y);
        else graphics.setSize((int)size.x, (int)size.y);

        lastSize = size.copy();
    }

    @Override
    public void setSize(Vec2f size) {
        this.size = size;
        updateGraphics = true;
    }

    /// All rendering functions that panels have:

    // createGraphics();
    public void createGraphics(Vec2f size) { getMain().createGraphics((int)size.x, (int)size.y); }

    // Image rendering functions
    public void imageMode(int mode) { graphics.imageMode(mode); }
    public void image(PImage img, Vec2f pos) { graphics.image(img, pos.x, pos.y); }
    public void image(PImage img, Vec2f pos, Vec2f size) { graphics.image(img, pos.x, pos.y, size.x, size.y); }
    public void image(PImage img, Vec2f pos, Vec2f size, Vec2f UVpos, Vec2f UVsize) { graphics.image(img, pos.x, pos.y, size.x, size.y, (int)UVpos.x, (int)UVpos.y, (int)UVsize.x, (int)UVsize.y); }

    // Shape rendering functions
    public void point(Vec2f pos) { graphics.point(pos.x, pos.y); }
    public void line(Vec2f p1, Vec2f p2) { graphics.line(p1.x, p1.y, p2.x, p2.y); }
    public void triangle(Vec2f p1, Vec2f p2, Vec2f p3) { graphics.triangle(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y); }
    public void quad(Vec2f p1, Vec2f p2, Vec2f p3, Vec2f p4) { graphics.quad(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, p4.x, p4.y); }
    public void rectMode(int mode) { graphics.rectMode(mode); }
    public void rect(Vec2f pos, Vec2f size) { graphics.rect(pos.x, pos.y, size.x, size.y); }
    public void rect(Vec2f pos, Vec2f size, float r) { graphics.rect(pos.x, pos.y, size.x, size.y, r); }
    public void rect(Vec2f pos, Vec2f size, float tl, float tr, float br, float bl) { graphics.rect(pos.x, pos.y, size.x, size.y, tl, tr, br, bl); }
    public void square(Vec2f pos, float extent) { graphics.square(pos.x, pos.y, extent); }
    public void ellipseMode(int mode) { graphics.ellipseMode(mode); }
    public void ellipse(Vec2f pos, Vec2f size) { graphics.ellipse(pos.x, pos.y, size.x, size.y); }
    public void arc(Vec2f pos, Vec2f size, float start, float stop) { graphics.arc(pos.x, pos.y, size.x, size.y, start, stop); }
    public void arc(Vec2f pos, Vec2f size, float start, float stop, int mode) { graphics.arc(pos.x, pos.y, size.x, size.y, start, stop, mode); }
    public void circle(Vec2f pos, float extent) { graphics.circle(pos.x, pos.y, extent); }

    // Text rendering functions
    public void textAlign(int alignX) { graphics.textAlign(alignX); }
    public void textAlign(int alignX, int alignY) { graphics.textAlign(alignX, alignY); }
    public float textAscent() { return graphics.textAscent(); }
    public float textDescent() { return graphics.textDescent(); }
    public void textFont(PFont which) { graphics.textFont(which); }
    public void textFont(PFont which, float size) { graphics.textFont(which, size); }
    public void textLeading(float leading) { graphics.textLeading(leading); }
    public void textMode(int mode) { graphics.textMode(mode); }
    public void textSize(float size) { graphics.textSize(size); }
    public float textWidth(char c) { return graphics.textWidth(c); }
    public float textWidth(String str) { return graphics.textWidth(str); }
    public float textWidth(char[] chars, int start, int length) { return graphics.textWidth(chars, start, length); }
    public void text(String text, Vec2f pos) { graphics.text(text, pos.x, pos.y); }

    // Colour functions
    public void noFill() { graphics.noFill(); }
    public void fill(int rgb) { graphics.fill(rgb); }
    public void fill(int rgb, float alpha) { graphics.fill(rgb, alpha); }
    public void fill(float gray) { graphics.fill(gray); }
    public void fill(float gray, float alpha) { graphics.fill(gray, alpha); }
    public void fill(float v1, float v2, float v3) { graphics.fill(v1, v2, v3); }
    public void fill(float v1, float v2, float v3, float alpha) { graphics.fill(v1, v2, v3, alpha); }

    public void strokeWeight(float weight) { graphics.strokeWeight(weight); }
    public void strokeJoin(int join) { graphics.strokeJoin(join); }
    public void strokeCap(int cap) { graphics.strokeCap(cap); }
    public void noStroke() { graphics.noStroke(); }
    public void stroke(int rgb) { graphics.stroke(rgb); }
    public void stroke(int rgb, float alpha) { graphics.stroke(rgb, alpha); }
    public void stroke(float gray) { graphics.stroke(gray); }
    public void stroke(float gray, float alpha) { graphics.stroke(gray, alpha); }
    public void stroke(float v1, float v2, float v3) { graphics.stroke(v1, v2, v3); }
    public void stroke(float v1, float v2, float v3, float alpha) { graphics.stroke(v1, v2, v3, alpha); }

    public void background(int rgb) { graphics.background(rgb); }
    public void background(int rgb, float alpha) { graphics.background(rgb, alpha); }
    public void background(float gray) { graphics.background(gray); }
    public void background(float gray, float alpha) { graphics.background(gray, alpha); }
    public void background(float v1, float v2, float v3) { graphics.background(v1, v2, v3); }
    public void background(float v1, float v2, float v3, float alpha) { graphics.background(v1, v2, v3, alpha); }
}

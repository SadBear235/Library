package org.sadbear.render;

import org.sadbear.util.vector.Vec2f;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;

public class Render {
    PApplet parent;

    public Render(PApplet parent) {
        this.parent = parent;
    }

    public int width() {
        return parent.width;
    }
    public int height() {
        return parent.height;
    }

    // createGraphics();
    public PGraphics createGraphics(Vec2f size) {
        return parent.createGraphics((int)size.x, (int)size.y);
    }

    // Image rendering functions
    public void imageMode(int mode) { parent.imageMode(mode); }
    public void image(PImage img, Vec2f pos) {
        if (pos.x < parent.width && pos.y < parent.height && pos.x + img.width > 0 && pos.y + img.height > 0) parent.image(img, pos.x, pos.y);
    }
    public void image(PImage img, Vec2f pos, Vec2f size) {
        //System.out.println(pos + "   " + size);
        if (pos.x < parent.width && pos.y < parent.height && pos.x + img.width > 0 && pos.y + img.height > 0) parent.image(img, pos.x, pos.y, size.x, size.y);
    }
    public void image(PImage img, Vec2f pos, Vec2f size, Vec2f UVpos, Vec2f UVsize) {
        if (pos.x < parent.width && pos.y < parent.height && pos.x + img.width > 0 && pos.y + img.height > 0) parent.image(img, pos.x, pos.y, size.x, size.y, (int)UVpos.x, (int)UVpos.y, (int)UVsize.x, (int)UVsize.y);
    }

    // Shape rendering functions
    public void point(Vec2f pos) { parent.point(pos.x, pos.y); }
    public void line(Vec2f p1, Vec2f p2) { parent.line(p1.x, p1.y, p2.x, p2.y); }
    public void triangle(Vec2f p1, Vec2f p2, Vec2f p3) { parent.triangle(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y); }
    public void quad(Vec2f p1, Vec2f p2, Vec2f p3, Vec2f p4) { parent.quad(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, p4.x, p4.y); }
    public void rectMode(int mode) { parent.rectMode(mode); }
    public void rect(Vec2f pos, Vec2f size) { parent.rect(pos.x, pos.y, size.x, size.y); }
    public void rect(Vec2f pos, Vec2f size, float r) { parent.rect(pos.x, pos.y, size.x, size.y, r); }
    public void rect(Vec2f pos, Vec2f size, float tl, float tr, float br, float bl) { parent.rect(pos.x, pos.y, size.x, size.y, tl, tr, br, bl); }
    public void square(Vec2f pos, float extent) { parent.square(pos.x, pos.y, extent); }
    public void ellipseMode(int mode) { parent.ellipseMode(mode); }
    public void ellipse(Vec2f pos, Vec2f size) { parent.ellipse(pos.x, pos.y, size.x, size.y); }
    public void arc(Vec2f pos, Vec2f size, float start, float stop) { parent.arc(pos.x, pos.y, size.x, size.y, start, stop); }
    public void arc(Vec2f pos, Vec2f size, float start, float stop, int mode) { parent.arc(pos.x, pos.y, size.x, size.y, start, stop, mode); }
    public void circle(Vec2f pos, float extent) { parent.circle(pos.x, pos.y, extent); }

    // Text rendering functions
    public void textAlign(int alignX) { parent.textAlign(alignX); }
    public void textAlign(int alignX, int alignY) { parent.textAlign(alignX, alignY); }
    public float textAscent() { return parent.textAscent(); }
    public float textDescent() { return parent.textDescent(); }
    public void textFont(PFont which) { parent.textFont(which); }
    public void textFont(PFont which, float size) { parent.textFont(which, size); }
    public void textLeading(float leading) { parent.textLeading(leading); }
    public void textMode(int mode) { parent.textMode(mode); }
    public void textSize(float size) { parent.textSize(size); }
    public float textWidth(char c) { return parent.textWidth(c); }
    public float textWidth(String str) { return parent.textWidth(str); }
    public float textWidth(char[] chars, int start, int length) { return parent.textWidth(chars, start, length); }
    public void text(String text, Vec2f pos) { parent.text(text, pos.x, pos.y); }

    // Colour functions
    public void noFill() { parent.noFill(); }
    public void fill(int rgb) { parent.fill(rgb); }
    public void fill(int rgb, float alpha) { parent.fill(rgb, alpha); }
    public void fill(float gray) { parent.fill(gray); }
    public void fill(float gray, float alpha) { parent.fill(gray, alpha); }
    public void fill(float v1, float v2, float v3) { parent.fill(v1, v2, v3); }
    public void fill(float v1, float v2, float v3, float alpha) { parent.fill(v1, v2, v3, alpha); }

    public void strokeWeight(float weight) { parent.strokeWeight(weight); }
    public void strokeJoin(int join) { parent.strokeJoin(join); }
    public void strokeCap(int cap) { parent.strokeCap(cap); }
    public void noStroke() { parent.noStroke(); }
    public void stroke(int rgb) { parent.stroke(rgb); }
    public void stroke(int rgb, float alpha) { parent.stroke(rgb, alpha); }
    public void stroke(float gray) { parent.stroke(gray); }
    public void stroke(float gray, float alpha) { parent.stroke(gray, alpha); }
    public void stroke(float v1, float v2, float v3) { parent.stroke(v1, v2, v3); }
    public void stroke(float v1, float v2, float v3, float alpha) { parent.stroke(v1, v2, v3, alpha); }

    public void background(int rgb) { parent.background(rgb); }
    public void background(int rgb, float alpha) { parent.background(rgb, alpha); }
    public void background(float gray) { parent.background(gray); }
    public void background(float gray, float alpha) { parent.background(gray, alpha); }
    public void background(float v1, float v2, float v3) { parent.background(v1, v2, v3); }
    public void background(float v1, float v2, float v3, float alpha) { parent.background(v1, v2, v3, alpha); }
}

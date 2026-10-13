package org.sadbear.util;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Render {
    static PApplet main;

    public static void setPApplet(PApplet main) {
        Render.main = main;
    }

    // createGraphics();
    public static void createGraphics(Vec2f size) { main.createGraphics((int)size.x, (int)size.y); }

    // Image rendering functions
    public static void imageMode(int mode) { main.imageMode(mode); }
    public static void image(PImage img, Vec2f pos) { main.image(img, pos.x, pos.y); }
    public static void image(PImage img, Vec2f pos, Vec2f size) { main.image(img, pos.x, pos.y, size.x, size.y); }
    public static void image(PImage img, Vec2f pos, Vec2f size, Vec2f UVpos, Vec2f UVsize) { main.image(img, pos.x, pos.y, size.x, size.y, (int)UVpos.x, (int)UVpos.y, (int)UVsize.x, (int)UVsize.y); }

    // Shape rendering functions
    public static void point(Vec2f pos) { main.point(pos.x, pos.y); }
    public static void line(Vec2f p1, Vec2f p2) { main.line(p1.x, p1.y, p2.x, p2.y); }
    public static void triangle(Vec2f p1, Vec2f p2, Vec2f p3) { main.triangle(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y); }
    public static void quad(Vec2f p1, Vec2f p2, Vec2f p3, Vec2f p4) { main.quad(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, p4.x, p4.y); }
    public static void rectMode(int mode) { main.rectMode(mode); }
    public static void rect(Vec2f pos, Vec2f size) { main.rect(pos.x, pos.y, size.x, size.y); }
    public static void rect(Vec2f pos, Vec2f size, float r) { main.rect(pos.x, pos.y, size.x, size.y, r); }
    public static void rect(Vec2f pos, Vec2f size, float tl, float tr, float br, float bl) { main.rect(pos.x, pos.y, size.x, size.y, tl, tr, br, bl); }
    public static void square(Vec2f pos, float extent) { main.square(pos.x, pos.y, extent); }
    public static void ellipseMode(int mode) { main.ellipseMode(mode); }
    public static void ellipse(Vec2f pos, Vec2f size) { main.ellipse(pos.x, pos.y, size.x, size.y); }
    public static void arc(Vec2f pos, Vec2f size, float start, float stop) { main.arc(pos.x, pos.y, size.x, size.y, start, stop); }
    public static void arc(Vec2f pos, Vec2f size, float start, float stop, int mode) { main.arc(pos.x, pos.y, size.x, size.y, start, stop, mode); }
    public static void circle(Vec2f pos, float extent) { main.circle(pos.x, pos.y, extent); }

    // Text rendering functions
    public static void textAlign(int alignX) { main.textAlign(alignX); }
    public static void textAlign(int alignX, int alignY) { main.textAlign(alignX, alignY); }
    public float textAscent() { return main.textAscent(); }
    public float textDescent() { return main.textDescent(); }
    public static void textFont(PFont which) { main.textFont(which); }
    public static void textFont(PFont which, float size) { main.textFont(which, size); }
    public static void textLeading(float leading) { main.textLeading(leading); }
    public static void textMode(int mode) { main.textMode(mode); }
    public static void textSize(float size) { main.textSize(size); }
    public float textWidth(char c) { return main.textWidth(c); }
    public float textWidth(String str) { return main.textWidth(str); }
    public float textWidth(char[] chars, int start, int length) { return main.textWidth(chars, start, length); }
    public static void text(String text, Vec2f pos) { main.text(text, pos.x, pos.y); }

    // Colour functions
    public static void noFill() { main.noFill(); }
    public static void fill(int rgb) { main.fill(rgb); }
    public static void fill(int rgb, float alpha) { main.fill(rgb, alpha); }
    public static void fill(float gray) { main.fill(gray); }
    public static void fill(float gray, float alpha) { main.fill(gray, alpha); }
    public static void fill(float v1, float v2, float v3) { main.fill(v1, v2, v3); }
    public static void fill(float v1, float v2, float v3, float alpha) { main.fill(v1, v2, v3, alpha); }

    public static void strokeWeight(float weight) { main.strokeWeight(weight); }
    public static void strokeJoin(int join) { main.strokeJoin(join); }
    public static void strokeCap(int cap) { main.strokeCap(cap); }
    public static void noStroke() { main.noStroke(); }
    public static void stroke(int rgb) { main.stroke(rgb); }
    public static void stroke(int rgb, float alpha) { main.stroke(rgb, alpha); }
    public static void stroke(float gray) { main.stroke(gray); }
    public static void stroke(float gray, float alpha) { main.stroke(gray, alpha); }
    public static void stroke(float v1, float v2, float v3) { main.stroke(v1, v2, v3); }
    public static void stroke(float v1, float v2, float v3, float alpha) { main.stroke(v1, v2, v3, alpha); }

    public static void background(int rgb) { main.background(rgb); }
    public static void background(int rgb, float alpha) { main.background(rgb, alpha); }
    public static void background(float gray) { main.background(gray); }
    public static void background(float gray, float alpha) { main.background(gray, alpha); }
    public static void background(float v1, float v2, float v3) { main.background(v1, v2, v3); }
    public static void background(float v1, float v2, float v3, float alpha) { main.background(v1, v2, v3, alpha); }
}

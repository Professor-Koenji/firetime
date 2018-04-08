package com.koenji.ecs.wrappers;

import processing.core.PApplet;

public interface IGraphicsContext {

  int CLOSE = PApplet.CLOSE;
  float TWO_PI = PApplet.TWO_PI;
  int CHORD = PApplet.CHORD;
  int PIE = PApplet.PIE;
  int LEFT = PApplet.LEFT;
  int RIGHT = PApplet.RIGHT;
  int TOP = PApplet.TOP;
  int BOTTOM = PApplet.BOTTOM;
  int CENTER = PApplet.CENTER;
  int BASELINE = PApplet.BASELINE;

  void fill(int rgb);
  void fill(int rgb, float alpha);
  void noFill();

  void rotate(float angle);
  void translate(float x, float y);

  void stroke(int rgb);
  void strokeWeight(float weight);
  void noStroke();

  void vertex(float x, float y);

  void arc(float x, float y, float w, float h, float start, float stop);
  void arc(float x, float y, float w, float h, float start, float stop, int mode);

  void rect(float x, float y, float w, float h);

  void line(float x1, float y1, float x2, float y2);

  void textSize(float size);
  void textAlign(int x, int y);

  void text(String text, float x, float y);
  void text(String text, float x, float y, float bX, float bY);

  void beginShape();
  void endShape();
  void endShape(int mode);

  void pushMatrix();
  void popMatrix();

  int getWidth();
  int getHeight();

}

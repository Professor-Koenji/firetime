package com.koenji.firetime;

import processing.core.PApplet;

public class App extends PApplet {

  @Override
  public void settings() {
    super.settings();

    this.size(1024, 768);
  }

  @Override
  public void setup() {
    super.setup();

    this.surface.setTitle("Firetime");

    this.background(0);
  }

  @Override
  public void draw() {
    super.draw();
  }
}

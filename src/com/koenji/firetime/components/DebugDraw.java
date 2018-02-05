package com.koenji.firetime.components;

import com.koenji.ecs.component.IComponent;

public class DebugDraw implements IComponent {

  public int w;
  public int h;
  public int rgba;

  public DebugDraw(int w, int h, int rgba) {
    this.w = w;
    this.h = h;
    this.rgba = rgba;
  }

}

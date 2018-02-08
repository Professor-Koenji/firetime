package com.koenji.ecs.component.render;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

public class RenderLine implements IComponent {

  public PVector to;
  public int rgba;
  public float weight;

  public RenderLine(float x, float y, int rgba, float weight) {
    this.to = new PVector(x, y);
    this.rgba = rgba;
    this.weight = weight;
  }
}

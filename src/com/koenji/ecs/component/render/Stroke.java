package com.koenji.ecs.component.render;

import com.koenji.ecs.component.IComponent;

public class Stroke implements IComponent {

  public float weight;
  public int rgba;

  public Stroke(float weight, int rgba) {
    this.weight = weight;
    this.rgba = rgba;
  }

}

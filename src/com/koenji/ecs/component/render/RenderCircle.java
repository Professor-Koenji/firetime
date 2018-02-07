package com.koenji.ecs.component.render;

import com.koenji.ecs.component.IComponent;

public class RenderCircle implements IComponent {

  public float r;
  public int rgba;

  public RenderCircle(float r) {
    this(r, 0xFFFFFFFF);
  }

  public RenderCircle(float r, int rgba) {
    this.r = r;
    this.rgba = rgba;
  }

}

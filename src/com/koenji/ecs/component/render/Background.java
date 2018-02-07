package com.koenji.ecs.component.render;

import com.koenji.ecs.component.IComponent;

public class Background implements IComponent {

  public int rgba;

  public Background(int rgba) {
    this.rgba = rgba;
  }

}

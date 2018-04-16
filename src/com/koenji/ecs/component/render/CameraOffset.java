package com.koenji.ecs.component.render;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

public class CameraOffset implements IComponent {

  public PVector offset;

  public CameraOffset(PVector offset) {
    this.offset = offset;
  }

}

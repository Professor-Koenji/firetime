package com.koenji.ecs.component.render;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

public class Text implements IComponent {

  public String contents;
  public int size;
  public int rgba;
  public PVector bounds;

  public Text(String contents, int size, int rgba) {
    this(contents, size, rgba, null);
  }

  public Text(String contents, int size, int rgba, PVector bounds) {
    set(contents);
    this.size = size;
    this.rgba = rgba;
    this.bounds = bounds;
  }

  public void set(String contents) {
    this.contents = contents;
  }

}

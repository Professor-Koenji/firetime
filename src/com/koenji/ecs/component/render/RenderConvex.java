package com.koenji.ecs.component.render;

import com.koenji.ecs.component.IComponent;
import com.koenji.ecs.component.physics.ConvexBody;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RenderConvex implements IComponent {

  public List<PVector> vertices;
  public int rgba;

  public RenderConvex(int rgba, PVector ...vertices) {
    this.rgba = rgba;
    this.vertices = new ArrayList<>(Arrays.asList(vertices));
  }

  public RenderConvex(ConvexBody body, int rgba) {
    this.vertices = body.vertices;
    this.rgba = rgba;
  }

}

package com.koenji.ecs.component.render;

import com.koenji.ecs.component.IComponent;
import com.koenji.ecs.component.physics.ConvexBody;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RenderPolygon implements IComponent {

  public List<PVector> vertices; // Vertices of the polygon
  public int rgba; // Colour of the shape

  public RenderPolygon(int rgba, PVector ...vertices) {
    this.rgba = rgba;
    this.vertices = new ArrayList<>(Arrays.asList(vertices));
  }

  public RenderPolygon(ConvexBody body, int rgba) {
    this.vertices = body.vertices;
    this.rgba = rgba;
  }

}

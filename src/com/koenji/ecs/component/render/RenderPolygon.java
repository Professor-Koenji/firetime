package com.koenji.ecs.component.render;

import com.koenji.ecs.component.IComponent;
import com.koenji.ecs.component.physics.ConvexBody;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * RenderPolygon component representing a polygon upon a body,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davies & Chris Williams
 * @version 1.0
 */

public class RenderPolygon implements IComponent {

  // DECLARE a List of the polygons vertices
  public List<PVector> vertices;
  // DECLARE the rgba colour
  public int rgba;

  /**
   * Constructor: set the colour and vertices of the polygon
   * @param rgba      - int value of the colour
   * @param vertices  - PVector class(es) of the vertices
   */
  public RenderPolygon(int rgba, PVector ...vertices) {
    this.rgba = rgba;
    this.vertices = new ArrayList<>(Arrays.asList(vertices));
  }

  /**
   * Constructor: set a polygon given the ConvexBody and colour
   * @param body  - ConvexBody to build from
   * @param rgba  - int value of the colour
   */
  public RenderPolygon(ConvexBody body, int rgba) {
    this.vertices = body.vertices;
    this.rgba = rgba;
  }

}

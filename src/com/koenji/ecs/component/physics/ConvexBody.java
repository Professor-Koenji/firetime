package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvexBody implements IComponent {

  public List<PVector> vertices;

  public ConvexBody(PVector ...vertices) {
    this.vertices = new ArrayList<>(Arrays.asList(vertices));
  }

}

package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.entity.Entity;
import processing.core.PVector;

public class Goal extends Entity {

  public Goal(PVector position) {
    addComponents(
      new Position(position),
      new RenderPolygon(ConvexBody.polygon(6, 64), 0xFFFFFFFF)
    );
  }
}

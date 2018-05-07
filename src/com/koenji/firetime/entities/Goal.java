package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.service.Locator;
import processing.core.PVector;

public class Goal extends Entity {

  PVector nearPoint;

  boolean firedEvent;

  public Goal(PVector position, PVector nearPoint) {
    this.nearPoint = nearPoint;
    this.firedEvent = false;
    addComponents(
      new Position(position),
      new RenderPolygon(ConvexBody.polygon(6, 64), 0xFFFFFFFF)
    );
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    if (this.firedEvent) return;
    // Get distance to nearpoint
    float dist = PVector.dist(getComponent(Position.class), this.nearPoint);
    if (dist < 32) {
      Locator.get(IEventBus.class).fireEvent(new );
    }
  }
}

package com.koenji.firetime.entities;

import com.koenji.ecs.audio.IAudioManager;
import com.koenji.ecs.component.physics.AngularVelocity;
import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Rotation;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.service.Locator;
import com.koenji.firetime.events.GameEvent;
import processing.core.PVector;

public class Goal extends Entity {

  PVector nearPoint;

  boolean firedEvent;

  public Goal(PVector position, PVector nearPoint) {
    this.nearPoint = nearPoint;
    this.firedEvent = false;
    addComponents(
      new Position(position),
      new Rotation(),
      new AngularVelocity(0.05f),
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
    if (dist < 64) {
      // Fire audio
      Locator.get(IAudioManager.class).playSound("level-finished");
      Locator.get(IEventBus.class).fireEvent(new GameEvent(GameEvent.END_OF_LEVEL));
      firedEvent = true;
    }
  }
}

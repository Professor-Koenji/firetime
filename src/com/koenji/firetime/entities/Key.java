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

public class Key extends Entity {

  private int colour;
  private PVector playerPos;

  private boolean firedEvent;

  public Key(float x, float y, int colour, PVector playerPos) {
    this.colour = colour;
    this.playerPos = playerPos;
    this.firedEvent = false;

    addComponents(
      new Position(x, y),
      new Rotation(),
      new AngularVelocity(0.025f),
      new RenderPolygon(ConvexBody.polygon(3, 32), Door.COLOURS[colour])
    );
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    // Get distance to nearpoint
    if (firedEvent) return;
    float dist = PVector.dist(getComponent(Position.class), this.playerPos);
    if (dist < 32) {
      firedEvent = true;
      Locator.get(IAudioManager.class).playSound("key");
      Locator.get(IEventBus.class).fireEvent(new GameEvent(GameEvent.GOT_KEY, this.colour));
      scene.remove(this);
    }
  }
}

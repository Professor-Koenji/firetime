package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.events.KeyEvent;
import com.koenji.ecs.event.events.MouseEvent;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.firetime.events.EmitBulletEvent;
import processing.core.PVector;

import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {

  public Player(PVector pos) {

    addComponents(
      new Position(pos),
      new Velocity(),
      new Acceleration(),
      new Friction(.97f),
      new InverseMass(1 / 18),
      new CircleBody(18),
      ConvexBody.polygon(8, 18),
      new RenderCircle(18, 0xFFFF4444)
    );
  }

  @Override
  public void added(IScene scene) {
    super.added(scene);
    //
    IEventBus eb = Locator.get(IEventBus.class);
    eb.addEventHandler(InputEvents.MOUSE_PRESSED, this::mousePressed);
  }

  @Override
  public void update(int dt) {
    super.update(dt);
  }

  private void mousePressed(MouseEvent e) {
    //
    IGraphicsContext gc = Locator.get(IGraphicsContext.class);
    PVector centre = new PVector(gc.getWidth(), gc.getHeight());
    centre.mult(.5f);
    float angle = PVector.sub(e.position(), centre).heading();
    PVector angleVec = PVector.fromAngle(angle);
    PVector p = getComponent(Position.class);
    PVector pos = PVector.add(p, angleVec.setMag(32f));

    IEventBus eb = Locator.get(IEventBus.class);
    eb.fireEvent(new EmitBulletEvent(EmitBulletEvent.EMIT_BULLET, pos.x, pos.y, angle));
  }

}

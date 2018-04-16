package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.events.KeyEvent;
import com.koenji.ecs.event.events.MouseEvent;
import com.koenji.ecs.scene.IScene;
import com.koenji.firetime.events.EmitBulletEvent;
import processing.core.PVector;

import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {

  private Map<Integer, Boolean> keys;
  private float speed;

  public Player(PVector pos) {
    keys = new HashMap<>();

    speed = .2f;

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
  public void added(IScene scene, IEventController eventController) {
    super.added(scene, eventController);
    //
    addEventHandler(InputEvents.KEY_PRESSED, this::keyPressed);
    addEventHandler(InputEvents.KEY_RELEASED, this::keyReleased);
    addEventHandler(InputEvents.MOUSE_PRESSED, this::mousePressed);
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    // Up
    Acceleration acc = getComponent(Acceleration.class);
    if (keys.getOrDefault(87, false)) acc.sub(0, speed);
    if (keys.getOrDefault(83, false)) acc.add(0, speed);
    if (keys.getOrDefault(65, false)) acc.sub(speed, 0);
    if (keys.getOrDefault(68, false)) acc.add(speed, 0);
  }

  private void keyPressed(KeyEvent e) {
    keys.put(e.keyCode(), true);
  }

  private void keyReleased(KeyEvent e) {
    keys.put(e.keyCode(), false);
  }

  private void mousePressed(MouseEvent e) {
    //
    Position p = getComponent(Position.class);
    float angle = PVector.sub(e.position(), p).heading();
    PVector angleVec = PVector.fromAngle(angle);
    PVector pos = PVector.add(p, angleVec.setMag(32f));

    fireEvent(new EmitBulletEvent(EmitBulletEvent.EMIT_BULLET, pos.x, pos.y, angle));
  }

}

package com.koenji.firetime.entities;

import com.koenji.ecs.audio.IAudioManager;
import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.ISubscriber;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.PhysicsEvents;
import com.koenji.ecs.event.events.CollisionEvent;
import com.koenji.ecs.event.events.MouseEvent;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.firetime.components.CanKill;
import com.koenji.firetime.events.EmitBulletEvent;
import com.koenji.firetime.events.GameEvent;
import processing.core.PVector;

public class Player extends Entity {

  private static Player player;

  public static float SpeedRate() {
    Velocity playerVelocity = player.getComponent(Velocity.class);
    return 0.15f + (playerVelocity.mag() / 9.45f) * 1.2f;
  }

  private ISubscriber handler;
  private ISubscriber collisionHandler;
  private IEventBus eb;

  private boolean waitingToDie;

  public Player(PVector pos) {
    waitingToDie = false;
    Player.player = this;
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
    eb = Locator.get(IEventBus.class);
    handler = eb.addEventHandler(InputEvents.MOUSE_PRESSED, this::mousePressed);
    collisionHandler = eb.addEventHandler(PhysicsEvents.COLLISION, this::haveIBeenShot);
  }

  @Override
  public void removed() {
    super.removed();
    //
    System.out.println("Removed");
    handler.unsubscribe();
    collisionHandler.unsubscribe();
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

  private void haveIBeenShot(CollisionEvent e) {
    if (waitingToDie) return;
    if (this == e.a() || this == e.b()) {

      boolean aCanKill = e.a().getComponent(CanKill.class) != null;
      boolean bCanKill = e.b().getComponent(CanKill.class) != null;
      if (aCanKill || bCanKill) {
        // I've been shot darn it
        waitingToDie = true;
        // Fire splat
        Locator.get(IAudioManager.class).playSound("dead-guard");
        eb.fireEvent(new GameEvent(GameEvent.END_OF_LEVEL));
      }
    }
  }
}

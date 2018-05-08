package com.koenji.firetime.states.guard;

import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.firetime.components.GuardState;
import com.koenji.firetime.entities.Player;
import com.koenji.firetime.events.EmitBulletEvent;
import com.koenji.firetime.states.IState;
import com.koenji.firetime.states.IStateMachine;
import com.koenji.firetime.systems.GuardFSM;
import processing.core.PVector;

public class Combat extends BaseState {

  public static final float FIRE_RATE = 20f;

  private IState oldState;
  private float fireRate;

  public Combat(IState oldState) {
    this.oldState = oldState;
    fireRate = FIRE_RATE;
  }

  @Override
  public void update(IStateMachine fsm, int dt) {
    super.update(fsm, dt);
    //
    PVector pos = this.entity.getComponent(Position.class);
    PVector vel = this.entity.getComponent(Velocity.class);

    PVector target = ((GuardState) fsm).getTarget();

    PVector diff = PVector.sub(target, pos);
    vel.set(diff.setMag(5f));

    fireRate -= Player.SpeedRate();

    if (fireRate <= 0) {
      // Fire a bullet
      IGraphicsContext gc = Locator.get(IGraphicsContext.class);
      float angle = PVector.sub(target, pos).heading();
      PVector angleVec = PVector.fromAngle(angle);
      PVector bPos = PVector.add(pos, angleVec.setMag(64f));
      IEventBus eb = Locator.get(IEventBus.class);
      eb.fireEvent(new EmitBulletEvent(EmitBulletEvent.EMIT_BULLET, bPos.x, bPos.y, angle));
      //
      fireRate = Combat.FIRE_RATE;
    }

    // Is player out of range
    float dist = PVector.dist(pos, ((GuardState) fsm).getTarget());
    if (dist >= GuardFSM.COMBAT_DISTANCE) {
      // Too far, away, back to patrol!
      fsm.setState(oldState);
    }
  }
}

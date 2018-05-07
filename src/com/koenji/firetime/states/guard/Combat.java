package com.koenji.firetime.states.guard;

import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.firetime.components.GuardState;
import com.koenji.firetime.states.IStateMachine;
import processing.core.PVector;

public class Combat extends BaseState {

  @Override
  public void update(IStateMachine fsm, int dt) {
    super.update(fsm, dt);
    //
    PVector pos = this.entity.getComponent(Position.class);
    PVector vel = this.entity.getComponent(Velocity.class);

    PVector diff = PVector.sub(((GuardState) fsm).getTarget(), pos);
    vel.set(diff.setMag(4f));

    // Is player out of range
    float dist = PVector.dist(pos, ((GuardState) fsm).getTarget());
    if (dist >= 500) {
      // Too far, away, back to patrol!
      // fsm.setState(new Patrolling());
    }
  }
}

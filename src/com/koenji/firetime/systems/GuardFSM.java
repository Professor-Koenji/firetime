package com.koenji.firetime.systems;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.system.System;
import com.koenji.firetime.components.GuardState;
import com.koenji.firetime.entities.Guard;
import processing.core.PVector;

public class GuardFSM extends System {

  private PVector chasePosition;

  public GuardFSM(PVector chasePosition) {
    this.chasePosition = chasePosition;
  }

  @Override
  public void entityAdded(IEntity entity) {
    if (entity.hasComponents(GuardState.class)) {
      entity.getComponent(GuardState.class).setTarget(chasePosition);
      entities.add(entity);
    }
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    // Manage the states of le guards
    for (IEntity guard : entities) {
      GuardState state = guard.getComponent(GuardState.class);
      if (state != null) state.currentState().update(state, dt);
    }
  }

  @Override
  public void removed() {
    //java.lang.System.out.println("here in guardfsm");
    super.removed();
    for (IEntity guard : entities) {
      GuardState state = guard.getComponent(GuardState.class);
      if (state != null) state.currentState().exitState(state, guard);
    }
  }
}

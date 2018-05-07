package com.koenji.firetime.states.guard;

import com.koenji.ecs.component.physics.Friction;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.IEntity;
import com.koenji.firetime.components.GuardState;
import com.koenji.firetime.states.IState;
import com.koenji.firetime.states.IStateMachine;

public class Dead implements IState {
  @Override
  public void enterState(IStateMachine fsm, IEntity entity) {
    // Remove
    entity.removeComponent(GuardState.class);
    entity.getComponent(RenderCircle.class).rgba = 0xFF000000;
    entity.addComponent(new Friction(0.95f));
  }

  @Override
  public void update(IStateMachine fsm, int dt) {
    //
  }

  @Override
  public void exitState(IStateMachine fsm, IEntity entity) {
    //
  }
}
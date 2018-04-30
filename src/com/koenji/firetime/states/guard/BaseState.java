package com.koenji.firetime.states.guard;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.PhysicsEvents;
import com.koenji.ecs.service.Locator;
import com.koenji.firetime.components.CanKill;
import com.koenji.firetime.states.IState;
import com.koenji.firetime.states.IStateMachine;

public class BaseState implements IState {
  @Override
  public void enterState(IStateMachine fsm, IEntity entity) {
    //
    Locator.get(IEventBus.class).addEventHandler(PhysicsEvents.COLLISION, e -> {
      if (entity == e.a() || entity == e.b()) {
        // We were involved in a motor accident
        boolean aCanKill = e.a().getComponent(CanKill.class) != null;
        boolean bCanKill = e.b().getComponent(CanKill.class) != null;
        if (aCanKill || bCanKill) {
          fsm.setState(new Dead());
        }
      }
    }, null);
  }

  @Override
  public void update(IStateMachine fsm, int dt, IEntity entity) {

  }

  @Override
  public void exitState(IStateMachine fsm, IEntity entity) {

  }
}

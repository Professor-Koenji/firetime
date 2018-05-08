package com.koenji.firetime.states.guard;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.ISubscriber;
import com.koenji.ecs.event.PhysicsEvents;
import com.koenji.ecs.service.Locator;
import com.koenji.firetime.components.CanKill;
import com.koenji.firetime.states.IState;
import com.koenji.firetime.states.IStateMachine;

public abstract class BaseState implements IState {

  protected IEntity entity;

  private ISubscriber handler;

  @Override
  public void enterState(IStateMachine fsm, IEntity entity) {
    this.entity = entity;
    //
    handler = Locator.get(IEventBus.class).addEventHandler(PhysicsEvents.COLLISION, e -> {
      if (this.entity == e.a() || this.entity == e.b()) {
        // We were involved in a motor accident
        boolean aCanKill = e.a().getComponent(CanKill.class) != null;
        boolean bCanKill = e.b().getComponent(CanKill.class) != null;
        if (aCanKill || bCanKill) {
          fsm.setState(new Dead());
        }
      }
    });
  }

  @Override
  public void update(IStateMachine fsm, int dt) {

  }

  @Override
  public void exitState(IStateMachine fsm, IEntity entity) {
    handler.unsubscribe();
  }
}

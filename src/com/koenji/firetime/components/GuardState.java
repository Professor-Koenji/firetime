package com.koenji.firetime.components;

import com.koenji.ecs.component.IComponent;
import com.koenji.ecs.entity.IEntity;
import com.koenji.firetime.states.IState;
import com.koenji.firetime.states.IStateMachine;
import processing.core.PVector;

public class GuardState implements IComponent, IStateMachine {

  private IState currentState;
  private IState previousState;

  private PVector target;

  private IEntity entity;

  public GuardState(IState state, IEntity entity) {
    this.entity = entity;
    this.currentState = state;
    this.currentState.enterState(this, entity);
  }

  public void setTarget(PVector target) {
    this.target = target;
  }

  public PVector getTarget() {
    return target;
  }

  @Override
  public IState previousState() {
    return previousState;
  }

  @Override
  public IState currentState() {
    return currentState;
  }

  @Override
  public void setState(IState newState) {
    currentState.exitState(this, entity);
    previousState = currentState;
    currentState = newState;
    currentState.enterState(this, entity);
  }
}

package com.koenji.firetime.states;

import com.koenji.ecs.entity.IEntity;

public interface IState {
  void enterState(IStateMachine fsm, IEntity entity);
  void update(IStateMachine fsm, int dt, IEntity entity);
  void exitState(IStateMachine fsm, IEntity entity);
}

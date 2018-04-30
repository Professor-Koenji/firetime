package com.koenji.firetime.states;

public interface IStateMachine {
  IState previousState();
  IState currentState();
  void setState(IState state);
}

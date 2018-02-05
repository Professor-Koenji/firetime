package com.koenji.ecs.input;

public interface IKeyManager {
  void pressed(int keyCode);
  void released(int keyCode);
  boolean isPressed(int keyCode);
}

package com.koenji.ecs.event.events;

public interface IKeyEvent {
  int keyCode();
  boolean isAutoRepeat();
}

package com.koenji.ecs.event.events;

import processing.core.PVector;

public interface IMouseEvent {

  PVector position();
  int button();

}

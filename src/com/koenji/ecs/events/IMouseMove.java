package com.koenji.ecs.events;

import processing.event.MouseEvent;

public interface IMouseMove extends IObserver {
  void mouseMove(MouseEvent event);
}

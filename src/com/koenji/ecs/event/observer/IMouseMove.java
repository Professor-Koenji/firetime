package com.koenji.ecs.event.observer;

import processing.event.MouseEvent;

public interface IMouseMove extends IObserver {
  void mouseMove(MouseEvent event);
}

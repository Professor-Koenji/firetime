package com.koenji.ecs.event.observer;

import processing.event.MouseEvent;

public interface IMousePress extends IObserver {
  void mousePress(MouseEvent event);
}

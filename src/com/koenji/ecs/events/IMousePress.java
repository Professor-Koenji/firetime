package com.koenji.ecs.events;

import processing.event.MouseEvent;

public interface IMousePress extends IObserver {
  void mousePress(MouseEvent event);
}

package com.koenji.ecs.event.observer;

import processing.event.MouseEvent;

public interface IMouseRelease extends IObserver {
  void mouseRelease(MouseEvent event);
}

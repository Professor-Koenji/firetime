package com.koenji.ecs.events;

import processing.event.MouseEvent;

public interface IMouseRelease extends IObserver {
  void mouseRelease(MouseEvent event);
}

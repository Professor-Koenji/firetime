package com.koenji.ecs.events;

import processing.event.MouseEvent;

public class MouseMoveObservable extends Observable<IMouseMove, MouseEvent> {

  @Override
  public void notify(MouseEvent event) {
    for (IMouseMove o : observers) {
      o.mouseMove(event);
    }
  }
}

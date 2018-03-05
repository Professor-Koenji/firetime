package com.koenji.ecs.event.observer;

import processing.event.MouseEvent;

public class MouseReleaseObservable extends Observable<IMouseRelease, MouseEvent> {
  @Override
  public void notify(MouseEvent event) {
    for (IMouseRelease o : observers) {
      o.mouseRelease(event);
    }
  }
}

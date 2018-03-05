package com.koenji.ecs.event.observer;

import processing.event.MouseEvent;

public class MousePressObservable extends Observable<IMousePress, MouseEvent> {

  public void notify(MouseEvent event) {
    for (IMousePress observer : observers) {
      observer.mousePress(event);
    }
  }

}

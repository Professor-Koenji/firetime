package com.koenji.ecs.events;

import processing.event.KeyEvent;

public class KeyPressObservable extends Observable<IKeyPress, KeyEvent> {

  public void notify(KeyEvent event) {
    for (IKeyPress observer : observers) {
      observer.keyPress(event);
    }
  }
}

package com.koenji.ecs.event.observer;

import processing.event.KeyEvent;

public class KeyPressObservable extends Observable<IKeyPress, KeyEvent> {

  public void notify(KeyEvent event) {
    for (IKeyPress observer : observers) {
      observer.keyPress(event);
    }
  }
}

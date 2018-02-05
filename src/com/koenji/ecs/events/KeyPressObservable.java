package com.koenji.ecs.events;

import processing.event.KeyEvent;

public class KeyPressObservable extends Observable<IKeyPressEvent, KeyEvent> {

  public void notify(KeyEvent event) {
    for (IKeyPressEvent observer : observers) {
      observer.keyPress(event);
    }
  }
}

package com.koenji.ecs.events;

import processing.event.KeyEvent;

public class KeyReleaseObservable extends Observable<IKeyRelease, KeyEvent> {
  @Override
  public void notify(KeyEvent event) {
    for (IKeyRelease o : observers) {
      o.keyRelease(event);
    }
  }
}

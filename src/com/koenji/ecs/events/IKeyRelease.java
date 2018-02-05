package com.koenji.ecs.events;

import processing.event.KeyEvent;

public interface IKeyRelease extends IObserver {
  void keyRelease(KeyEvent event);
}

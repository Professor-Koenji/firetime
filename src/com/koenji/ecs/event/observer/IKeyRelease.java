package com.koenji.ecs.event.observer;

import processing.event.KeyEvent;

public interface IKeyRelease extends IObserver {
  void keyRelease(KeyEvent event);
}

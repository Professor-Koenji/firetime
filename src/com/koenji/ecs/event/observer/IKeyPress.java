package com.koenji.ecs.event.observer;

import processing.event.KeyEvent;

public interface IKeyPress extends IObserver {
  void keyPress(KeyEvent event);
}

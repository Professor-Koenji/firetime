package com.koenji.ecs.events;

import processing.event.KeyEvent;

public interface IKeyPress extends IObserver {
  void keyPress(KeyEvent event);
}

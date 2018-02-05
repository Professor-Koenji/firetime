package com.koenji.ecs.input;

import com.koenji.ecs.events.IObserver;
import processing.event.Event;

public interface IInputManager {
  <T extends IObserver> void notify(Class<T> type, Event event);
  <T extends IObserver> void subscribe(Class<T> type, T instance);
}

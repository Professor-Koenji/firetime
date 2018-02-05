package com.koenji.ecs.events;

import processing.event.Event;

public interface IObservable<T, T2> {
  void notify(T2 event);
  void add(T observer);
  void remove(T observer);
}

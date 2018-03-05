package com.koenji.ecs.event.observer;

public interface IObservable<T, T2> {
  void notify(T2 event);
  void add(T observer);
  void remove(T observer);
}

package com.koenji.ecs.events;

public interface IObservable<T, T2> {
  void notify(T2 event);
  void add(T observer);
  void remove(T observer);
}

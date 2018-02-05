package com.koenji.ecs.events;

import processing.event.Event;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable<T, T2 extends Event> implements IObservable<T, T2> {

  protected List<T> observers;

  public Observable() {
    observers = new ArrayList<>();
  }

  public void add(T observer) {
    observers.add(observer);
  }

  public void remove(T observer) {
    observers.remove(observer);
  }
}

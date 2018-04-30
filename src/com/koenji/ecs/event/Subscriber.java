package com.koenji.ecs.event;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public class Subscriber implements ISubscriber {

  private IEventBus bus;

  private EventType<? extends Event> eventType;
  private EventHandler<? super Event> eventHandler;

  public Subscriber(IEventBus bus, EventType<? extends Event> type, EventHandler<? super Event> handler) {
    this.bus = bus;
    this.eventType = type;
    this.eventHandler = handler;
  }

  @Override
  public void unsubscribe() {
    this.bus.removeEventHandler(this.eventType, this.eventHandler);
  }
}

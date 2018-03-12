package com.koenji.ecs.system;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityManager;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.scene.IScene;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import java.util.ArrayList;
import java.util.List;

public abstract class System implements ISystem, IEventController {

  protected IScene scene;
  protected IEventController eventController;
  protected List<IEntity> entities;

  public System() {
    entities = new ArrayList<>();
  }

  public void added(IScene scene, IEventController eventController) {
    this.scene = scene;
    this.eventController = eventController;
  }

  public void added(IScene scene, IEventController eventController, IEntityManager entityManager) {
    added(scene, eventController);
    //
    for (IEntity e : entityManager.iterable()) {
      entityAdded(e);
    }
  }

  public void removed() {}

  public void update(int dt) {}

  public void entityAdded(IEntity entity) {
    entities.add(entity);
  }

  final public void entityRemoved(IEntity entity) {
    entities.remove(entity);
  }

  @Override
  public void fireEvent(Event type) {
    eventController.fireEvent(type);
  }

  @Override
  public void fireEvent(Event type, boolean propagate) {
    eventController.fireEvent(type, propagate);
  }

  @Override
  public <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler) {
    eventController.addEventHandler(type, handler);
  }

  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type) {
    eventController.removeEventHandler(type);
  }

  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type, boolean global) {
    eventController.removeEventHandler(type, global);
  }

  @Override
  public void removeAllEventHandlers() {
    eventController.removeAllEventHandlers();
  }

  @Override
  public void removeAllEventHandlers(boolean global) {
    eventController.removeAllEventHandlers(global);
  }
}

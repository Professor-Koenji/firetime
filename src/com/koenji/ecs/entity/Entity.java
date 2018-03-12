package com.koenji.ecs.entity;

import com.koenji.ecs.component.IComponent;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.scene.IScene;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import java.util.HashMap;
import java.util.Map;

public abstract class Entity implements IEntity, IEventController {

  protected IScene scene;
  protected IEventController eventController;

  private Map<Class<? extends IComponent>, IComponent> components;

  public Entity() {
    components = new HashMap<>();
  }

  public void added(IScene scene, IEventController eventController) {
    this.scene = scene;
    this.eventController = eventController;
  }

  public void removed() {}

  public void update(int dt) { }

  public IEntity addComponent(IComponent c) {
    components.put(c.getClass(), c);
    if (scene != null) scene.modifiedEntity(this);
    return this;
  }

  public IEntity addComponents(IComponent... cs) {
    for (IComponent c : cs) addComponent(c);
    return this;
  }

  public IEntity removeComponent(Class<? extends IComponent> c) {
    components.remove(c);
    if (scene != null) scene.modifiedEntity(this);
    return this;
  }

  @SuppressWarnings("unchecked")
  public <T extends IComponent> T getComponent(Class<T> c) {
    return (T) components.get(c);
  }

  @SafeVarargs
  public final boolean hasComponents(Class<? extends IComponent> ...cs) {
    for (Class<? extends IComponent> c : cs) {
      if (!components.containsKey(c)) {
        return false;
      }
    }
    return true;
  }

  public IScene getScene() {
    return scene;
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

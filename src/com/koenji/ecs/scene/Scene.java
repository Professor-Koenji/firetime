package com.koenji.ecs.scene;

import com.koenji.ecs.entity.EntityManager;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityGroup;
import com.koenji.ecs.entity.IEntityManager;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.ISystem;
import com.koenji.ecs.system.ISystemManager;
import com.koenji.ecs.system.SystemManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

/**
 * A Scene is a collection of entities and systems that interact
 * and form complex behaviour with each other. A scene encapsulates
 * a 'state' within a game, such as a level, scene or section.
 *
 * @author Brad Davies & Chris Williams
 * @version 1.4
 */
public abstract class Scene implements IScene, IEventController {

  private IEventBus eventBus;
  private IEntityManager entityManager;
  private ISystemManager systemManager;

  /**
   * Creates a new Scene and sets up the Entity & System managers.
   */
  public Scene() {
    this.entityManager = new EntityManager(this);
    this.systemManager = new SystemManager(this, entityManager);
  }

  /**
   * Stores a local reference to the event bus via Service Locator.
   */
  public void added() {
    eventBus = Locator.get(IEventBus.class);
  }

  /**
   * If clearEvents is `true`, then all event handlers are removed.
   * @param clearEvents Whether the Scene should clear it's events
   */
  public void removed(boolean clearEvents) {
    if (clearEvents) removeAllEventHandlers();
  }

  /**
   * Updates the entity manager and system manager.
   * @param dt The passed time since the last frame (in ms).
   */
  @Override
  public void update(int dt) {
    entityManager.update(dt);
    systemManager.update(dt);
  }

  /**
   * Adds the entity to the scene.
   * @param entity The entity to add.
   */
  @Override
  public void add(IEntity entity) {
    entityManager.add(entity);
    systemManager.entityAdded(entity);
  }

  /**
   * Adds the entity group to the scene.
   * @param entityGroup The entity group to add.
   */
  public void add(IEntityGroup entityGroup) {
    for (IEntity e : entityGroup.iterable()) {
      add(e);
    }
  }

  /**
   * Adds the system to the scene.
   * @param system The System to add.
   */
  @Override
  public void add(ISystem system) {
    systemManager.add(system);
  }

  /**
   * Removes the entity from the scene.
   * @param entity The entity to remove.
   */
  @Override
  public void remove(IEntity entity) {
    entityManager.remove(entity);
    systemManager.entityRemoved(entity);
  }

  /**
   * Removes the entity group from the scene.
   * @param entityGroup The entity group to remove.
   */
  public void remove(IEntityGroup entityGroup) {
    for (IEntity e : entityGroup.iterable()) {
      remove(e);
    }
  }

  /**
   * Removes the system from the scene.
   * @param system The system to remove.
   */
  @Override
  public void remove(ISystem system) {
    systemManager.remove(system);
  }

  /**
   * Removes all entities from the scene.
   */
  @Override
  public void removeAllEntities() {
    entityManager.clear();
  }

  /**
   * Removes all systems from the scene.
   */
  @Override
  public void removeAllSystems() {
    systemManager.clear();
  }

  /**
   * Removes everything (Systems & Entities) from the scene.
   */
  @Override
  public void removeAll() {
    removeAllEntities();
    removeAllSystems();
  }

  /**
   * Triggers an entity update within each system.
   * @param entity The modified entity
   */
  @Override
  public void modifiedEntity(IEntity entity) {
    systemManager.entityRemoved(entity);
    systemManager.entityAdded(entity);
  }

  /**
   * @return The number of entities within the scene.
   */
  @Override
  public int entityCount() {
    return entityManager.count();
  }

  /**
   * @return The number of systems within the scene.
   */
  @Override
  public int systemCount() {
    return systemManager.count();
  }

  /**
   * Fires an event from the Scene scope.
   * @param type - Event type
   */
  @Override
  public void fireEvent(Event type) {
    fireEvent(type, false);
  }

  /**
   * Fires an event from the Scene scope, with propagation defined.
   * @param type      - Event type
   * @param propagate - boolean flag
   */
  @Override
  public void fireEvent(Event type, boolean propagate) {
    eventBus.fireEvent(type, this, propagate);
  }

  /**
   * Adds an event listener from this Scene scope for a given EventType.
   * @param type    - EventType class
   * @param handler - EventHandler that 'handles' the event
   * @param <T> Any Event type.
   */
  @Override
  public <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler) {
    eventBus.addEventHandler(type, handler, this);
  }

  /**
   * Removes an event handler for a given EventType.
   * @param type - EventType class to remove
   * @param <T> Any Event type.
   */
  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type) {
    eventBus.removeEventHandler(type, this, false);
  }

  /**
   * Removes an event handler for a given EventType, with a global option.
   * @param type   - EventType to remove
   * @param global - boolean flag
   * @param <T> Any Event type.
   */
  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type, boolean global) {
    eventBus.removeEventHandler(type, this, global);
  }

  /**
   * Removes all event handlers from the Scene's scope (EventGroup).
   */
  @Override
  public void removeAllEventHandlers() {
    removeAllEventHandlers(false);
  }

  /**
   * Removes all event handlers from this Scene, and optionally all global scope.
   * @param global - boolean flag
   */
  @Override
  public void removeAllEventHandlers(boolean global) {
    eventBus.removeAllEventHandlers(this, global);
  }
}

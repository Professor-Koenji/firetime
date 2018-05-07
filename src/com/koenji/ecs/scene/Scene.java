package com.koenji.ecs.scene;

import com.koenji.ecs.entity.EntityManager;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityGroup;
import com.koenji.ecs.entity.IEntityManager;
import com.koenji.ecs.event.IEventBus;
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
 * @author Brad Davies &amp; Chris Williams
 * @version 1.4
 */
public abstract class Scene implements IScene {

  private IEntityManager entityManager;
  private ISystemManager systemManager;

  /**
   * Creates a new Scene and sets up the Entity&amp;System managers.
   */
  public Scene() {
    this.entityManager = new EntityManager(this);
    this.systemManager = new SystemManager(this, entityManager);
  }

  /**
   * Stores a local reference to the event bus via Service Locator.
   */
  public void added() {}
  public void removed() {}

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
   * Removes everything (Systems&amp;Entities) from the scene.
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
}

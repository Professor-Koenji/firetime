package com.koenji.ecs.scene;

import com.koenji.ecs.Core;
import com.koenji.ecs.ICore;
import com.koenji.ecs.entity.EntityManager;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityGroup;
import com.koenji.ecs.entity.IEntityManager;
import com.koenji.ecs.event.bus.IEventBus;
import com.koenji.ecs.system.ISystem;
import com.koenji.ecs.system.ISystemManager;
import com.koenji.ecs.system.SystemManager;

public abstract class Scene implements IScene {

  protected ICore core;

  private IEventBus eventBus;
  private IEntityManager entityManager;
  private ISystemManager systemManager;

  public Scene() {
    this.entityManager = new EntityManager(this);
    this.systemManager = new SystemManager(this, entityManager);
  }

  public void added(ICore core, IEventBus eventBus) {
    this.core = core;
    this.eventBus = eventBus;
  }

  public void removed() {}

  @Override
  public void update(int dt) {
    entityManager.update(dt);
    systemManager.update(dt);
  }

  @Override
  public void add(IEntity entity) {
    entityManager.add(entity);
    systemManager.entityAdded(entity);
  }

  public void add(IEntityGroup entityGroup) {
    for (IEntity e : entityGroup.iterable()) {
      add(e);
    }
  }

  @Override
  public void add(ISystem system) {
    systemManager.add(system);
  }

  @Override
  public void remove(IEntity entity) {
    entityManager.remove(entity);
    systemManager.entityRemoved(entity);
  }

  public void remove(IEntityGroup entityGroup) {
    for (IEntity e : entityGroup.iterable()) {
      remove(e);
    }
  }

  @Override
  public void remove(ISystem system) {
    systemManager.remove(system);
  }

  @Override
  public void removeAllEntities() {
    entityManager.clear();
  }

  @Override
  public void removeAllSystems() {
    systemManager.clear();
  }

  @Override
  public void removeAll() {
    removeAllEntities();
    removeAllSystems();
  }

  @Override
  public void modifiedEntity(IEntity entity) {
    systemManager.entityRemoved(entity);
    systemManager.entityAdded(entity);
  }

  public Core gc() {
    return core.gc();
  }

  @Override
  public int entityCount() {
    return entityManager.count();
  }

  @Override
  public int systemCount() {
    return systemManager.count();
  }

  @Override
  public IEventBus getEventBus() {
    return eventBus;
  }
}

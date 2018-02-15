package com.koenji.ecs.scene;

import com.koenji.ecs.Core;
import com.koenji.ecs.ICore;
import com.koenji.ecs.entity.EntityManager;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityGroup;
import com.koenji.ecs.entity.IEntityManager;
import com.koenji.ecs.events.*;
import com.koenji.ecs.system.ISystem;
import com.koenji.ecs.system.ISystemManager;
import com.koenji.ecs.system.SystemManager;
import com.koenji.firetime.InputTest.InputHandler;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public abstract class Scene implements IScene, IKeyPress, IKeyRelease, IMouseMove, IMousePress, IMouseRelease {

  protected ICore core;
  protected InputHandler inputHandler;

  private IEntityManager entityManager;
  private ISystemManager systemManager;

  public Scene() {
    this.entityManager = new EntityManager(this);
    this.systemManager = new SystemManager(this, entityManager);
    this.inputHandler = new InputHandler();
  }

  public void added(ICore core) {
    this.core = core;
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
  public void keyPress(KeyEvent event) {

  }

  public void keyRelease(KeyEvent event) {

  }

  public void mouseMove(MouseEvent event) {

  }

  public void mousePress(MouseEvent event) {

  }

  public void mouseRelease(MouseEvent event) {

  }
}

package com.koenji.ecs.entity;

import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class EntityManager implements IEntityManager {

  private IScene scene;
  private IEventController eventController;

  private List<IEntity> toAdd;
  private List<IEntity> toRemove;
  private List<IEntity> entities;

  public EntityManager(Scene scene) {
    this.scene = scene;
    this.eventController = scene;

    toAdd = new ArrayList<>();
    toRemove = new ArrayList<>();
    entities = new ArrayList<>();
  }

  public void add(IEntity entity) {
    toAdd.add(entity);
  }

  public void remove(IEntity entity) {
    toRemove.add(entity);
  }

  public void clear() {
    toAdd.clear();
    toRemove.clear();
    entities.clear();
  }

  public void update(int dt) {
    // Remove any entities first
    entities.removeAll(toRemove);
    for (IEntity entity : toRemove) {
      entity.removed();
    }
    // Add new scenes
    entities.addAll(toAdd);
    for (IEntity entity : toAdd) {
      entity.added(scene, eventController);
    }
    // Empty modifier lists
    toAdd.clear();
    toRemove.clear();
    // Update all current scenes
    for (IEntity entity : entities) {
      entity.update(dt);
    }
  }

  public Iterable<IEntity> iterable() {
    return entities;
  }

  public int count() {
    return entities.size();
  }
}

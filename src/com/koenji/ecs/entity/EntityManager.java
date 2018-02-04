package com.koenji.ecs.entity;

import com.koenji.ecs.scene.IScene;

import java.util.ArrayList;
import java.util.List;

public class EntityManager implements IEntityManager {

  private IScene scene;

  private List<IEntity> toAdd;
  private List<IEntity> toRemove;
  private List<IEntity> entities;

  public EntityManager(IScene scene) {
    this.scene = scene;

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
      entity.added(scene);
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

package com.koenji.ecs.system;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityManager;
import com.koenji.ecs.scene.IScene;

import java.util.ArrayList;
import java.util.List;

public class SystemManager implements ISystemManager {

  private IScene scene;
  private IEntityManager entityManager;

  private List<ISystem> toAdd;
  private List<ISystem> toRemove;
  private List<ISystem> systems;

  public SystemManager(IScene scene, IEntityManager entityManager) {
    this.scene = scene;
    this.entityManager = entityManager;

    toAdd = new ArrayList<>();
    toRemove = new ArrayList<>();
    systems = new ArrayList<>();
  }

  public void add(ISystem system) {
    toAdd.add(system);
  }

  public void remove(ISystem system) {
    toRemove.add(system);
  }

  public void clear() {
    toAdd.clear();
    toRemove.clear();
    systems.clear();
  }

  public void update(int dt) {
    // Remove any systems first
    systems.removeAll(toRemove);
    for (ISystem system : toRemove) {
      system.removed();
    }
    // Add new scenes
    systems.addAll(toAdd);
    for (ISystem system : toAdd) {
      system.added(scene, entityManager);
    }
    // Empty modifier lists
    toAdd.clear();
    toRemove.clear();
    // Update all current scenes
    for (ISystem system : systems) {
      // Update system
      system.update(dt);
    }
  }

  public int count() {
    return systems.size();
  }

  @Override
  public void entityAdded(IEntity entity) {
    for (ISystem system : systems) {
      system.entityAdded(entity);
    }
  }

  @Override
  public void entityRemoved(IEntity entity) {
    for (ISystem system : systems) {
      system.entityRemoved(entity);
    }
  }
}

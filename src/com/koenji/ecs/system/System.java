package com.koenji.ecs.system;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityManager;
import com.koenji.ecs.scene.IScene;

import java.util.ArrayList;
import java.util.List;

public abstract class System implements ISystem {

  protected IScene scene;
  protected List<IEntity> entities;

  public System() {
    entities = new ArrayList<>();
  }

  public void added(IScene scene) {
    this.scene = scene;
  }

  public void added(IScene scene, IEntityManager entityManager) {
    added(scene);
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
}

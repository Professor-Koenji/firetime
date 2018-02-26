package com.koenji.ecs.system;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityManager;
import com.koenji.ecs.scene.IScene;

public interface ISystem {
  void added(IScene scene);
  void added(IScene scene, IEntityManager entityManager);
  void removed();
  void update(int dt);
  void entityAdded(IEntity entity);
  void entityRemoved(IEntity entity);
}

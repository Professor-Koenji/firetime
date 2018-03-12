package com.koenji.ecs.system;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityManager;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.scene.IScene;

public interface ISystem {
  void added(IScene scene, IEventController eventController);
  void added(IScene scene, IEventController eventController, IEntityManager entityManager);
  void removed();
  void update(int dt);
  void entityAdded(IEntity entity);
  void entityRemoved(IEntity entity);
}

package com.koenji.ecs.scene;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityGroup;
import com.koenji.ecs.system.ISystem;

public interface IScene {
  void added();
  void removed(boolean clearEvents);
  void update(int dt);
  void add(IEntity entity);
  void add(IEntityGroup entityGroup);
  void add(ISystem system);
  void remove(IEntity entity);
  void remove(IEntityGroup entityGroup);
  void remove(ISystem system);
  void removeAllEntities();
  void removeAllSystems();
  void removeAll();
  void modifiedEntity(IEntity entity);
  int entityCount();
  int systemCount();
}

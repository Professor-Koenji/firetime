package com.koenji.ecs.system;

import com.koenji.ecs.entity.IEntity;

public interface ISystemManager {
  void add(ISystem system);
  void remove(ISystem system);
  void clear();
  void update(int dt);
  int count();
  void entityAdded(IEntity entity);
  void entityRemoved(IEntity entity);
}

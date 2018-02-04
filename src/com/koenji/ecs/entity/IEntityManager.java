package com.koenji.ecs.entity;

public interface IEntityManager {
  void add(IEntity entity);
  void remove(IEntity entity);
  void clear();
  void update(int dt);
  Iterable<IEntity> iterable();
  int count();
}

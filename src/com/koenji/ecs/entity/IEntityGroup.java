package com.koenji.ecs.entity;

import com.koenji.ecs.component.IComponent;

public interface IEntityGroup {
  void add(IEntity e);
  void remove(IEntity e);
  void clear();
  Iterable<IEntity> iterable();
  int count();
  void addComponent(IComponent c);
  void addComponents(IComponent ...cs);
  void removeComponent(Class<? extends IComponent> c);
}

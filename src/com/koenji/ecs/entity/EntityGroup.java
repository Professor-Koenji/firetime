package com.koenji.ecs.entity;

import com.koenji.ecs.component.IComponent;

import java.util.ArrayList;
import java.util.List;

public class EntityGroup implements IEntityGroup {

  private List<IEntity> entities;

  public EntityGroup() {
    entities = new ArrayList<>();
  }

  public void add(IEntity e) {
    entities.add(e);
  }

  public void remove(IEntity e) {
    entities.remove(e);
  }

  public void clear() {
    entities.clear();
  }

  public int count() {
    return entities.size();
  }

  public Iterable<IEntity> iterable() {
    return entities;
  }

  public void addComponent(IComponent c) {
    for (IEntity e : entities) {
      e.addComponent(c);
    }
  }

  public void addComponents(IComponent... cs) {
    for (IEntity e : entities) {
      e.addComponents(cs);
    }
  }

  public void removeComponent(Class<? extends IComponent> c) {
    for (IEntity e : entities) {
      e.removeComponent(c);
    }
  }
}

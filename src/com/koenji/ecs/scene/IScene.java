package com.koenji.ecs.scene;

import com.koenji.ecs.Core;
import com.koenji.ecs.ICore;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityGroup;
import com.koenji.ecs.event.bus.IEventBus;
import com.koenji.ecs.system.ISystem;

public interface IScene {
  void added(ICore core, IEventBus eventBus);
  void removed();
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
  IEventBus getEventBus();
  Core gc();
  int entityCount();
  int systemCount();
}

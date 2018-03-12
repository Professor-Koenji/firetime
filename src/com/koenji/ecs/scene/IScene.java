package com.koenji.ecs.scene;

import com.koenji.ecs.Core;
import com.koenji.ecs.ICore;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityGroup;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.system.ISystem;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public interface IScene {
  void added(ICore core, IEventBus eventBus);
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
  Core gc();
  int entityCount();
  int systemCount();
  void removeAllEventHandlers();
  void removeAllEventHandlers(boolean global);
}

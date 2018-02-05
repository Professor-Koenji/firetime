package com.koenji.ecs.scene;

import com.koenji.ecs.Core;
import com.koenji.ecs.ICore;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.system.ISystem;

public interface IScene {
  void added(ICore core);
  void removed();
  void update(int dt);
  void add(IEntity entity);
  void add(ISystem system);
  void remove(IEntity entity);
  void remove(ISystem system);
  void removeAllEntities();
  void removeAllSystems();
  void removeAll();
  int getWidth();
  int getHeight();
  Core gc();
  int entityCount();
  int systemCount();
}

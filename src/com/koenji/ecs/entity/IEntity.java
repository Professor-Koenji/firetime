package com.koenji.ecs.entity;

import com.koenji.ecs.component.IComponent;
import com.koenji.ecs.scene.IScene;
import com.koenji.firetime.components.DebugDraw;

public interface IEntity {
  void added(IScene scene);
  void removed();
  void update(int dt);
  IEntity addComponent(IComponent c);
  IEntity addComponents(IComponent ...cs);
  IEntity removeComponent(Class<? extends IComponent> c);
  <T extends IComponent> T getComponent(Class<T> c);
  boolean hasComponents(Class<? extends IComponent> ...cs);
  IScene getScene();
}

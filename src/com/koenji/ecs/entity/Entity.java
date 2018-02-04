package com.koenji.ecs.entity;

import com.koenji.ecs.component.IComponent;
import com.koenji.ecs.scene.IScene;

import java.util.HashMap;

public abstract class Entity implements IEntity {

  protected IScene scene;

  private HashMap<Class<? extends IComponent>, IComponent> components;

  public Entity() {
    components = new HashMap<>();
  }

  public void added(IScene scene) {
    this.scene = scene;
  }

  public void removed() {}

  public void update(int dt) { }

  public IEntity addComponent(IComponent c) {
    components.put(c.getClass(), c);
    return this;
  }

  public IEntity addComponents(IComponent... cs) {
    return this;
  }

  public IEntity removeComponent(Class<? extends IComponent> c) {
    return this;
  }

  public IComponent getComponent(Class<? extends IComponent> c) {
    return components.get(c);
  }

  public IScene getScene() {
    return scene;
  }
}

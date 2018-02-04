package com.koenji.ecs.system;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.scene.IScene;

public abstract class System implements ISystem {

  protected IScene scene;

  public void added(IScene scene) {
    this.scene = scene;
  }

  public void removed() {}

  public void update(Iterable<IEntity> entities, int dt) {}
}

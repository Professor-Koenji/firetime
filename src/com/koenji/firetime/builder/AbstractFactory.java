package com.koenji.firetime.builder;

import com.koenji.ecs.scene.IScene;

public abstract class AbstractFactory implements IAbstractFactory {

  protected IScene scene;

  public AbstractFactory(IScene scene) {
    this.scene = scene;
  }
}

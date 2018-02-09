package com.koenji.firetime.builder;

import com.koenji.ecs.entity.IEntity;

public abstract class AbstractFactory implements IAbstractFactory {

  protected abstract IEntity makeEntity(String name);

  public IEntity orderEntity(String type) {
    IEntity entity = makeEntity(type);

    // Do stuff with entity before rtn

    return entity;
  }
}

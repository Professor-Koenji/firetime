package com.koenji.firetime.builder;

import com.koenji.ecs.entity.IEntity;
import com.koenji.firetime.entities.*;

public class EntityFactory extends AbstractFactory {

  @Override
  protected IEntity makeEntity(String e) {
    IEntity entity;

    if(e.equalsIgnoreCase("Particle")) {
      IComponentFactory componentFactory = new ComponentFactory();
      entity = new Particle(componentFactory);


    } else {
      entity = null;
    }

    return entity;
  }

//  @Override
//  public IComponent getComponent(String component, float... options) {
//    return null;
//  }
}

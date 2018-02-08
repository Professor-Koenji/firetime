package com.koenji.firetime.builder;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.scene.Scene;

public class EntityFactory extends AbstractFactory {

  public EntityFactory(Scene scene) {
    super(scene);
  }

  public IEntity getEntity(String e) {
    IEntity entity;

    switch(e) {
      case "Particle" :
        //entity = new Particle();
        entity = null;
        break;

        default:
          entity = null;
    }

    return entity;
  }
}

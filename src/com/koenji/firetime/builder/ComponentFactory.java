package com.koenji.firetime.builder;

import com.koenji.ecs.component.IComponent;
import com.koenji.ecs.component.physics.*;

public class ComponentFactory implements IComponentFactory {

  public ComponentFactory() {

  }

  public IComponent getComponent(String c, float ...options) {

    //System.out.println(Arrays.toString(options));

    IComponent component;

    switch (c) {
      case "Acceleration" :
        component =  new Acceleration();
        break;

      case "BoundingBox" :
        component = new BoundingBox(options);
        break;

      case "CircleBody" :
        component = new CircleBody(options);
        break;

      case "Friction" :
        component = new Friction(options);
        break;

      case "InverseMass" :
        component = new InverseMass(options);
        break;

      case "Position" :
        component = new Position(options);
        break;

      case "Velocity" :
        component = new Velocity(options);
        break;

      default :
          component = null;


    }

    return component;
  }

}

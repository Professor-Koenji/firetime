package com.koenji.firetime.builder;

import com.koenji.ecs.component.IComponent;
import com.koenji.ecs.component.physics.*;

public class ComponentFactory {

  IComponent getComponent(String c) {

    IComponent component;

    switch (c) {
      case "acceleration" :
        component =  new Acceleration();
        break;

      case "BoundingBox" :
        //component = new BoundingBox();
        component = null;
        break;

      case "CircleBody" :
        //component = new CircleBody();
        component = null;
        break;

      case "Friction" :
        component = new Friction();
        break;

      case "InverseMass" :
        //component = new InverseMass();
        component = null;
        break;

      case "Position" :
        component = new Position();
        break;

      case "Velocity" :
        component = new Velocity();
        break;

        default:
          component = null;


    }

    return component;
  }
}

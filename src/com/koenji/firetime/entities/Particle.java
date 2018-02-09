package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.scene.IScene;
import com.koenji.firetime.builder.IComponentFactory;

public class Particle extends Entity {

  IComponentFactory componentFactory;

  public Particle(IComponentFactory componentFactory) {
    this.componentFactory = componentFactory;
  }

  @Override
  public void added(IScene scene) {
    super.added(scene);

    float size = scene.gc().random(8f, 26f);

    addComponent(componentFactory.getComponent("Position", scene.gc().random(0f, scene.gc().getWidth()), scene.gc().random(0f, scene.gc().getHeight())));
    addComponent(componentFactory.getComponent("Velocity", scene.gc().random(-5f, 5f), scene.gc().random(-5f,5f)));
    addComponent(componentFactory.getComponent("Acceleration"));
    addComponent(componentFactory.getComponent("Friction", 0.999f));

    addComponent(componentFactory.getComponent("InverseMass", 1 / (size * size)));
    addComponent(componentFactory.getComponent("BoundingBox", 0,0, scene.gc().getWidth(), scene.gc().getHeight()));
    addComponent(componentFactory.getComponent("CircleBody", size));

//    addComponent(new Position(x, y));
//
//
//    addComponent(new Velocity(scene.gc().random(-5f, 5f), scene.gc().random(-5f,5f)));
//    addComponent(new Acceleration());
//    addComponent(new Friction(0.999f));
//    addComponent(new InverseMass(1 / (size * size)));
//
//    // Constrain Position within this box
//    addComponent(new BoundingBox(0, 0, scene.gc().getWidth(), scene.gc().getHeight()));

    // Physics Body
    addComponent(new CircleBody(size));

    addComponent(new RenderCircle(size, 0xCF3344CC));
  }
}

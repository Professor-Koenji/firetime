package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.scene.IScene;
import com.koenji.firetime.components.Circle;

public class Particle extends Entity {

  public Particle(float x, float y) {
    addComponent(new Position(x, y));
  }

  @Override
  public void added(IScene scene) {
    super.added(scene);

    float size = scene.gc().random(8f, 32f);

    addComponent(new Velocity(scene.gc().random(-4, 4), scene.gc().random(-4,4)));
    addComponent(new Acceleration());
    addComponent(new Friction(0.999f));
    addComponent(new InverseMass(1 / size));

    addComponent(new BoundingBox(0, 0, scene.getWidth(), scene.getHeight()));

    addComponent(new Circle(size));
  }
}

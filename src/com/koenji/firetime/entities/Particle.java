package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.events.IKeyPress;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.component.physics.Circle;
import processing.event.KeyEvent;

public class Particle extends Entity implements IKeyPress {

  private boolean gravityEnabled;

  public Particle(float x, float y) {
    addComponent(new Position(x, y));

    gravityEnabled = true;
  }

  @Override
  public void added(IScene scene) {
    super.added(scene);

    float size = scene.gc().random(8f, 32f);

    scene.gc().subscribe(IKeyPress.class, this);

    addComponent(new Velocity(scene.gc().random(-5f, 5f), scene.gc().random(-5f,5f)));
    addComponent(new Acceleration());
    addComponent(new Friction(0.999f));
    addComponent(new InverseMass(1 / size));

    addComponent(new BoundingBox(0, 0, scene.getWidth(), scene.getHeight()));

    addComponent(new Circle(size));
  }

  @Override
  public void keyPress(KeyEvent event) {
    gravityEnabled = !gravityEnabled;
    if (gravityEnabled) {
      addComponent(new Gravity(.1f));
    } else {
      removeComponent(Gravity.class);
    }
  }
}

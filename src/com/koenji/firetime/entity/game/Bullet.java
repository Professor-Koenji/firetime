package com.koenji.firetime.entity.game;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.observer.IKeyPress;
import com.koenji.ecs.scene.IScene;
import com.koenji.firetime.event.WeaponFireEvent;
import processing.core.PVector;
import processing.event.KeyEvent;

public class Bullet extends Entity implements IKeyPress {

  private boolean doDraw;

  public Bullet(PVector pos, PVector vel) {
    doDraw = true;

    addComponents(
      new Position(pos),
      new Velocity(vel),
      new CircleBody(6),
      ConvexBody.polygon(8, 6),
      new InverseMass(.001f),
      new RenderCircle(6, 0xFFFF0000)
    );
  }

  @Override
  public void added(IScene scene) {
    super.added(scene);
    //
    addComponents(new BoundingBox(BoundingBox.WRAP, 0, 0, scene.gc().getWidth(), scene.gc().getHeight()));

    scene.gc().subscribe(IKeyPress.class, this);

    scene.fireEvent(new WeaponFireEvent());
  }

  @Override
  public void keyPress(KeyEvent event) {
    if (event.getKey() != 'k') return;
    if (doDraw) {
      removeComponent(CircleBody.class);
    } else {
      addComponent(new CircleBody(6));
    }
    doDraw = !doDraw;
  }
}

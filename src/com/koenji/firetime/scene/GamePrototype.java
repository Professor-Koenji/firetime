package com.koenji.firetime.scene;

import com.koenji.ecs.ICore;
import com.koenji.ecs.component.physics.Acceleration;
import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.events.*;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.ConvexCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.firetime.entity.game.Player;
import processing.core.PVector;

public class GamePrototype extends Scene {

  @Override
  public void added(ICore core) {
    super.added(core);
    //
    add(EntityObject.create(
      new Background(0x10000040)
    ));

    ConvexBody wall = new ConvexBody(
      250,
      new PVector(-200, -20),
      new PVector(200, -20),
      new PVector(200, 20),
      new PVector(-200, 20)
    );
    wall.isStatic = true;
    add(EntityObject.create(
      new Position(300, 400),
      new Velocity(),
      new Acceleration(),
      wall,
      new RenderPolygon(wall, 0xFF3399CC)
    ));
    //
    Player p = new Player();
    core.subscribe(IKeyPress.class, p);
    core.subscribe(IKeyRelease.class, p);
    core.subscribe(IMousePress.class, p);
    core.subscribe(IMouseMove.class, p);
    add(p);
    //
    add(new LinearMotion());
    add(new CircleCollider());
    add(new ConvexCollider());
    add(new BasicRenderer());
  }
}
